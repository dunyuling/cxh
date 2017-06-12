package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentDao;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.util.StringUtil;
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
        String str = "select a.id,a.name,a.userid,a.mobile,a.IDCard,a.corpName,a.licenseImg,a.expireDate,a.money,a.active from agent a " +
                "order by a.id desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public List<Map<String, Object>> getAgents(int page, int pageSize, String mobile, String IDCard, String expire_days) {
        String sql = "select a.id,a.name,a.userid,a.mobile,a.IDCard,a.corpName,a.licenseImg,a.expireDate,a.money,a.active from agent a " +
                "where a.mobile like '%" + mobile + "%' and a.IDCard like '%" + IDCard + "%' ";
        sql += StringUtil.isBlank(expire_days) ? "" : " and TIMESTAMPDIFF(day,curdate(),expireDate) < " + expire_days;
        sql += " order by a.id desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize;
        return this.findBySql(sql);
    }

    public int getAgentsCount(String mobile, String IDCard, String expire_days) {
        String sql = "select count(a.id) as count from agent a " +
                "where a.mobile like '%" + mobile + "%' and a.IDCard like '%" + IDCard + "%'";
        sql += StringUtil.isBlank(expire_days) ? "" : " and TIMESTAMPDIFF(day,curdate(),expireDate) < " + expire_days;
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public Agent getAgentByIDCard(String IDCard) {
        Map<String, Object> map = new HashMap<>();
        map.put("IDCard", IDCard);
        String sql = "from Agent where IDCard =:IDCard";
        return this.findOneByHql(sql, map);
    }

    public Agent getActiveAgentByMobile(String mobile) {
        Map<String, Object> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("active", true);
        String sql = "from Agent where mobile =:mobile and active =:active";
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

    public List<Map<String, Object>> findAllBalanceLow() {
        String sql = "select a.id,a.userid,a.name,a.money,floor(a.money/b.amount) as count from agent a join " +
                " (select a.userid, sum(af.amount) as amount  from agent a " +
                " right join proxy_address pa on a.id = pa.agent_id " +
                " join address_fee af on pa.af_id = af.id group by a.userid) b" +
                " on a.userid=b.userid where a.money < b.amount * 10 and a.active is true";
        return this.findBySql(sql);
    }

    public Map<String, Object> getByMobile(String mobile) {
        String sql = "select mobile ,name,id from agent where mobile = '" + mobile + "' and active is true";
        List<Map<String, Object>> list = this.findBySql(sql);
        return list.isEmpty() ? null : list.get(0);
    }
}