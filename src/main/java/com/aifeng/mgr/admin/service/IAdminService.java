package com.aifeng.mgr.admin.service;

import java.util.List;
import java.util.Map;

import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.util.Pager;
import com.aifeng.core.service.IBaseService;

public interface IAdminService extends IBaseService<Admin>{

	/**
	 * 根据账号查询用户信息
	 * @param account
	 * @return
	 */
	public Admin findByAccount(String account);
	
	public List<Map<String,Object>> getPermissions(String userCode);
	
	
	public Pager getList(Pager page);
	
	
	public boolean initPwd(int id);
	
	public List<Map<String, Object>> getRoleList(int userId);
	
	
}
