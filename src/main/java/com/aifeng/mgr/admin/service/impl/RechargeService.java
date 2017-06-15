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
    private final MessageService messageService;

    @Autowired
    public RechargeService(RechargeDao refundDao, AgentService agentService, MessageService messageService) {
        this.rechargeDao = refundDao;
        this.agentService = agentService;
        this.messageService = messageService;
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

        String content = "为您成功充值： " + amount + " 元";
        messageService.sendMsg(agent.getUserid(), content);
    }

    @Transactional
    public List<Map<String, Object>> getRecharge(int page, int size, String addr) {
        return rechargeDao.getRecharge(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return rechargeDao.queryRechargeCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryRecharge(int page, int size, String name, String addr) {
        return rechargeDao.queryRecharge(page, size, name, addr);
    }

    @Transactional
    public int queryCount(String name, String addr) {
        return rechargeDao.queryRechargeCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentRecharge(long agentId, int page, int size) {
        return rechargeDao.getAgentRecharge(agentId, page, size);
    }

    @Transactional
    public int getAgentCount(long agentId) {
        return rechargeDao.getAgentRechargeCount(agentId);
    }
}