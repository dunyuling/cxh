package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.Constants;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AgentMessageDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.AgentMessage;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.model.MessageRepeat;
import com.aifeng.mgr.admin.service.IAgentMessageService;
import com.aifeng.ws.wx.RequestBody;
import com.aifeng.ws.wx.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Service
public class AgentMessageService extends BaseService<AgentMessage> implements IAgentMessageService {

    private final AgentMessageDao agentMessageDao;
    private final MessageRepeatService messageRepeatService;
    private final FeeDeductionService feeDeductionService;
    private final RestTemplate restTemplate;
    private final AuxiliaryInformationService auxiliaryInformationService;

    @Autowired
    public AgentMessageService(AgentMessageDao agentMessageDao, MessageRepeatService messageRepeatService, FeeDeductionService feeDeductionService, RestTemplate restTemplate, AuxiliaryInformationService auxiliaryInformationService) {
        this.agentMessageDao = agentMessageDao;
        this.messageRepeatService = messageRepeatService;
        this.feeDeductionService = feeDeductionService;
        this.restTemplate = restTemplate;
        this.auxiliaryInformationService = auxiliaryInformationService;
    }

    @Transactional
    public void save(Message message, Agent agent) {
        AgentMessage agentMessage = new AgentMessage();
        agentMessage.setAgent_id(message.getId());
        agentMessage.setTimes(1);
        agentMessage.setUpdateDate(new Date());
        agentMessage.setMessage_id(agent.getId());
        this.agentMessageDao.insert(agentMessage);

//        execSendMsg(agentMessage, message, agent);
    }

    @Transactional
    public void execSendMsg(AgentMessage agentMessage, Message message, Agent agent) {
        agentMessage.setTimes(agentMessage.getTimes() + 1);
        agentMessage.setUpdateDate(new Date());
        this.agentMessageDao.insert(agentMessage);

        //扣费
        MessageRepeat messageRepeat = messageRepeatService.getOrMockFirst();
        feeDeductionService.save(agent.getId(), messageRepeat.getAmount());

        //TODO  实际发送消息的动作在这里执行
        RequestBody requestBody = new RequestBody();
        requestBody.setTouser(agent.getUserid());
        requestBody.setMsgtype("text");
        requestBody.setAgentid(0);

        Map<String, Object> map = new HashMap<>();
        map.put("content", message.getContent()); //TODO 定制消息模板
        requestBody.setText(map);
        requestBody.setSafe(0);

        String url = Constants.sendMeg + "?access_token=" + auxiliaryInformationService.getOrMockFirst().getAccess_token();
        restTemplate.getForEntity(url, null, ResponseType.class);
    }

    @Transactional
    public List<AgentMessage> getAllNeedRepeat() {
        MessageRepeat messageRepeat = messageRepeatService.getOrMockFirst();
        return this.agentMessageDao.getAllNeedRepeat(messageRepeat.getTotalTimes());
    }
}