package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class ProxyAddressDao extends BaseDao<ProxyAddress> implements IProxyAddressDao {

    public ProxyAddress getByAgentIdAndAfId(long agent_id, long af_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("agent_id", agent_id);
        map.put("af_id", af_id);
        map.put("proxyStatus1", ProxyStatus.Authored);
        map.put("proxyStatus2", ProxyStatus.Applying);
        String sql = "from ProxyAddress where agent_id =:agent_id and af_id =:af_id and (proxyStatus =:proxyStatus1 or proxyStatus =:proxyStatus2)";
        return this.findOneByHql(sql, map);
    }

    public ProxyAddress getByAfId(long af_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("af_id", af_id);
        map.put("proxyStatus", ProxyStatus.Authored);
        String sql = "from ProxyAddress where af_id =:af_id and proxyStatus =:proxyStatus";
        return this.findOneByHql(sql, map);
    }
}
