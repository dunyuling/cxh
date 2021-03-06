package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IProblemDao;
import com.aifeng.mgr.admin.model.Problem;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class ProblemDao extends BaseDao<Problem> implements IProblemDao {

    public List<Map<String, Object>> getProblem(int page, int pageSize) {
        String sql = "select p.id, p.title, p.description, p.createDate, tma.name as cs_name, a.name as a_name,p.solve,p.solution from problem p " +
                "left join tb_mgr_admin tma on tma.id= p.customerservice_id " +
                "left join agent a on p.agent_id = a.id " +
                "order by solve , createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public List<Map<String, Object>> queryProblem(int page, int pageSize, String agent_name, String cs_name) {
        String sql = "select p.id, p.title, p.description, p.createDate, tma.name as cs_name, a.name as a_name,p.solve,p.solution from problem p " +
                "left join tb_mgr_admin tma on tma.id= p.customerservice_id " +
                "left join agent a on p.agent_id = a.id ";
        sql += agent_name.isEmpty() ? "" : " where a.name like '%" + agent_name + "%'";
        sql += cs_name.isEmpty() ? "" : (sql.contains("where") ? " and tma.name like '%" + cs_name + "%' " :
                " where tma.name like '%" + cs_name + "%' ");
        sql += " order by solve , createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int queryProblemsCount(String agent_name, String cs_name) {
        String sql = "select count(p.id) as count from problem p " +
                "left join tb_mgr_admin tma on tma.id= p.customerservice_id " +
                "left join agent a on p.agent_id = a.id ";
        sql += agent_name.isEmpty() ? "" : " where a.name like '%" + agent_name + "%'";
        sql += cs_name.isEmpty() ? "" : (sql.contains("where") ? " and tma.name like '%" + cs_name + "%' " :
                " where tma.name like '%" + cs_name + "%' ");
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getCustomerServiceProblems(int id, int page, int pageSize) {
        String str = "select p.id, p.title,p.createDate, p.description,p.solve,p.solution, tma.name from problem p " +
                "join tb_mgr_admin tma on p.customerService_id = tma.id " +
                "where p.customerService_id = '" + id + "'" +
                " order by solve , createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getCustomerServiceProblemCount(int id) {
        String str = "select count(p.id) as count from problem p " +
                "join customer_service cs on p.customerService_id = cs.id " +
                "where p.customerService_id = '" + id + "'";
        return Integer.parseInt(this.findBySql(str).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentProblems(long id, int page, int pageSize) {
        String str = "select p.id, p.title, p.createDate, p.description,p.solve,p.solution, a.name from problem p " +
                "join agent a on p.agent_id = a.id " +
                "where p.agent_id = '" + id + "'" +
                " order by solve , createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentProblemCount(long id) {
        String str = "select count(p.id) as count from problem p " +
                "join agent a on p.agent_id = a.id " +
                "where p.agent_id = '" + id + "'";
        return Integer.parseInt(this.findBySql(str).get(0).get("count").toString());
    }

    public Map<String, Object> getSingleProblem(long id) {
        String str = "select p.id, p.title, p.description from problem p " +
                "where p.id = " + id;
        return this.findBySql(str).get(0);
    }
}