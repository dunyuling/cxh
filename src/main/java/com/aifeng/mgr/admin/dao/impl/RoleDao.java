package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IRoleDao;
import com.aifeng.mgr.admin.model.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleDao extends BaseDao<Role> implements IRoleDao {


    public Role getByRoleName(String role_name) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", role_name);
        return this.findOneByHql("from Role where name =:name", params);
    }
}
