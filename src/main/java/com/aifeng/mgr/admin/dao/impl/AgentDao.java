package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentDao;
import com.aifeng.mgr.admin.model.Agent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class AgentDao extends BaseDao<Agent> implements IAgentDao {

    public List<Map<String, Object>> getAgents(int page, int pageSize) {
        String str = "select a.id,a.name,a.userid,a.mobile,a.IDCard,a.corpName,a.licenseImg,a.expireDate,a.money from agent a " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public Agent getAgentByIDCard(String IDCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("IDCard", IDCard);
        String sql = "from Agent where IDCard =:IDCard";
        return this.findOneByHql(sql, map);
    }

    public Agent getAgentByUserId(String userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        String sql = " from Agent where userid =:userid";
        return this.findOneByHql(sql, map);
    }

    /*public Map<String,Object> getAgentByUserId2(String user_id) {
        String sql = "select id,name from agent where userid = '" + user_id + "'";
        return this.findBySql(sql).get(0);
    }*/
}
