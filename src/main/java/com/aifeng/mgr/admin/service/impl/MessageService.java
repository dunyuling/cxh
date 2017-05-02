package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.MessageDao;
import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.AgentMessage;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.service.IMessageService;
import com.aifeng.util.Util;
import com.aifeng.ws.wx.RequestBody;
import com.aifeng.ws.wx.ResponseType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class MessageService extends BaseService<Message> implements IMessageService {

    private final MessageDao messageDao;
    private final AddressService addressService;
    private final AgentMessageService agentMessageService;
    private final AgentService agentService;
    private final MessageRepeatService messageRepeatService;
    private final RestTemplate restTemplate;
    private final AuxiliaryInformationService auxiliaryInformationService;

    @Autowired
    public MessageService(MessageDao messageDao, AddressService addressService, AgentMessageService agentMessageService, AgentService agentService, MessageRepeatService messageRepeatService, RestTemplate restTemplate, AuxiliaryInformationService auxiliaryInformationService) {
        this.messageDao = messageDao;
        this.addressService = addressService;
        this.agentMessageService = agentMessageService;
        this.agentService = agentService;
        this.messageRepeatService = messageRepeatService;
        this.restTemplate = restTemplate;
        this.auxiliaryInformationService = auxiliaryInformationService;
    }

    @Transactional
    public void sendAuthorMsg(long proxyAddressId) {
        Address address = addressService.getAddressByProxyAddressId(proxyAddressId);
        String content = "您已成功代理 " + address.getProvince() + " " + address.getCity() + " " + address.getArea();
        Message message = this.messageDao.insert(new Message(proxyAddressId, content));

        Agent agent = agentService.getAgentByProxyAddressId(proxyAddressId);
        agentMessageService.save(message, agent);
    }

    @Transactional
    public void sendRefuseMsg(long proxyAddressId, String denyReason) {
        Address address = addressService.getAddressByProxyAddressId(proxyAddressId);
        String content = "您申请的对 " + address.getProvince() + " " + address.getCity() + " " + address.getArea() +
                " 已经被拒绝，原因为 " + denyReason;
        Message message = this.messageDao.insert(new Message(proxyAddressId, content));

        Agent agent = agentService.getAgentByProxyAddressId(proxyAddressId);
        agentMessageService.save(message, agent);
    }


    //TODO 此处添加定时器,定时器的间隔要从数据库读取
    @Transactional
    public void getAllNeedRepeat() {
        List<AgentMessage> agentMessageList = agentMessageService.getAllNeedRepeat();
        for (AgentMessage agentMessage : agentMessageList) {
            Message message = findById(agentMessage.getMessage_id());
            Agent agent = agentService.findById(agentMessage.getAgent_id());
            agentMessageService.execSendMsg(agentMessage, message, agent);
        }
    }

    @Transactional
    public void sendMsg() {
        RequestBody requestBody = new RequestBody();
        requestBody.setTouser("lhg0");
        requestBody.setMsgtype("text");
        requestBody.setAgentid(0);

        Map<String, Object> map = new HashMap<>();
        map.put("content", "a");
        requestBody.setText(map);
//        requestBody.setText1("aa");
        requestBody.setSafe(0);

        String access_token = auxiliaryInformationService.getAccessToken();
        ResponseEntity<ResponseType> response = restTemplate.postForEntity(Util.loadSendMsgUrl(access_token), requestBody, ResponseType.class);
        System.out.println(response.getBody().getErrmsg());
    }


}