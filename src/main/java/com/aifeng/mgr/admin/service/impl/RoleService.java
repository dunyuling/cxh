package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.util.Pager;
import com.aifeng.mgr.admin.model.Role;
import com.aifeng.mgr.admin.service.IRoleService;
import org.springframework.stereotype.Service;

@Service("roleService")
public class RoleService extends BaseService<Role> implements IRoleService{
	
	public Pager getRoleList(Pager page){
		return this.findPageByHql("from Role ", page, null);
	}
	
	

}
