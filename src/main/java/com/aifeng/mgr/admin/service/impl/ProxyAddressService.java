package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.service.IBaseService;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.ProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IProxyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class ProxyAddressService extends BaseService<ProxyAddress> implements IProxyAddressService {

    private final ProxyAddressDao proxyAddressDao;

    @Autowired
    public ProxyAddressService(ProxyAddressDao proxyAddressDao) {
        this.proxyAddressDao = proxyAddressDao;
    }

    @Transactional
    public boolean checkProxied(long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(af_id) != null;
    }

    @Transactional
    public void save(long agent_id, long af_id) {
        ProxyAddress proxyAddress = new ProxyAddress();
        proxyAddress.setAgent_id(agent_id);
        proxyAddress.setAf_id(af_id);
        proxyAddress.setCreateDate(new Date());
        proxyAddress.setProxyStatus(ProxyStatus.APPLYING); //TODO 基于后台手动授权
        this.proxyAddressDao.insert(proxyAddress);
    }

    @Transactional
    public ProxyAddress getOne(long id) {
        return proxyAddressDao.findById(id);
    }

    @Transactional
    public void author(long id) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddress.setProxyStatus(ProxyStatus.AUTHORED);
        proxyAddressDao.insert(proxyAddress);
    }

    @Transactional
    public void refuse(long id, String reason) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddress.setProxyStatus(ProxyStatus.REFUSED);
        proxyAddress.setDenyReason(reason);
        proxyAddressDao.insert(proxyAddress);
    }
}
