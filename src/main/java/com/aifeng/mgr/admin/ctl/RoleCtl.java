package com.aifeng.mgr.admin.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Action;
import com.aifeng.mgr.admin.model.Role;
import com.aifeng.mgr.admin.service.IActionService;
import com.aifeng.mgr.admin.service.IMenuService;
import com.aifeng.mgr.admin.service.IRoleMenuService;
import com.aifeng.mgr.admin.service.IRoleService;
import com.aifeng.mgr.admin.vo.ZTree;
import com.aifeng.util.Pager;
import com.aifeng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/role")
public class RoleCtl  extends BaseCtl{
	
	Logger log = Logger.getLogger(RoleCtl.class);

	private final IRoleService roleService;
	private final IMenuService menuService;
	private final IActionService actionService;
	private final IRoleMenuService roleMenuService;

	@Autowired
	public RoleCtl(IRoleService roleService, IMenuService menuService, IActionService actionService, IRoleMenuService roleMenuService) {
		this.roleService = roleService;
		this.menuService = menuService;
		this.actionService = actionService;
		this.roleMenuService = roleMenuService;
	}

	@RequestMapping("list")
	public String list(ModelMap mm, @RequestParam(required=false)String menuCode){
		mm.put(MENU_CODE, menuCode);
		return "sys/role/list";	
	}
	
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, String search, ModelMap mm){
		Pager pager = this.roleService.getRoleList(new Pager(page, pageSize));
		JSONObject json = new JSONObject();
		json.put("rows", pager.getResults());
		json.put("total", pager.getTotal());
		return JSONObject.toJSONString(json, features);
	}
	
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Role role;
		if(StringUtil.isNotBlank(id)){
			role = this.roleService.findById(Integer.valueOf(id.trim()));
			mm.put("role", role);
		}
		return "sys/role/" + action;
	}
	
	@SuppressWarnings("UnnecessaryUnboxing")
	@RequestMapping(value="getListMenu", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getListMenu(int roleId, ModelMap mm){
		List<Map<String, Object>> list = this.menuService.getRoleMenu(roleId);
		List<Map<String, Object>> list2 = this.actionService.getAllAction(roleId+"");
		
		List<ZTree> zts = new ArrayList<>();
		ZTree zt;
		for(Map<String, Object> mo : list){
			int menu_id = Integer.valueOf(mo.get("menu_id").toString()).intValue();
			int pId = Integer.valueOf(mo.get("menu_pid").toString()).intValue();
			String name = mo.get("menu_name").toString();
			String code = mo.get("menu_code").toString();
			int set = Integer.valueOf(mo.get("set")+"");
			zt = new ZTree(menu_id, pId , name, code);
			if(pId == 0){
				zt.setParent(true);
			}
			zt.setOpen(true);
			if(set >= 0){
				zt.setChecked(true);
				zt.setVal(set);
			}
			zts.add(zt);
		}
		
		for(Map<String, Object> mo : list2){
			int action_id = Integer.valueOf(mo.get("action_id").toString()).intValue();
			int action_val = Integer.valueOf(mo.get("action_val").toString()).intValue();
			int pId = Integer.valueOf(mo.get("menu_id").toString()).intValue();
			String name = mo.get("action_name").toString();
			
			zt = new ZTree(action_id * 1000, pId , name);
			
			zt.setAction(true);
			
			int set = Integer.valueOf(mo.get("set")+"");
			if(set > 0){
				zt.setChecked(true);
			}
			zt.setVal(action_val);
			zts.add(zt);
		}
		
		
		return JSONObject.toJSONString(zts, features);
	}
	
	
	@RequestMapping(value="getMenuAction", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getMenuAction(String menuCode){
		List<Action> list = this.actionService.getActionByMenuCode(menuCode);
		return JSONObject.toJSONString(list, features);
	}
	@RequestMapping(value="getAllAction", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String getAllAction(String roleId){
		List<Map<String, Object>> list = this.actionService.getAllAction(roleId);
		return JSONObject.toJSONString(list, features);
	}
	
	
	@RequestMapping(value="setPermission", method = RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String setPermission(String acs, int roleId, ModelMap mm){
		JSONArray ja = JSONArray.parseArray(acs);
		
		this.roleMenuService.setPermission(roleId, ja);
		
		return AJAX_SUCCESS; 
	}
	
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Role role, ModelMap mm){
		if(role != null ){
			this.roleService.add(role);
		}
		return AJAX_SUCCESS;
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Role role, ModelMap mm){
		if(role != null ){
			this.roleService.update(role);
		}
		return AJAX_SUCCESS;
	}
	
	
	@RequestMapping("del")
	@ResponseBody
	public String del(int id, ModelMap mm){
		
//		mm.put("superMenuList", this.menuService.getSuperMenuList());
		if(id != 0){
			this.roleService.deleteById(id);
		}
		
		return AJAX_SUCCESS;
	}
	
	
	

}
