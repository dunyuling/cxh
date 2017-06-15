package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.RefundDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.Refund;
import com.aifeng.mgr.admin.service.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class RefundService extends BaseService<Refund> implements IRefundService {

    private final RefundDao refundDao;
    private final AgentService agentService;
    private final MessageService messageService;

    @Autowired
    public RefundService(RefundDao refundDao, AgentService agentService, MessageService messageService) {
        this.refundDao = refundDao;
        this.agentService = agentService;
        this.messageService = messageService;
    }

    @Transactional
    public void save(long operate_id, long agent_id, int amount) {
        Refund refund = new Refund();
        refund.setAgent_id(agent_id);
        refund.setAmount(amount);
        refund.setAdmin_id(operate_id);
        refundDao.insert(refund);

        Agent agent = agentService.findById(agent_id);
        agent.setMoney(agent.getMoney() + amount);
        agentService.update(agent);

        String content = "为您成功退费： " + amount + " 元";
        messageService.sendMsg(agent.getUserid(), content);
    }

    @Transactional
    public List<Map<String, Object>> getRefund(int page, int size, String addr) {
        return refundDao.getRefund(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return refundDao.getRefundCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryRefund(int page, int size, String name, String addr) {
        return refundDao.queryRefund(page, size, name, addr);
    }

    @Transactional
    public int queryTotal(String name, String addr) {
        return refundDao.queryRefundCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentRefund(long agentId, int page, int size) {
        return refundDao.getAgentRefund(agentId, page, size);
    }

    @Transactional
    public int getAgentCount(long agentId) {
        return refundDao.getAgentRefundCount(agentId);
    }
}