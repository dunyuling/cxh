package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.constants.ProxyStatus;
import com.aifeng.mgr.admin.dao.impl.ProxyAddressDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IProxyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Service
@Scope
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
    public ProxyAddress getProxyByAfId(long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(af_id);
    }

    @Transactional
    public ProxyAddress getAuthoredProxyByAfId(long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(af_id);
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
    public synchronized void auditProxyAddress(long id, String province, String city, String area, String proxyStatus, String denyReason) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        if (proxyAddress.getProxyStatus() == ProxyStatus.APPLYING) {
            ProxyStatus status = ProxyStatus.valueOf(proxyStatus);
            proxyAddress.setProxyStatus(status);
            proxyAddress.setDenyReason(denyReason);
            proxyAddress.setUpdateDate(new Date());
            proxyAddressDao.update(proxyAddress);

            AgentService agentService = SpringUtil.getBean("agentService");
            MessageService messageService = SpringUtil.getBean("messageService");

            String content = "您代理 " + province + " " + city + " " + area + " ";
            Agent agent = agentService.findById(proxyAddress.getAgent_id());
            switch (status) {
                case AUTHORED:
                    content = "恭喜" + content + "成功";
                    break;
                case REFUSED:
                    content = "抱歉" + content + "失败，原因是:" + denyReason;
                    break;
            }
            messageService.sendMsg(agent.getUserid(), content);
        }
    }

    @Transactional
    public void delAgent(long id) {
        ProxyAddress proxyAddress = proxyAddressDao.findById(id);
        proxyAddressDao.delete(proxyAddress);
    }
}
