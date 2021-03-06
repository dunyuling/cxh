package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.MessageDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.AgentMessage;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.service.IMessageService;
import com.aifeng.util.Util;
import com.aifeng.ws.wx.RequestBody;
import com.aifeng.ws.wx.ResponseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@Service
public class MessageService extends BaseService<Message> implements IMessageService {

    private final MessageDao messageDao;
    private final AgentMessageService agentMessageService;
    private final AgentService agentService;
    private final RestTemplate restTemplate;
    private final AuxiliaryInformationService auxiliaryInformationService;
    private final FeeDeductionService feeDeductionService;
    private final ExecutorService executorService;

    @Autowired
    public MessageService(MessageDao messageDao, AgentMessageService agentMessageService, AgentService agentService, RestTemplate restTemplate, AuxiliaryInformationService auxiliaryInformationService, FeeDeductionService feeDeductionService) {
        this.messageDao = messageDao;
        this.agentMessageService = agentMessageService;
        this.agentService = agentService;
        this.restTemplate = restTemplate;
        this.auxiliaryInformationService = auxiliaryInformationService;
        this.feeDeductionService = feeDeductionService;
        executorService = new ScheduledThreadPoolExecutor(10);
    }

    @Transactional
    public void sendMsg(Agent agent, Message message, int amount, boolean charge) {
        RequestBody requestBody = new RequestBody();
        requestBody.setTouser(agent.getUserid());
        requestBody.setMsgtype("text");
        requestBody.setAgentid(0);

        Map<String, Object> map = new HashMap<>();
        map.put("content", message.getContent());
        requestBody.setText(map);
        requestBody.setSafe(0);

        String access_token = auxiliaryInformationService.getAccessToken();
        ResponseEntity<ResponseType> response = restTemplate.postForEntity(Util.loadSendMsgUrl(access_token), requestBody, ResponseType.class);
        System.out.println(response.getBody().getErrmsg());

        if (charge && amount > 0) {
            feeDeductionService.save(agent.getId(), message.getId(), amount);
        }
    }

    //不计费
    public void sendMsg(String toUser, String content) {
        RequestBody requestBody = new RequestBody();
        requestBody.setTouser(toUser);
        requestBody.setMsgtype("text");
        requestBody.setAgentid(0);

        Map<String, Object> map = new HashMap<>();
        map.put("content", content);
        requestBody.setText(map);
        requestBody.setSafe(0);

        executorService.execute(() -> {
            String access_token = auxiliaryInformationService.getAccessToken();
            ResponseEntity<ResponseType> response = restTemplate.postForEntity(Util.loadSendMsgUrl(access_token), requestBody, ResponseType.class);
            System.out.println("msg: " + response.getBody().getErrmsg());
        });
    }

    @Transactional
    public Message save(String content, long proxyAddress_id) {
        Message message = new Message();
        message.setContent(content);
        message.setProxyAddress_id(proxyAddress_id);
        return messageDao.insert(message);
    }

    @Transactional
    public List<Map<String, Object>> getMessage(int page, int size, String addr) {
        return messageDao.getMessage(page, size, addr);
    }

    @Transactional
    public long getMessageCount(String addr) {
        return messageDao.getMessageCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryMessage(int page, int size, String name, String addr) {
        return messageDao.queryMessage(page, size, name, addr);
    }

    @Transactional
    public int queryMessageCount(String name, String addr) {
        return messageDao.queryMessageCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentMessage(long agentId, int page, int size) {
        return messageDao.getAgentMessage(agentId, page, size);
    }

    @Transactional
    public long getAgentCount(long agentId) {
        return messageDao.getAgentMessageCount(agentId);
    }
}