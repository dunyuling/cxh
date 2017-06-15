package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.FeeDeductionDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.model.FeeDeduction;
import com.aifeng.mgr.admin.service.IFeeDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class FeeDeductionService extends BaseService<FeeDeduction> implements IFeeDeductionService {

    private final FeeDeductionDao feeDeductionDao;
    private final AgentService agentService;

    @Autowired
    public FeeDeductionService(FeeDeductionDao feeDeductionDao, AgentService agentService) {
        this.feeDeductionDao = feeDeductionDao;
        this.agentService = agentService;
    }

    @Transactional
    public void save(long agent_id, long message_id, int amount) {
        FeeDeduction feeDeduction = new FeeDeduction();
        feeDeduction.setAgent_id(agent_id);
        feeDeduction.setMessage_id(message_id);
        feeDeduction.setAmount(amount);
        feeDeductionDao.insert(feeDeduction);

        Agent agent = agentService.findById(agent_id);
        agent.setMoney(agent.getMoney() - amount);
        agentService.update(agent);
    }

    @Transactional
    public List<Map<String, Object>> getFeeDeduction(int page, int size, String addr) {
        return feeDeductionDao.getFeeDeduction(page, size, addr);
    }

    @Transactional
    public int getCount(String addr) {
        return feeDeductionDao.getFeeDeductionCount(addr);
    }

    @Transactional
    public List<Map<String, Object>> queryFeeDeduction(int page, int size, String name, String addr) {
        return feeDeductionDao.queryFeeDeduction(page, size, name, addr);
    }

    @Transactional
    public int queryCount(String name, String addr) {
        return feeDeductionDao.queryFeeDeductionCount(name, addr);
    }

    @Transactional
    public List<Map<String, Object>> getAgentFeeDeduction(long agentId, int page, int size) {
        return feeDeductionDao.getAgentFeeDeduction(agentId, page, size);
    }

    @Transactional
    public int getAgentCount(long agentId) {
        return feeDeductionDao.getAgentFeeDeductionCount(agentId);
    }
}