package com.aifeng.mgr.admin.service.impl;

import com.aifeng.mgr.admin.dao.IRoleMenuDao;
import com.aifeng.mgr.admin.model.RoleMenu;
import com.aifeng.mgr.admin.service.IRoleMenuService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.IRoleDao;
import com.aifeng.mgr.admin.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("roleMenuService")
public class RoleMenuService extends BaseService<RoleMenu> implements IRoleMenuService {

	@Autowired
	public IRoleDao roleDao;
	
	@Autowired
	public IRoleMenuDao roleMenuDao;
	
	
	@Override
	@Transactional(readOnly=false)
	public boolean setPermission(int roleId, JSONArray ja){
		Role role = this.roleDao.findById(roleId);
		this.roleMenuDao.deleteByWhere(" and role_code='" + role.getCode() + "'");  // 清除该角色权限信息
		if(role != null) {
			RoleMenu rm = null;
			for(int i = 0; i < ja.size(); i++){
				rm = new RoleMenu();
				JSONObject jo = ja.getJSONObject(i);
				rm.setMenuCode(ja.getJSONObject(i).getString("menu_code"));
				rm.setActionVal(Integer.valueOf(jo.getIntValue("action_val")));
				rm.setRoleCode(role.getCode());
				this.roleMenuDao.insert(rm);
			}
			return true;
		}
		return false;
	}
	
}
