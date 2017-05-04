package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IRechargeDao;
import com.aifeng.mgr.admin.model.Recharge;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class RechargeDao extends BaseDao<Recharge> implements IRechargeDao {

    public List<Map<String, Object>> getRecharges(int page, int pageSize) {
        String str = "select ag.name as ag_name, r.amount,ad.name as ad_name from recharge r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }
}
