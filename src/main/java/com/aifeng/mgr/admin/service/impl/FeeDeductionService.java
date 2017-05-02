package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.FeeDeductionDao;
import com.aifeng.mgr.admin.model.FeeDeduction;
import com.aifeng.mgr.admin.service.IFeeDeductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-28.
 */
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
    public void save(long agent_id, int amount) {
        FeeDeduction feeDeduction = new FeeDeduction();
        feeDeduction.setAgent_id(agent_id);
        feeDeduction.setAmount(amount);
        feeDeductionDao.insert(feeDeduction);
    }
}