package com.aifeng.mgr.admin.dao.impl;

import java.util.List;
import java.util.Map;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMenuDao;
import com.aifeng.util.Pager;
import com.aifeng.mgr.admin.model.Menu;
import org.springframework.stereotype.Repository;

@Repository
public class MenuDao extends BaseDao<Menu> implements IMenuDao {

	public String getNewSuperMenuCode(){
		StringBuffer sb = new StringBuffer("SELECT MAX(CONVERT(CODE, UNSIGNED )) AS max_code FROM tb_mgr_menu WHERE pid = 0 ");
		Map<String, Object> map = ((List<Map<String,Object>>)this.findBySql(sb.toString())).get(0);
		return Integer.valueOf(map.get("max_code").toString()) + 1 + "";
	}
	
	
	public String getNewSecMenuCode(Menu m){
//		int ret ;
		StringBuffer sb = new StringBuffer("select MAX(CONVERT(CODE, UNSIGNED )) as max_code from tb_mgr_menu where pid = " + m.getPid());
		List<Map<String, Object>> list = (List<Map<String, Object>>)this.findBySql(sb.toString());
		Menu superMenu = this.findById(m.getPid());
		int maxCode = 0;
		if(list.size() > 0){
			Map<String, Object> map = list.get(0);
			if(map.get("max_code") != null){
				maxCode = Integer.valueOf(map.get("max_code") + "") +  1;
			}
			else{
				maxCode = Integer.valueOf(superMenu.getCode()) * 10 + 1;
			}
		}
		return maxCode + "";
	}


	@Override
	public Pager getSuperMenuList(Pager page) {
		
		// TODO Auto-generated method stub
		return this.findPageByHql("from Menu where pid = 0 and display = true order by weight", page, null);
	}


	@Override
	public List<Menu> getSuperMenuList() {
		// TODO Auto-generated method stub
		return (List<Menu>) this.findByHql("from Menu where pid = 0 and display = true order by weight", null);
	}


	@Override
	public List<Menu> getSecMenuList() {
		// TODO Auto-generated method stub
		return (List<Menu>) this.findByHql("from Menu where pid <> 0 and display = true order by weight ", null);
	}


	@Override
	public List<Map<String, Object>> getRoleMenu(int roleId) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer(" SELECT tmm.id AS menu_id, tmm.code as menu_code,tmm.pid as menu_pid, tmm.name AS menu_name, IFNULL((SELECT tmrm.action_val FROM tb_mgr_role_menu tmrm LEFT JOIN  tb_mgr_role tmr ON tmr.code = tmrm.role_code WHERE tmr.id =" + roleId + " AND tmrm.menu_code = tmm.code),-1) AS `set` FROM tb_mgr_menu tmm  order by tmm.code, tmm.weight ");
		return (List<Map<String, Object>>) this.findBySql(sb.toString());
	}


	@Override
	public List<Menu> getMenuList() {
		// TODO Auto-generated method stub
		return (List<Menu>) this.findByHql("from Menu where display = true order by code, weight ", null);
	}
}
