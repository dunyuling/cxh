package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AgentMessageDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.AgentMessage;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.model.MessageRepeat;
import com.aifeng.mgr.admin.service.IAgentMessageService;
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
public class AgentMessageService extends BaseService<AgentMessage> implements IAgentMessageService {

    private final AgentMessageDao agentMessageDao;
    private final MessageRepeatService messageRepeatService;

    @Autowired
    public AgentMessageService(AgentMessageDao agentMessageDao, MessageRepeatService messageRepeatService) {
        this.agentMessageDao = agentMessageDao;
        this.messageRepeatService = messageRepeatService;
    }

    @Transactional
    public void save(Message message, Agent agent) {
        AgentMessage agentMessage = new AgentMessage();
        agentMessage.setAgent_id(agent.getId());
        agentMessage.setTimes(1);
        agentMessage.setUpdateDate(new Date());
        agentMessage.setMessage_id(message.getId());
        this.agentMessageDao.insert(agentMessage);
    }

    @Transactional
    public List<AgentMessage> getAllNeedRepeat() {
        MessageRepeat messageRepeat = messageRepeatService.getOrMockFirst();
        return this.agentMessageDao.getAllNeedRepeat(messageRepeat.getTotalTimes());
    }

    @Transactional
    public List<Map<String, Object>> getPagerAm(int page, int size) {
        return agentMessageDao.getAm(page, size);
    }

    @Transactional
    public long getTotal() {
        return agentMessageDao.countAll();
    }
}