package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.RefundDao;
import com.aifeng.mgr.admin.model.Refund;
import com.aifeng.mgr.admin.service.IRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class RefundService extends BaseService<Refund> implements IRefundService {

    private final RefundDao refundDao;

    @Autowired
    public RefundService(RefundDao refundDao) {
        this.refundDao = refundDao;
    }

    @Transactional
    public void save(long operate_id, long agent_id, int amount) {
        Refund refund = new Refund();
        refund.setAgent_id(agent_id);
        refund.setAmount(amount);
        refund.setAdmin_id(operate_id);
        refundDao.insert(refund);
    }
}