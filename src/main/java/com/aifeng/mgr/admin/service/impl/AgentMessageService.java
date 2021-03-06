package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.dao.impl.AgentMessageDao;
import com.aifeng.mgr.admin.model.*;
import com.aifeng.mgr.admin.service.IAgentMessageService;
import com.aifeng.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Service
public class AgentMessageService extends BaseService<AgentMessage> implements IAgentMessageService {

    private AddressService addressService;
    private AgentService agentService;
    private MessageService messageService;
    private MemberService memberService;
    private final AgentMessageDao agentMessageDao;
    private final MessageRepeatService messageRepeatService;

    @Autowired
    public AgentMessageService(AgentMessageDao agentMessageDao, MessageRepeatService messageRepeatService) {
        this.agentMessageDao = agentMessageDao;
        this.messageRepeatService = messageRepeatService;
    }

    @Transactional
    public void saveAgentMessage(Member member, Message message, Agent agent) {
        AgentMessage agentMessage = new AgentMessage();
        agentMessage.setMember_id(member.getId());
        agentMessage.setAgent_id(agent.getId());
        agentMessage.setTimes(1);
        agentMessage.setUpdateDate(new Date());
        agentMessage.setMessage_id(message.getId());
        this.agentMessageDao.insert(agentMessage);
    }

    @Transactional
    List<AgentMessage> getAllNeedRepeat() {
        MessageRepeat messageRepeat = messageRepeatService.getOrMockFirst();
        return this.agentMessageDao.getAllNeedRepeat(messageRepeat.getTotalTimes());
    }

    @Transactional
    public List<Map<String, Object>> getAgentMessage(int page, int size, String addr) {
        return agentMessageDao.getAgentMessage(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return agentMessageDao.getAgentMessageCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryAgentMessage(int page, int size, String name, String addr) {
        return agentMessageDao.queryAgentMessage(page, size, name, addr);
    }

    @Transactional
    public int queryCount(String name, String addr) {
        return agentMessageDao.queryAgentMessageCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentMessage(long agentId, int page, int size) {
        return agentMessageDao.getAgentMessage(agentId, page, size);
    }

    @Transactional
    public int getCount(long agentId) {
        return agentMessageDao.getAgentMessageCount(agentId);
    }

    @Transactional
    public void visitAgentMessage(long member_id) {
        AgentMessage agentMessage = agentMessageDao.findByMemberId(member_id);
        if (!agentMessage.isVisit()) {
            agentMessage.setVisit(true);
            agentMessage.setVisitDate(new Date());
            agentMessageDao.update(agentMessage);
        }
    }

    @Scheduled(cron = "0 */5 * * * ?")
    @Transactional
    public void repeatSendAgentMessage() {
        addressService = addressService == null ? SpringUtil.getBean("addressService") : addressService;
        agentService = agentService == null ? SpringUtil.getBean("agentService") : agentService;
        messageService = messageService == null ? SpringUtil.getBean("messageService") : messageService;
        memberService = memberService == null ? SpringUtil.getBean("memberService") : memberService;

        List<Map<String, Object>> list = agentMessageDao.getUnVisit();
        for (Map<String, Object> map : list) {
            long id = Long.parseLong(map.get("id").toString());
            long agent_id = Long.parseLong(map.get("agent_id").toString());
            long member_id = Long.parseLong(map.get("member_id").toString());
            long address_id = Long.parseLong(map.get("address_id").toString());

            Agent agent = agentService.findById(agent_id);
            Member member = memberService.findById(member_id);
            Address address = addressService.findById(address_id);
            AgentMessage agentMessage = findById(id);

            String content = Util.loadMsg(address, member, agentMessage.getTimes() + 1);
            System.out.println("id: " + id + "\t agent_id: " + agent_id + "\tmember_id: " + member_id + "\taddress_id: " + address_id + "\t where am.visit is false");
            messageService.sendMsg(agent.getUserid(), content);

            agentMessage.setTimes(agentMessage.getTimes() + 1);
            agentMessageDao.update(agentMessage);
        }
    }
}