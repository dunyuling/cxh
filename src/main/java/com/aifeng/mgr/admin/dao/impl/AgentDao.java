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
                "order by a.id desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public Agent getAgentByIDCard(String IDCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("IDCard", IDCard);
        String sql = "from Agent where IDCard =:IDCard";
        return this.findOneByHql(sql, map);
    }

    public Agent getAgentByMobile(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        String sql = "from Agent where mobile =:mobile";
        return this.findOneByHql(sql, map);
    }

    public Agent getAgentByUserId(String userid) {
        Map<String, Object> map = new HashMap<>();
        map.put("userid", userid);
        String sql = " from Agent where userid =:userid";
        return this.findOneByHql(sql, map);
    }

    public Map<String, Object> getAgentSubmitMsgFromWx(String user_id) {
        String sql = "select a.id,a.IDCard,a.mobile,a.corpName,a.name,date_format(a.expireDate,'%Y-%m-%d') as expireDate,a.licenseImg,addr.province,addr.city,addr.area from agent a " +
                "left join proxy_address pa on a.id = pa.agent_id " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join address addr on af.address_id = addr.id " +
                "where a.userid = '" + user_id + "'";
        System.out.println("sql: " + sql + " ==============");
        List<Map<String, Object>> list = this.findBySql(sql);
        return list.isEmpty() ? null : list.get(0);
    }

    public List<Agent> findAll() {
        String hql = " from Agent ";
        return (List<Agent>) this.findByHql(hql, null);
    }
}
