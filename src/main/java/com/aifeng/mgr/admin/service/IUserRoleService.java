package com.aifeng.mgr.admin.service;

import com.aifeng.core.service.IBaseService;
import com.aifeng.mgr.admin.model.UserRole;

public interface IUserRoleService extends IBaseService<UserRole> {

	public boolean setUserRole(String roleIds, int userId);
	
}
