package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentMessageDao;
import com.aifeng.mgr.admin.model.AgentMessage;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class AgentMessageDao extends BaseDao<AgentMessage> implements IAgentMessageDao {

    public List<AgentMessage> getAllNeedRepeat(int totalTimes) {
        Map<String, Object> params = new HashMap<>();
        params.put("totalTimes", totalTimes);
        String sql = "from AgentMessage where times <:totalTimes";
        return (List<AgentMessage>) this.findByHql(sql, params);
    }

    public List<Map<String, Object>> getAgentMessage(int page, int pageSize, String addr) {
        String sql = "select distinct am.id,ag.name,m.content,am.times,am.visit,am.visitDate,am.updateDate from agent_message am " +
                " join agent ag on am.agent_id = ag.id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id " +
                " join message m on am.message_id = m.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += "order by am.visitDate desc,am.updateDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int getAgentMessageCount(String addr) {
        String sql = "select count(distinct am.id) as count from agent_message am " +
                " join agent ag on am.agent_id = ag.id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id " +
                " join message m on am.message_id = m.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryAgentMessage(int page, int pageSize, String name, String addr) {
        String sql = "select distinct am.id,ag.name,m.content,am.times,am.visit,am.visitDate,am.updateDate from agent_message am " +
                " join agent ag on am.agent_id = ag.id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id " +
                " join message m on am.message_id = m.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += "name like '%" + name + "%'";
        sql += " order by am.visitDate desc,am.updateDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int queryAgentMessageCount(String name, String addr) {
        String sql = "select count(distinct am.id) as count from agent_message am " +
                " join agent ag on am.agent_id = ag.id " +
                " left join proxy_address pa on ag.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id " +
                " join address addr on af.address_id = addr.id " +
                " join message m on am.message_id = m.id ";
        sql += addr == null ? "" : " where addr.province in (" + addr + ") ";
        sql += sql.contains("where") ? " and " : " where ";
        sql += "name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentMessage(long agentId, int page, int pageSize) {
        String str = "select am.id,a.name,m.content,am.times,am.visit,am.visitDate from agent_message am " +
                "left join agent a on am.agent_id = a.id " +
                "left join message m on am.message_id = m.id " +
                "where a.id = " + agentId +
                " order by am.visitDate desc,am.updateDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentMessageCount(long agentId) {
        String str = "select am.id,a.name,m.content,am.times,am.visit,am.visitDate from agent_message am " +
                "left join agent a on am.agent_id = a.id " +
                "left join message m on am.message_id = m.id " +
                "where a.id = " + agentId;
        return this.findBySql(str).size();
    }

    public List<Map<String, Object>> getUnVisit() {
        String sql = "select am.id ,am.agent_id ,am.member_id,m.address_id from agent_message am " +
                " join member m on am.member_id = m.id" +
                " where am.visit is false ";
        return this.findBySql(sql);
    }

    public AgentMessage findByMemberId(long member_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("member_id", member_id);
        String sql = "from AgentMessage where member_id=:member_id";
        return this.findOneByHql(sql, params);
    }
}