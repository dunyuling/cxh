package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMemberDao;
import com.aifeng.mgr.admin.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class MemberDao extends BaseDao<Member> implements IMemberDao {

    public List<Map<String, Object>> getMember(int page, int pageSize) {
        String str = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area from member m " +
                "left join address a on m.address_id = a.id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public Map<String, Object> getSingleProxyAddress(long id) {
        String str = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area from member m " +
                "left join address a on m.address_id = a.id where m.id = " + id;
        return this.findBySql(str).get(0);
    }

    public List<Map<String, Object>> getTotal(String user_id) {
        String sql = "select m.id, m.name,m.mobile,m.createDate from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.user_id = " + user_id;
        return this.findBySql(sql);
    }

    public List<Map<String, Object>> getToday(String user_id) {
        String sql = "select m.id, m.name,m.mobile,m.createDate from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.user_id = " + user_id;
        return this.findBySql(sql);
    }

    public List<Map<String, Object>> getToday(String user_id, boolean visit) {
        String sql = "select m.id, m.name,m.mobile,m.createDate from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where am.visit is " + visit + " and ag.user_id = " + user_id
                ;
        return this.findBySql(sql);
    }
}