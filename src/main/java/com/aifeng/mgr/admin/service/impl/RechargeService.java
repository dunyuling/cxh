package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.RechargeDao;
import com.aifeng.mgr.admin.model.Recharge;
import com.aifeng.mgr.admin.service.IRechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class RechargeService extends BaseService<Recharge> implements IRechargeService {

    private final RechargeDao rechargeDao;

    @Autowired
    public RechargeService(RechargeDao refundDao) {
        this.rechargeDao = refundDao;
    }

    @Transactional
    public void save(long operate_id, long agent_id, int amount) {
        Recharge recharge = new Recharge();
        recharge.setAgent_id(agent_id);
        recharge.setAmount(amount);
        recharge.setAdmin_id(operate_id);
        rechargeDao.insert(recharge);
    }
}