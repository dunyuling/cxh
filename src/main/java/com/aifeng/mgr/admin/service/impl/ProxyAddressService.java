package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.ProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IProxyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    public List<Map<String, Object>> getPagerProxyAddress(int page, int size) {
        return proxyAddressDao.getProxyAddress(page, size);
    }

    @Transactional
    public Map<String, Object> getProxyAddress(long id) {
        return proxyAddressDao.getSingleProxyAddress(id);
    }

    @Transactional
    public long getTotal() {
        return proxyAddressDao.countAll();
    }

    @Transactional
    public void auditProxyAddress(long id, String proxyStatus, String denyReason) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddress.setProxyStatus(ProxyStatus.valueOf(proxyStatus));
        proxyAddress.setDenyReason(denyReason);
        proxyAddress.setUpdateDate(new Date());
        proxyAddressDao.update(proxyAddress);
    }

    @Transactional
    public void delAgent(long id) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddressDao.delete(proxyAddress);
    }
}
