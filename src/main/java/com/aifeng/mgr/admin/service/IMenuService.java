package com.aifeng.mgr.admin.service;

import java.util.List;
import java.util.Map;

import com.aifeng.util.Pager;
import com.aifeng.core.service.IBaseService;
import com.aifeng.mgr.admin.model.Menu;

public interface IMenuService extends IBaseService<Menu> {

	public  List<Menu> getMenuList();
	
	public Pager getSuperMenuList(Pager page);
	
	public List<Menu> getSuperMenuList();
	
	public List<Menu> getSecMenuList();
	
	public List<Map<String,Object>> getRoleMenu(int roleId);

	Menu addMenu(Menu menu);
	
}
