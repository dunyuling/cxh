package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.RoleDao;
import com.aifeng.util.Pager;
import com.aifeng.mgr.admin.model.Role;
import com.aifeng.mgr.admin.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends BaseService<Role> implements IRoleService {

    private final RoleDao roleDao;

    @Autowired
    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public Pager getRoleList(Pager page) {
        return this.findPageByHql("from Role ", page, null);
    }

    public Role getRoleByName(String role_name) {
        return roleDao.getByRoleName(role_name);
    }


}
