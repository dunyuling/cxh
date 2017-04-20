package com.aifeng.mgr.admin.service;

import com.aifeng.mgr.admin.model.RoleMenu;
import com.aifeng.core.service.IBaseService;

import com.alibaba.fastjson.JSONArray;

public interface IRoleMenuService extends IBaseService<RoleMenu> {

	public boolean setPermission(int roleId, JSONArray ja);
	
}
