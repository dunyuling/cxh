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

/**
 * Created by pro on 17-4-27.
 */
@Service
public class AgentMessageService extends BaseService<AgentMessage> implements IAgentMessageService {

    private final AgentMessageDao agentMessageDao;
    private final MessageService messageService;
    private final MessageRepeatService messageRepeatService;
    private final AgentService agentService;

    @Autowired
    public AgentMessageService(AgentMessageDao agentMessageDao, MessageService messageService, MessageRepeatService messageRepeatService, AgentService agentService) {
        this.agentMessageDao = agentMessageDao;
        this.messageService = messageService;
        this.messageRepeatService = messageRepeatService;
        this.agentService = agentService;
    }

    @Transactional
    public void save(Message message, Agent agent) {
        AgentMessage agentMessage = new AgentMessage();
        agentMessage.setAgent_id(message.getId());
        agentMessage.setMessage_id(agent.getId());
        agentMessage.setTimes(1);
        this.agentMessageDao.insert(agentMessage);

        execSendMsg(message, agent);
    }

    //TODO 此处添加定时器,定时器的间隔要从数据库读取
    @Transactional
    public void getAllNeedRepeat() {
        MessageRepeat messageRepeat = messageRepeatService.getOrMockFirst();
        List<AgentMessage> agentMessageList = this.agentMessageDao.getAllNeedRepeat(messageRepeat.getTotalTimes());
        for (AgentMessage agentMessage : agentMessageList) {
            Message message = messageService.findById(agentMessage.getMessage_id());
            Agent agent = agentService.findById(agentMessage.getAgent_id());
            execSendMsg(message, agent);

            agentMessage.setTimes(agentMessage.getTimes() + 1);
            agentMessage.setUpdateDate(new Date());
            this.agentMessageDao.insert(agentMessage);
        }
    }

    @Transactional
    public void execSendMsg(Message message, Agent agent) {
        //TODO  实际发送消息的动作在这里执行
    }


}
