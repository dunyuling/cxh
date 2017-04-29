package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentDao;
import com.aifeng.mgr.admin.model.Agent;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class AgentDao extends BaseDao<Agent> implements IAgentDao {

    public Agent getAgentByIDCard(String IDCard) {
        Map<String,Object> map = new HashMap<>();
        map.put("IDCard", IDCard);
        String sql = "from Agent where IDCard =:IDCard";
        return this.findOneByHql(sql,map);
    }
}
