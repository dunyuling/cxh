package com.aifeng.mgr.admin.dao;

import java.util.List;
import java.util.Map;

import com.aifeng.core.dao.IBaseDao;
import com.aifeng.mgr.admin.model.Menu;
import com.aifeng.util.Pager;

public interface IMenuDao extends IBaseDao<Menu> {

	public String getNewSuperMenuCode();
	
	public String getNewSecMenuCode(Menu m);
	
	public Pager getSuperMenuList(Pager page);
	
	public List<Menu> getSuperMenuList() ;
	
	public List<Menu> getSecMenuList();
	
	public List<Map<String,Object>>  getRoleMenu(int roleId);
	
	public List<Menu> getMenuList();
	
}
