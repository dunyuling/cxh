package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.MessageDao;
import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class MessageService extends BaseService<Message> implements IMessageService {

    private final MessageDao messageDao;
    private final AddressService addressService;
    private final AgentMessageService agentMessageService;
    private final AgentService agentService;

    @Autowired
    public MessageService(MessageDao messageDao, AddressService addressService, AgentMessageService agentMessageService, AgentService agentService) {
        this.messageDao = messageDao;
        this.addressService = addressService;
        this.agentMessageService = agentMessageService;
        this.agentService = agentService;
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
}