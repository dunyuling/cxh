package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.IAdminDao;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.UserRole;
import com.aifeng.mgr.admin.service.IUserRoleService;
import com.aifeng.util.DateUtil;
import com.aifeng.mgr.admin.dao.IUserRoleDao;
import com.aifeng.ws.wx.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service("userRoleService")
public class UserRoleService extends BaseService<UserRole> implements IUserRoleService {

    @Autowired
    public IAdminDao adminDao;


    @Autowired
    public IUserRoleDao userRoleDao;

    @Override
    @Transactional(readOnly = false)
    public boolean setUserRole(String roleCodes, int userId) {
        Admin user = this.adminDao.findById(userId);
        this.userRoleDao.deleteByWhere(" and user_code='" + user.getCode() + "'");  // 清除该角色权限信息
        String[] codes = roleCodes.split(",");

        if (user != null) {
            for (String code : codes) {
                UserRole ur = new UserRole();
                ur.setRoleCode(code);
                ur.setUserCode(user.getCode());
                ur.setCreateDate(DateUtil.getCurrentDate());
                this.userRoleDao.insert(ur);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public void save(String user_code, String role_code) {
        UserRole userRole = new UserRole();
        userRole.setUserCode(user_code);
        userRole.setRoleCode(role_code);
        userRole.setCreateDate(new Date());
        userRoleDao.insert(userRole);
    }
}