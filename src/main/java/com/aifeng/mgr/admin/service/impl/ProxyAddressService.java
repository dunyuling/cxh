package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.service.IBaseService;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.ProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class ProxyAddressService extends BaseService<ProxyAddress> implements IBaseService<ProxyAddress> {

    private final ProxyAddressDao proxyAddressDao;
    private final MessageService messageService;

    @Autowired
    public ProxyAddressService(ProxyAddressDao proxyAddressDao, MessageService messageService) {
        this.proxyAddressDao = proxyAddressDao;
        this.messageService = messageService;
    }

    @Transactional
    public boolean checkOthersProxied(long af_id) {
        return proxyAddressDao.getByAfId(af_id) == null;
    }

    @Transactional
    public boolean checkSelfProxied(long agent_id, long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(agent_id, af_id) == null;
    }

    @Transactional
    public void save(long agent_id, long af_id) {
        ProxyAddress proxyAddress = new ProxyAddress();
        proxyAddress.setAgent_id(agent_id);
        proxyAddress.setAf_id(af_id);
        proxyAddress.setCreateDate(new Date());
        this.proxyAddressDao.insert(proxyAddress);
    }

    @Transactional
    public ProxyAddress getOne(long id) {
        return proxyAddressDao.findById(id);
    }

    @Transactional
    public void author(long id) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddress.setProxyStatus(ProxyStatus.Authored);
        proxyAddressDao.insert(proxyAddress);

        //TODO 给用户发送一条消息

    }

    @Transactional
    public void refuse(long id, String reason) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddress.setProxyStatus(ProxyStatus.Refused);
        proxyAddress.setDenyReason(reason);
        proxyAddressDao.insert(proxyAddress);

        //TODO 给用户发送一条信息

    }
}
