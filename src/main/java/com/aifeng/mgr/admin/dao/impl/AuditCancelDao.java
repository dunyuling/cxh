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

    public List<Map<String, Object>> getAuditCancel(int page, int pageSize, String addr) {
        String sql = "select distinct ac.id, ag.name as ag_name, ac.amount,ad.name as ad_name,ac.createDate from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += "order by ac.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int getAuditCancelCount(String addr) {
        String sql = "select count(distinct ac.id) as count from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryAuditCancel(int page, int pageSize, String name, String addr) {
        String sql = "select distinct ac.id, ag.name as ag_name, ac.amount,ad.name as ad_name,ac.createDate from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%' ";
        sql += "order by ac.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int queryAuditCancelCount(String name, String addr) {
        String sql = "select count(distinct ac.id) as count from audit_cancel ac " +
                "left join tb_mgr_admin ad on ad.id = ac.admin_id " +
                "left join agent ag on ag.id = ac.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%' ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAuditCancel(long agentId, int page, int pageSize) {
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
