package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.constants.ProxyStatus;
import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IProxyAddressDao;
import com.aifeng.mgr.admin.model.ProxyAddress;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class ProxyAddressDao extends BaseDao<ProxyAddress> implements IProxyAddressDao {

    public ProxyAddress getByAgentIdAndAfId(long af_id) {
        Map<String, Object> map = new HashMap<>();
        map.put("af_id", af_id);
        map.put("proxyStatus1", ProxyStatus.AUTHORED);
        map.put("proxyStatus2", ProxyStatus.APPLYING);
        String sql = "from ProxyAddress where af_id =:af_id and (proxyStatus =:proxyStatus1 or proxyStatus =:proxyStatus2)";
        return this.findOneByHql(sql, map);
    }
}
