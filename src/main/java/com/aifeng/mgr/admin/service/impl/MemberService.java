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
import java.util.List;
import java.util.Map;

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
    public void audit(long member_id, String status, String denyReason) {
        Member member = findById(member_id);
        Status status1 = Status.valueOf(status);
        member.setStatus(status1);
        if (status1 == Status.FAILURE) {
            member.setDenyReason(denyReason);
        }
        member.setUpdateDate(new Date());

        if (status1 == Status.SUCCESS) {
            toSendWxMsg(member);
        }
    }

    @Transactional
    private void toSendWxMsg(Member member) {
        AddressService addressService = SpringUtil.getBean("addressService");
        AddressFeeService addressFeeService = SpringUtil.getBean("addressFeeService");
        ProxyAddressService proxyAddressService = SpringUtil.getBean("proxyAddressService");
        AgentService agentService = SpringUtil.getBean("agentService");
        MessageService messageService = SpringUtil.getBean("messageService");
        AgentMessageService agentMessageService = SpringUtil.getBean("agentMessageService");

        Address address = addressService.findById(member.getAddress_id());
        AddressFee addressFee = addressFeeService.getAddressFee(member.getAddress_id());
        long af_id = addressFee.getId();
        int amount = addressFee.getAmount();
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

            //基本信息
            Message message = messageService.save(content, proxyAddress.getId());
            //做为重发凭证
            agentMessageService.save(member, message, agent);

            messageService.sendMsg(agent, message, amount, true);
        }
    }

    @Transactional
    private void toSendMsg() {

    }

    @Transactional
    public List<Map<String, Object>> getPageMember(int page, int size) {
        return memberDao.getMember(page, size);
    }

    @Transactional
    public Map<String, Object> getSingleMember(long id) {
        return memberDao.getSingleProxyAddress(id);
    }

    @Transactional
    public long getTotal() {
        return memberDao.countAll();
    }

    /*@Transactional
    public void edit(long id, String name, String mobile, String type, String province, String city, String area) {
        long addressId = addressService.getAddressId(province, city, area);
        Member member = findById(id);
        member.setName(name);
        member.setMobile(mobile);
        member.setType(InsuranceType.valueOf(type));
        member.setAddress_id(addressId);
        member.setUpdateDate(new Date());
        memberDao.insert(member);
    }*/

    @Transactional
    public void delMember(long id) {
        Member member = memberDao.findById(id);
        memberDao.delete(member);
    }

    @Transactional
    public List<Map<String, Object>> getTotalFromWx(String user_id) {
        return memberDao.getTotal(user_id);
    }

    @Transactional
    public List<Map<String, Object>> getTodayFromWx() {
        return null;
    }

    @Transactional
    public List<Map<String, Object>> getTodayFromWx(boolean visit) {
        return null;
    }
}