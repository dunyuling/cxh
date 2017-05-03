package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.Constants;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.constants.Status;
import com.aifeng.mgr.admin.dao.impl.MemberDao;
import com.aifeng.mgr.admin.model.*;
import com.aifeng.mgr.admin.service.IMemberService;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-4-27.
 */
@Service
public class MemberService extends BaseService<Member> implements IMemberService {

    private final MemberDao memberDao;
    private final AddressService addressService;

    @Autowired
    public MemberService(MemberDao memberDao, AddressService addressService) {
        this.memberDao = memberDao;
        this.addressService = addressService;
    }

    @Transactional
    public String registerMember(String province, String city, String area, String name, String mobile, int index) {
        String msg = "";
        try {
            long address_id = addressService.getAddressId(province, city, area);
            if (address_id == 0) msg = "注册失败！相应地区不存在";
            else {
                Member member = new Member();
                member.setName(name);
                member.setMobile(mobile);
                member.setType(InsuranceType.getTypeByIndex(index));
                member.setAddress_id(address_id);
                memberDao.insert(member);
                msg = "注册成功";


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Transactional
    public void audit(long member_id, String province, String city, String area, String status, String denyReason) {
        Member member = findById(member_id);
        Status status1 = Status.valueOf(status);
        member.setStatus(status1);
        if (status1 == Status.FAILURE) {
            member.setDenyReason(denyReason);
        }
        member.setUpdateDate(new Date());

        if (status1 == Status.SUCCESS) {
            long address_id = addressService.getAddressId(province, city, area);
            toSendWxMsg(address_id, member);
        }
    }

    @Transactional
    private void toSendWxMsg(long address_id, Member member) {
        AddressService addressService = SpringUtil.getBean("addressService");
        AddressFeeService addressFeeService = SpringUtil.getBean("addressFeeService");
        ProxyAddressService proxyAddressService = SpringUtil.getBean("proxyAddressService");
        AgentService agentService = SpringUtil.getBean("agentService");
        MessageService messageService = SpringUtil.getBean("messageService");
        AgentMessageService agentMessageService = SpringUtil.getBean("agentMessageService");

        Address address = addressService.findById(address_id);
        long af_id = addressFeeService.getAddressFee(address_id).getId();
        ProxyAddress proxyAddress = proxyAddressService.getProxyByAfId(af_id);
        if (proxyAddress != null) {
            long agent_id = proxyAddress.getAgent_id();
            Agent agent = agentService.findById(agent_id);

            String content = Constants.wxMsgTitle +
                    "\n\n时间: " + DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm") +
                    "\n姓名: " + member.getName() + "" +
                    "\n电话: " + member.getMobile() +
                    "\n地区: " + address.getProvince() + " " + address.getCity() + " " + address.getArea() +
                    "\n咨询类型: " + "车险询价" +
                    "\n您处理该信息后，请点击:http://www.baidu.com";

            messageService.sendMsg(agent.getUserid(), content);

            //基本信息
            Message message = messageService.save(content, proxyAddress.getId());
            //做为重发凭证
            agentMessageService.save(message, agent);
        }
    }

    @Transactional
    private void toSendMsg() {

    }
}
