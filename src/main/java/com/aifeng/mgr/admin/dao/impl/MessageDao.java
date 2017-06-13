package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMessageDao;
import com.aifeng.mgr.admin.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class MessageDao extends BaseDao<Message> implements IMessageDao {

    public List<Map<String, Object>> getMessage(int page, int pageSize, String addr) {
        String sql = "select m.id,ag.name, m.content, a.province,a.city,a.area from message m " +
                "join proxy_address pa on m.proxyAddress_id = pa.id " +
                "join address_fee af on pa.af_id = af.id " +
                "join agent ag on pa.agent_id = ag.id " +
                "join address a on af.address_id = a.id ";
        sql += addr == null ? "" : " where a.province in (" + addr + ")";
        sql += " order by m.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int getMessageCount(String addr) {
        String sql = "select count(m.id) as count from message m " +
                "join proxy_address pa on m.proxyAddress_id = pa.id " +
                "join address_fee af on pa.af_id = af.id " +
                "join agent ag on pa.agent_id = ag.id " +
                "join address a on af.address_id = a.id ";
        sql += addr == null ? "" : " where a.province in (" + addr + ")";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryMessage(int page, int pageSize, String name, String addr) {
        String sql = "select m.id,ag.name, m.content, a.province,a.city,a.area from message m " +
                "join proxy_address pa on m.proxyAddress_id = pa.id " +
                "join address_fee af on pa.af_id = af.id " +
                "join agent ag on pa.agent_id = ag.id " +
                "join address a on af.address_id = a.id ";
        sql += addr == null ? "" : " where a.province in (" + addr + ")";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        sql += " order by m.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int queryMessageCount(String name, String addr) {
        String sql = "select count(m.id) as count from message m " +
                "join proxy_address pa on m.proxyAddress_id = pa.id " +
                "join address_fee af on pa.af_id = af.id " +
                "join agent ag on pa.agent_id = ag.id " +
                "join address a on af.address_id = a.id ";
        sql += addr == null ? "" : " where a.province in (" + addr + ")";
        sql += sql.contains("where") ? " and " : " where ";
        sql += " ag.name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentMessage(long agentId, int page, int pageSize) {
        String str = "select m.id,ag.name, m.content, a.province,a.city,a.area from message m " +
                "left join proxy_address pa on m.proxyAddress_id = pa.id " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "left join address a on af.address_id = a.id " +
                "where ag.id = " + agentId +
                " order by m.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public int getAgentMessageCount(long agentId) {
        String str = "select m.id,ag.name, m.content, a.province,a.city,a.area from message m " +
                "left join proxy_address pa on m.proxyAddress_id = pa.id " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "left join address a on af.address_id = a.id " +
                "where ag.id = " + agentId;
        return this.findBySql(str).size();
    }
}