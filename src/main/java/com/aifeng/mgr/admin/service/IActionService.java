package com.aifeng.mgr.admin.service;

import java.util.List;
import java.util.Map;

import com.aifeng.core.service.IBaseService;
import com.aifeng.mgr.admin.model.Action;

public interface IActionService extends IBaseService<Action>{

	public List<Action> getActionByMenuCode(String menuCode);
	
	/**
	 * 获取所有action
	 * @Title: getAllAction 
	 * @Description: TODO
	 * @return
	 * @return: List<Action>
	 */
	public List<Action> getAllAction();
	
	public Action addAction(Action ac);
	
	public List<Map<String, Object>> getAllAction2();
	
	List<Map<String, Object>> getAllAction(String roleId);
	
}
