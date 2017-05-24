package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAdminDao;
import com.aifeng.mgr.admin.model.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class AdminDao extends BaseDao<Admin> implements IAdminDao {

    public List<Map<String, Object>> getCustomerService(int page, int pageSize) {
        String sql = "select tmr.name as tmr_name,tma.account, tma.id, tma.name,tma.phone,tma.addr,tma.sex from tb_mgr_admin tma " +
                "join tb_mgr_user_role tmur on tmur.user_code = tma.code " +
                "join tb_mgr_role tmr on tmur.role_code = tmr.code " +
                "where tmr.name='客服' limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        System.out.println("sql: " + sql);
        return this.findBySql(sql);
    }

    public int getCustomerServiceTotal() {
        String sql = "select count(tma.id) from tb_mgr_admin tma " +
                "left join tb_mgr_user_role tmur on tmur.user_code = tma.code " +
                "join tb_mgr_role tmr on tmur.role_code = tmr.code " +
                "where tmr.name='客服';";
        System.out.println("sql1: " + sql);
        return this.findBySql(sql).size();
    }

    public Map<String, Object> getSingleCustomerService(long id) {
        String sql = "select tma.id, tma.name,tma.phone,tma.addr,tma.sex from tb_mgr_admin tma " +
                "where tma.id = " + id + ";";
        return this.findBySql(sql).get(0);
    }

    public List<Map<String, Object>> getUsedProvinces() {
        String sql = "select tma.addr from tb_mgr_admin tma " +
                "left join tb_mgr_user_role tmur on tmur.user_code = tma.code " +
                "left join tb_mgr_role tmr on tmr.code = tmur.role_code ";
        return this.findBySql(sql);
    }
}
