package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AuditCancelDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.AuditCancel;
import com.aifeng.mgr.admin.service.IAuditCancelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class AuditCancelService extends BaseService<AuditCancel> implements IAuditCancelService {

    private final AuditCancelDao auditCancelDao;
    private final AgentService agentService;
    private final MessageService messageService;

    @Autowired
    public AuditCancelService(AuditCancelDao auditCancelDao, AgentService agentService, MessageService messageService) {
        this.auditCancelDao = auditCancelDao;
        this.agentService = agentService;
        this.messageService = messageService;
    }

    @Transactional
    public void save(long operate_id, long agent_id) {
        Agent agent = agentService.findById(agent_id);

        AuditCancel auditCancel = new AuditCancel();
        auditCancel.setAgent_id(agent_id);
        auditCancel.setAmount(agent.getMoney());
        auditCancel.setAdmin_id(operate_id);
        auditCancelDao.insert(auditCancel);

        agent.setMoney(0);
        agentService.update(agent);

        String content = "您与车险汇解除关系后，剩余的" + agent.getMoney() + " 元，已归还给您";
        messageService.sendMsg(agent.getUserid(), content);
    }

    @Transactional
    public List<Map<String, Object>> getAuditCancel(int page, int size, String addr) {
        return auditCancelDao.getAuditCancel(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return auditCancelDao.getAuditCancelCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryAuditCancel(int page, int size, String name, String addr) {
        return auditCancelDao.queryAuditCancel(page, size, name, addr);
    }

    @Transactional
    public int queryCount(String name, String addr) {
        return auditCancelDao.queryAuditCancelCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentAuditCancel(long agentId, int page, int size) {
        return auditCancelDao.getAuditCancel(agentId, page, size);
    }

    @Transactional
    public long getAgentCount(long agentId) {
        return auditCancelDao.getAgentAuditCancelCount(agentId);
    }
}