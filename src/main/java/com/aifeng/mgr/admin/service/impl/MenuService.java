package com.aifeng.mgr.admin.service.impl;

import java.util.List;
import java.util.Map;

import com.aifeng.mgr.admin.dao.IMenuDao;
import com.aifeng.mgr.admin.service.IMenuService;
import com.aifeng.util.Pager;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("menuService")
public class MenuService extends BaseService<Menu> implements IMenuService {

	@Autowired
	public IMenuDao menuDao;
	
//	@Override
//	public Pager getMenuList(Pager page) {
//		// TODO Auto-generated method stub
//		return this.findPageByHql("from Menu where display = true order by code, weight ", page, null);
//	}
	
	@Override
	@Transactional(readOnly = false)
	public Menu addMenu(Menu menu){
		if(menu.getPid() == 0){
			menu.setCode(this.menuDao.getNewSuperMenuCode());
		}
		else{
			menu.setCode(this.menuDao.getNewSecMenuCode(menu));
		}
		return this.add(menu);
	}
	
	@Override
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		return this.menuDao.getMenuList();
//		return this.findPageByHql("from Menu where display = true order by code, weight ", page, null);
	}


	@Override
	public Pager getSuperMenuList(Pager page){
		return this.menuDao.getSuperMenuList(page);
//		return (List<Menu>) this.findByHql("from Menu where pid = 0 and display = true order by weight", null);
	}
	
	
	@Override
	public List<Menu> getSuperMenuList() {
		// TODO Auto-generated method stub
		return this.menuDao.getSuperMenuList();
	}


	@Override
	public List<Menu> getSecMenuList(){
		return this.menuDao.getSecMenuList();
	}
	
	
	public List<Map<String,Object>>  getRoleMenu(int roleId){
		return this.menuDao.getRoleMenu(roleId);
		
	}
	
	
	
}
