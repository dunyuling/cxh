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

    public List<Map<String, Object>> getFds(int page, int pageSize, String addr) {
        String sql = "select distinct fd.id, ag.name, m.content,fd.amount,fd.createDate from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += "order by fd.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int getFdsCount(String addr) {
        String sql = "select count(distinct fd.id) as count from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryFds(int page, int pageSize, String name, String addr) {
        String sql = "select distinct fd.id, ag.name, m.content,fd.amount,fd.createDate from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%' ";
        sql += "order by fd.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int queryFdsCount(String name, String addr) {
        String sql = "select count(distinct fd.id) as count from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentFds(long agentId, int page, int pageSize) {
        String str = "select ag.name, m.content,fd.amount from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " where ag.id = " + agentId +
                " order by fd.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentFdsCount(long agentId) {
        String str = "select ag.name, m.content,fd.amount from fee_deduction fd " +
                "left join message m on m.id = fd.message_id " +
                "left join agent ag on ag.id = fd.agent_id " +
                " where ag.id = " + agentId;
        return this.findBySql(str).size();
    }
}
