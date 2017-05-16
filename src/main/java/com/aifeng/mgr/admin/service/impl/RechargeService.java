package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.RechargeDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.Recharge;
import com.aifeng.mgr.admin.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class RechargeService extends BaseService<Recharge> implements IRechargeService {

    private final RechargeDao rechargeDao;
    private final AgentService agentService;

    @Autowired
    public RechargeService(RechargeDao refundDao, AgentService agentService) {
        this.rechargeDao = refundDao;
        this.agentService = agentService;
    }

    @Transactional
    public void save(long operate_id, long agent_id, int amount) {
        Recharge recharge = new Recharge();
        recharge.setAgent_id(agent_id);
        recharge.setAmount(amount);
        recharge.setAdmin_id(operate_id);
        rechargeDao.insert(recharge);

        Agent agent = agentService.findById(agent_id);
        agent.setMoney(agent.getMoney() + amount);
        agentService.update(agent);
    }

    @Transactional
    public List<Map<String, Object>> getPagerRecharge(int page, int size) {
        return rechargeDao.getRecharges(page, size);
    }

    @Transactional
    public long getTotal() {
        return rechargeDao.countAll();
    }

    @Transactional
    public List<Map<String, Object>> getAgentPagerRecharge(long agentId, int page, int size) {
        return rechargeDao.getAgentRecharges(agentId, page, size);
    }

    @Transactional
    public int getAgentTotal(long agentId) {
        return rechargeDao.getAgentRechargesCount(agentId);
    }
}