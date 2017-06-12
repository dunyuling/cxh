package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.core.util.SpringUtil;
import com.aifeng.mgr.admin.constants.ProxyStatus;
import com.aifeng.mgr.admin.dao.impl.ProxyAddressDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.ProxyAddress;
import com.aifeng.mgr.admin.service.IProxyAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ProxyAddressService extends BaseService<ProxyAddress> implements IProxyAddressService {

    private final ProxyAddressDao proxyAddressDao;

    @Autowired
    public ProxyAddressService(ProxyAddressDao proxyAddressDao) {
        this.proxyAddressDao = proxyAddressDao;
    }

    @Transactional
    boolean checkProxied(long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(af_id) != null;
    }

    @Transactional
    ProxyAddress getAuthoredProxyByAfId(long af_id) {
        return proxyAddressDao.getByAgentIdAndAfId(af_id);
    }

    @Transactional
    public void save(long agent_id, long af_id) {
        ProxyAddress proxyAddress = new ProxyAddress();
        proxyAddress.setAgent_id(agent_id);
        proxyAddress.setAf_id(af_id);
        proxyAddress.setCreateDate(new Date());
        proxyAddress.setProxyStatus(ProxyStatus.APPLYING);
        this.proxyAddressDao.insert(proxyAddress);
    }

    @Transactional
    public List<Map<String, Object>> getPagerProxyAddress(int page, int size, String addr) {
        return proxyAddressDao.getProxyAddress(page, size, addr);
    }

    @Transactional
    public int getProxyAddressCount(String addr) {
        return proxyAddressDao.getProxyAddressCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> getQueryProxyAddress(int page, int size, String name, String addr) {
        return proxyAddressDao.getQueryProxyAddress(page, size, name, addr);
    }

    @Transactional
    public int getQueryProxyAddressCount(String name, String addr) {
        return proxyAddressDao.getQueryProxyAddressCount(name, addr);
    }

    @Transactional
    public Map<String, Object> getProxyAddress(long id) {
        return proxyAddressDao.getSingleProxyAddress(id);
    }

    @Transactional
    public List<Map<String, Object>> getAgentPagerProxyAddress(long agent_id, int page, int size) {
        return proxyAddressDao.getAgentProxyAddress(agent_id, page, size);
    }

    @Transactional
    public long getAgentCount(long agent_id) {
        return proxyAddressDao.getAgentCount(agent_id);
    }

    @Transactional
    public List<Map<String, Object>> getAgentQueryPagerProxyAddress(long agent_id, int page, int size, String name) {
        return proxyAddressDao.getAgentQueryProxyAddress(agent_id, page, size, name);
    }

    @Transactional
    public long getAgentQueryCount(long agent_id, String name) {
        return proxyAddressDao.getAgentQueryCount(agent_id, name);
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

    @Transactional
    void auditCancelPa(long agent_id) {
        this.proxyAddressDao.auditCancelPa(agent_id);
    }
}
