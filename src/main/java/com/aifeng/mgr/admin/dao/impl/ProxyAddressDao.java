package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.constants.ProxyStatus;
import com.aifeng.mgr.admin.dao.IProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProxyAddressDao extends BaseDao<ProxyAddress> implements IProxyAddressDao {

    public ProxyAddress getByAddressFeeId(long af_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("af_id", af_id);
        params.put("proxyStatus1", ProxyStatus.AUTHORED);
        params.put("proxyStatus2", ProxyStatus.APPLYING);
        String sql = "from ProxyAddress where af_id =:af_id and (proxyStatus =:proxyStatus1 or proxyStatus =:proxyStatus2)";
        return this.findOneByHql(sql, params);
    }

    public List<Map<String, Object>> getProxyAddress(int page, int pageSize, String addr) {
        String sql = "select pa.id, ag.name,ag.corpName,  a.province ,a.city, a.area,pa.proxyStatus,pa.createDate,pa.updateDate from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.proxyStatus != '" + ProxyStatus.CANCELD + "'";
        sql += addr == null ? "" : " and a.province in (" + addr + ")";
        sql += " order by pa.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int getProxyAddressCount(String addr) {
        String sql = "select count(pa.id) as count from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.proxyStatus != '" + ProxyStatus.CANCELD + "'";
        sql += addr == null ? "" : " and a.province in (" + addr + ")";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryProxyAddress(int page, int pageSize, String name, String addr) {
        String sql = "select pa.id, ag.name, ag.corpName, a.province ,a.city, a.area,pa.proxyStatus,pa.createDate,pa.updateDate from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.proxyStatus != '" + ProxyStatus.CANCELD + "' and name like '%" + name + "%'";
        sql += addr == null ? "" : " and a.province in (" + addr + ")";
        sql += " order by pa.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int queryProxyAddressCount(String name, String addr) {
        String sql = "select count(pa.id) as count from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.proxyStatus != '" + ProxyStatus.CANCELD + "' and name like '%" + name + "%'";
        sql += addr == null ? "" : " and a.province in (" + addr + ")";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getAgentProxyAddress(long agent_id, int page, int pageSize) {
        String sql = "select pa.id, ag.name, ag.corpName, a.province ,a.city, a.area,pa.proxyStatus,pa.createDate,pa.updateDate from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where ag.id = " + agent_id + " and pa.proxyStatus != '" + ProxyStatus.CANCELD + "'" +
                " order by pa.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int getAgentCount(long agent_id) {
        String sql = "select count(pa.id) as count from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where ag.id = " + agent_id + " and pa.proxyStatus != '" + ProxyStatus.CANCELD + "'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryAgentProxyAddress(long agent_id, int page, int pageSize, String name) {
        String sql = "select pa.id, ag.name,ag.corpName,  a.province ,a.city, a.area,pa.proxyStatus,pa.createDate,pa.updateDate from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where ag.id = " + agent_id + " and pa.proxyStatus != '" + ProxyStatus.CANCELD + "' and name like '%" + name + "%'" +
                " order by pa.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public int queryAgentCount(long agent_id, String name) {
        String sql = "select count(pa.id) as count from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where ag.id = " + agent_id + " and pa.proxyStatus != '" + ProxyStatus.CANCELD + "' and name like '%" + name + "%'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public Map<String, Object> getSingleProxyAddress(long id) {
        String sql = "select pa.id, ag.name,ag.corpName,  a.province ,a.city, a.area,pa.proxyStatus, " +
                "ag.mobile,ag.IDCard,ag.corpName,ag.licenseImg,ag.expireDate from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.id =" + id;


        return this.findBySql(sql).get(0);
    }

    public void auditCancelProxyAddress(long agent_id) {
        String sql = "update proxy_address pa set pa.proxyStatus = '" + ProxyStatus.CANCELD + "' " +
                " where pa.agent_id = " + agent_id;
        this.sqlUpdate(sql, null);
    }
}