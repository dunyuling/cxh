package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAuditCancelDao;
import com.aifeng.mgr.admin.model.AuditCancel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-30.
 */
@Repository
public class AuditCancelDao extends BaseDao<AuditCancel> implements IAuditCancelDao {

    public List<Map<String, Object>> getAuditCancels(int page, int pageSize) {
        String str = "select ag.name as ag_name, ac.amount,ad.name as ad_name,ac.createDate from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                "order by ac.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public List<Map<String, Object>> getAuditCancels(long agentId, int page, int pageSize) {
        String str = "select ag.name as ag_name, ac.amount,ad.name as ad_name,ac.createDate from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                "where ag.id = " + agentId +
                " order by ac.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentAuditCancelCount(long agentId) {
        String str = "select ag.name as ag_name, ac.amount,ad.name as ad_name,ac.createDate from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                "where ag.id = " + agentId;
        return this.findBySql(str).size();
    }
}
