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

    public List<Map<String, Object>> getRecharge(int page, int pageSize, String addr) {
        String sql = "select distinct r.id ,ag.name as ag_name, r.amount,ad.name as ad_name, r.createDate from recharge r " +
                " join tb_mgr_admin ad on ad.id = r.admin_id " +
                " join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += "order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int queryRechargeCount(String addr) {
        String sql = "select count(distinct r.id) as count from recharge r " +
                " join tb_mgr_admin ad on ad.id = r.admin_id " +
                " join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryRecharge(int page, int pageSize, String name, String addr) {
        String sql = "select distinct r.id ,ag.name as ag_name, r.amount,ad.name as ad_name, r.createDate from recharge r " +
                " join tb_mgr_admin ad on ad.id = r.admin_id " +
                " join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        sql += "order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int queryRechargeCount(String name, String addr) {
        String sql = "select count(distinct r.id) as count from recharge r " +
                " join tb_mgr_admin ad on ad.id = r.admin_id " +
                " join agent ag on ag.id = r.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentRecharge(long agentId, int page, int pageSize) {
        String str = "select ag.name as ag_name, r.amount,ad.name as ad_name, r.createDate from recharge r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                "where ag.id = " + agentId +
                " order by r.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentRechargeCount(long agentId) {
        String str = "select ag.name as ag_name, r.amount,ad.name as ad_name, r.createDate from recharge r " +
                "left join tb_mgr_admin ad on ad.id = r.admin_id " +
                "left join agent ag on ag.id = r.agent_id " +
                "where ag.id = " + agentId;
        return this.findBySql(str).size();
    }
}
