package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.constants.Status;
import com.aifeng.mgr.admin.dao.impl.MemberDao;
import com.aifeng.mgr.admin.model.*;
import com.aifeng.mgr.admin.service.IMemberService;
import com.aifeng.util.AliSMSUtil;
import com.aifeng.util.Util;
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
    private final AgentMessageService agentMessageService;

    @Autowired
    public MemberService(MemberDao memberDao, AddressService addressService, AgentMessageService agentMessageService) {
        this.memberDao = memberDao;
        this.addressService = addressService;
        this.agentMessageService = agentMessageService;
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
                msg = "已提交，请保持通话通畅，稍后有客服人员与您联系。";
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
        AuxiliaryInformationService auxiliaryInformationService = SpringUtil.getBean("auxiliaryInformationService");

        Address address = addressService.findById(member.getAddress_id());
        AddressFee addressFee = addressFeeService.getAddressFee(member.getAddress_id());
        long af_id = addressFee.getId();
        int amount = addressFee.getAmount();
        ProxyAddress proxyAddress = proxyAddressService.getAuthoredProxyByAfId(af_id);
        if (proxyAddress != null) {
            long agent_id = proxyAddress.getAgent_id();
            Agent agent = agentService.findById(agent_id);

            if (agent.getMoney() > addressFee.getAmount()) {
                String zone = address.getProvince() + " " + address.getCity() + " " + address.getArea();
                String content = Util.loadMsg(address, member, -1);
                AuxiliaryInformation auxiliaryInformation = auxiliaryInformationService.getOrMockFirst();
                AliSMSUtil.send(auxiliaryInformation, agent.getName(), zone, member.getType().getType(), member.getName(), member.getMobile(), agent.getMobile());
                //基本信息
                Message message = messageService.save(content, proxyAddress.getId());
                //做为重发凭证
                agentMessageService.save(member, message, agent);
                messageService.sendMsg(agent, message, amount, true);
            }
        }
    }

    @Transactional
    public List<Map<String, Object>> getPageMember(int page, int size, String addr) {
        return memberDao.getMember(page, size, addr == null ? null : loadAddr(addr));
    }

    @Transactional
    public int getInitTotal(String addr) {
        return memberDao.getInitCount( addr == null ? null : loadAddr(addr));
    }

    private String loadAddr(String addr) {
        String addrTemp = "";
        for (String temp : addr.split(",")) {
            addrTemp += "'" + temp + "',";
        }
        addrTemp = addrTemp.substring(0, addrTemp.lastIndexOf(","));
        return addrTemp;
    }


    @Transactional
    public Map<String, Object> getSingleMember(long id) {
        return memberDao.getSingleProxyAddress(id);
    }

    @Transactional
    public long getTotal() {
        return memberDao.countAll();
    }

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
    public int getTotalCountFromWx(String user_id) {
        return memberDao.getTotalCount(user_id);
    }

    @Transactional
    public List<Map<String, Object>> getQueryFromWx(String user_id, String date) {
        return memberDao.getQuery(user_id, date);
    }

    @Transactional
    public int getQueryCountFromWx(String user_id, String dateStr) {
        return memberDao.getQueryCount(user_id, dateStr);
    }

    @Transactional
    public List<Map<String, Object>> getTodayFromWx(String user_id) {
        return memberDao.getToday(user_id);
    }

    @Transactional
    public int getTodayCountFromWx(String user_id) {
        return memberDao.getTodayCount(user_id);
    }

    @Transactional
    public List<Map<String, Object>> getTodayFromWx(String user_id, boolean visit) {
        return memberDao.getToday(user_id, visit);
    }

    @Transactional
    public int getTodayCountFromWx(String user_id, boolean visit) {
        return memberDao.getTodayCount(user_id, visit);
    }

    @Transactional
    public Map<String, Object> getDetailFromWx(long id) {
        return memberDao.getDetail(id);
    }
}