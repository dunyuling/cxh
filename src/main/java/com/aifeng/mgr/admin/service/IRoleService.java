package com.aifeng.mgr.admin.service;

import com.aifeng.core.service.IBaseService;
import com.aifeng.util.Pager;
import com.aifeng.mgr.admin.model.Role;

public interface IRoleService extends IBaseService<Role> {

	public Pager getRoleList(Pager page);
	
}
