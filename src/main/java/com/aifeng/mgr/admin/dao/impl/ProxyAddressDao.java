package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class ProxyAddressDao extends BaseDao<ProxyAddress> implements IProxyAddressDao {

    public ProxyAddress getByAgentIdAndAfId(long af_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("af_id", af_id);
        params.put("proxyStatus1", ProxyStatus.AUTHORED);
        params.put("proxyStatus2", ProxyStatus.APPLYING);
        String sql = "from ProxyAddress where af_id =:af_id and (proxyStatus =:proxyStatus1 or proxyStatus =:proxyStatus2)";
        return this.findOneByHql(sql, params);
    }

    public List<Map<String, Object>> getProxyAddress(int page, int pageSize) {
        String sql = "select pa.id, ag.name,  a.province ,a.city, a.area,pa.proxyStatus from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(sql);
    }

    public Map<String, Object> getSingleProxyAddress(long id) {
        String sql = "select pa.id, ag.name,  a.province ,a.city, a.area,pa.proxyStatus from proxy_address pa " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address a on af.address_id = a.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "where pa.id =" + id;
        return this.findBySql(sql).get(0);
    }
}