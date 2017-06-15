package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IRefundDao;
import com.aifeng.mgr.admin.model.Refund;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class RefundDao extends BaseDao<Refund> implements IRefundDao {

    public List<Map<String, Object>> getRefund(int page, int pageSize, String addr) {
        String sql = "select distinct r.id, ag.name as ag_name, r.amount,ad.name as ad_name,r.createDate from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += "order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;

        return this.findBySql(sql);
    }

    public int getRefundCount(String addr) {
        String sql = "select count(distinct r.id) as count from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryRefund(int page, int pageSize, String name, String addr) {
        String sql = "select distinct r.id, ag.name as ag_name, r.amount,ad.name as ad_name,r.createDate from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        sql += "order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int queryRefundCount(String name, String addr) {
        String sql = "select count(distinct r.id) as count from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentRefund(long agentId, int page, int pageSize) {
        String sql = "select ag.name as ag_name, r.amount,ad.name as ad_name,r.createDate from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                "where ag.id = " + agentId +
                " order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int getAgentRefundCount(long agentId) {
        String sql = "select ag.name as ag_name, r.amount,ad.name as ad_name,r.createDate from refund r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                "where ag.id = " + agentId;
        return this.findBySql(sql).size();
    }
}
