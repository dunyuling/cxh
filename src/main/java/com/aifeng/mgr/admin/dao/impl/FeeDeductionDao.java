package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IFeeDeductionDao;
import com.aifeng.mgr.admin.model.FeeDeduction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class FeeDeductionDao extends BaseDao<FeeDeduction> implements IFeeDeductionDao {

    public List<Map<String, Object>> getFds(int page, int pageSize) {
        String str = "select ag.name, m.content,fd.amount from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }
}
