package com.aifeng.mgr.admin.ctl;

import com.aifeng.mgr.admin.model.Action;
import com.aifeng.mgr.admin.service.IActionService;
import com.aifeng.util.DateUtil;
import org.apache.log4j.Logger;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/action")
public class ActionCtl extends BaseCtl {
	
	Logger log = Logger.getLogger(ActionCtl.class);

	
	@Autowired
    IActionService actionService;
	
	
	@RequestMapping("getMenuActionList")	
	@ResponseBody
	public String getMenuActionList(String menuCode, ModelMap mm){
//		List<Map<String, Object>> list = this.actionService.getActionByMenuCode(menuCode);
//		mm.put("menuCode", menuCode);
//		return JSONObject.toJSONString(list, features);	
		return null;
	}
	
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Action ac = null;
		if(StringUtil.isNotBlank(id)){
			ac = this.actionService.findById(Integer.valueOf(id.trim()));
			mm.put("action", ac);
		}
		return "action/" + action;
	}
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Action action, ModelMap mm){
		if(action != null ){
			action.setCreateDate(DateUtil.getCurrentDate());
			this.actionService.add(action);
		}
		return AJAX_SUCCESS;
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Action action, ModelMap mm){
		if(action != null ){
			action.setCreateDate(DateUtil.getCurrentDate());
			this.actionService.update(action);
		}
		return AJAX_SUCCESS;
	}
	
	

}
