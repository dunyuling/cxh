package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMemberDao;
import com.aifeng.mgr.admin.model.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class MemberDao extends BaseDao<Member> implements IMemberDao {

    public List<Map<String, Object>> getMember(int page, int pageSize, String addr) {
        String sql;
        if (addr == null) {
            sql = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area,m.createDate,tma.name as tma_name from member m " +
                    "left join address a on m.address_id = a.id " +
                    "left join tb_mgr_admin tma on tma.id = m.user_id " +
                    "order by m.createDate desc " +
                    "limit " + pageSize + " offset " + (page - 1) * pageSize;
        } else {
            sql = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area,m.createDate,tma.name as tma_name from member m " +
                    "left join address a on m.address_id = a.id " +
                    "left join tb_mgr_admin tma on tma.id = m.user_id " +
                    " where a.province in (" + addr +
                    " ) order by m.createDate desc " +
                    "limit " + pageSize + " offset " + (page - 1) * pageSize;
        }
        System.out.println("sql: " + sql);
        return this.findBySql(sql);
    }

    public int getCount(String addr) {
        String sql;
        if (addr == null) {
            sql = "select count(m.id) as count from member m " +
                    "left join address a on m.address_id = a.id ";
        } else {
            sql = "select count(m.id) as count from member m " +
                    "left join address a on m.address_id = a.id " +
                    " where a.province in (" + addr + " )";
        }
        System.out.println("sql: " + sql);
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryMember(int page, int pageSize, String name, String mobile, String addr) {
        String sql;
        if (addr == null) {
            sql = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area,m.createDate,tma.name as tma_name from member m " +
                    "left join address a on m.address_id = a.id " +
                    "left join tb_mgr_admin tma on tma.id = m.user_id " +
                    " where m.name like '%" + name + "%' and m.mobile like '%" + mobile + "%'" +
                    " order by m.createDate desc " +
                    "limit " + pageSize + " offset " + (page - 1) * pageSize;
        } else {
            sql = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area,m.createDate,tma.name as tma_name from member m " +
                    "left join address a on m.address_id = a.id " +
                    "left join tb_mgr_admin tma on tma.id = m.user_id " +
                    " where m.name like '%" + name + "%' and m.mobile like '%" + mobile + "%'" +
                    " and a.province in (" + addr + " ) order by m.createDate desc " +
                    "limit " + pageSize + " offset " + (page - 1) * pageSize;
        }
        System.out.println("sql: " + sql);
        return this.findBySql(sql);
    }

    public int queryCount(String name, String mobile, String addr) {
        String sql;
        if (addr == null) {
            sql = "select count(m.id) as count from member m " +
                    "left join address a on m.address_id = a.id " +
                    " where m.name like '%" + name + "%' and m.mobile like '%" + mobile + "%'";
        } else {
            sql = "select count(m.id) as count from member m " +
                    "left join address a on m.address_id = a.id " +
                    " where m.name like '%" + name + "%' and m.mobile like '%" + mobile + "%'" +
                    " and a.province in (" + addr + " )";
        }
        System.out.println("sql: " + sql);
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public Map<String, Object> getSingleProxyAddress(long id) {
        String str = "select m.id,m.name,m.mobile,m.type,m.status, a.province,a.city,a.area from member m " +
                "left join address a on m.address_id = a.id where m.id = " + id;
        return this.findBySql(str).get(0);
    }

    public List<Map<String, Object>> getTotalMember(String user_id) {
        String sql = "select m.id, m.name,m.mobile,m.createDate,m.type,am.visit from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.userid = '" + user_id + "'" +
                " order by m.createDate desc ";
        return this.findBySql(sql);
    }

    public int getTotalCount(String user_id) {
        String sql = "select count(m.id) as count from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.userid = '" + user_id + "'";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> queryMember(String user_id, String dateStr) {
        String sql = "select m.id, m.name,m.mobile,m.createDate,m.type,am.visit from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.userid = '" + user_id + "' " +
                " and date_format(createDate,'%Y-%m-%d') = str_to_date('" + dateStr + "','%Y-%m-%d')" +
                " order by m.createDate desc ";
        return this.findBySql(sql);
    }

    public int queryCount(String user_id, String dateStr) {
        String sql = "select count(m.id) as count from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.userid = '" + user_id + "'" +
                " and date_format(createDate,'%Y-%m-%d') = str_to_date('" + dateStr + "','%Y-%m-%d')";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getTodayMember(String user_id) {
        String sql = "select m.id, m.name,m.mobile,m.type,m.createDate,am.visit from agent_message am" +
                " left join member m on am.member_id = m.id " +
                " left join agent ag on am.agent_id = ag.id " +
                " where ag.userid='" + user_id + "'" +
                " and date_format(am.updateDate,'%Y-%m-%d') = curdate() " +
                " order by m.createDate desc";
        System.out.println("111sql: " + sql);
        return this.findBySql(sql);
    }

    public int getTodayCount(String user_id) {
        String sql = "select count(m.id) as count from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where ag.userid = '" + user_id +
                "' and date_format(am.updateDate,'%Y-%m-%d') = curdate()";
        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public List<Map<String, Object>> getTodayMember(String user_id, boolean visit) {
        String sql = "select m.id, m.name,m.mobile,m.type, m.createDate,am.visit from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where am.visit is " + visit + " and ag.userid = '" + user_id +
                "' and date_format(am.updateDate,'%Y-%m-%d') = curdate()" +
                " order by m.createDate desc ";
        return this.findBySql(sql);
    }

    public int getTodayCount(String user_id, boolean visit) {
        String sql = "select count(m.id) as count from agent_message am " +
                "left join member m on am.member_id = m.id " +
                "left join agent ag on am.agent_id = ag.id " +
                "where am.visit is " + visit + " and ag.userid = '" + user_id +
                "' and date_format(am.updateDate,'%Y-%m-%d') = curdate()";

        return Integer.parseInt(this.findBySql(sql).get(0).get("count").toString());
    }

    public Map<String, Object> getDetail(long member_id) {
        String sql = "select m.id, m.name,m.mobile,m.type, m.createDate,am.visit,m.type, addr.province,addr.city,addr.area ,am.visitDate from agent_message am " +
                "join member m on am.member_id = m.id " +
                "join address addr on addr.id = m.address_id " +
                "where m.id = '" + member_id + "'";
        return this.findBySql(sql).get(0);
    }
}