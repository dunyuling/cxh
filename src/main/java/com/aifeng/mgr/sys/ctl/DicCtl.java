package com.aifeng.mgr.sys.ctl;

import com.aifeng.core.model.Dic;
import com.aifeng.util.Pager;
import org.apache.log4j.Logger;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.sys.service.IDicMgrService;
import com.aifeng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/dic")
public class DicCtl extends BaseCtl{
	
	Logger log = Logger.getLogger(DicCtl.class);

	
	@Autowired
	IDicMgrService dicMgrService;
	
	@RequestMapping("list")
	public String list(ModelMap mm){
		return "sys/dic/list";	
	}
	
	@RequestMapping(value="list2", method = RequestMethod.GET, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String list2(int page, int pageSize, ModelMap mm){
		Pager pager = this.dicMgrService.getDicList(new Pager(page, pageSize));
		JSONObject json = new JSONObject();
		if(pager != null){
			json.put("rows", pager.getResults());
			json.put("total", pager.getTotal());
		}
		return JSONObject.toJSONString(json, features);
	}
	
	@RequestMapping("transfer")
	public String transfer(String id, String action, ModelMap mm){
		Dic dic = null;
		if(StringUtil.isNotBlank(id)){
			dic = this.dicMgrService.findById(Integer.valueOf(id.trim()));
			mm.put("dic", dic);
		}
		return "sys/dic/" + action;
	}
	
	
	@RequestMapping(value="add", method = RequestMethod.POST)
	@ResponseBody
	public String add(Dic dic, ModelMap mm){
		if(dic != null ){
			this.dicMgrService.add(dic);
		}
		return AJAX_SUCCESS;
	}
	
	@RequestMapping(value="edit", method = RequestMethod.POST)
	@ResponseBody
	public String edit(Dic dic, ModelMap mm){
		if(dic != null ){
			this.dicMgrService.update(dic);
		}
		return AJAX_SUCCESS;
	}
	
	
	@RequestMapping("del")
	@ResponseBody
	public String del(int id, ModelMap mm){
		
//		mm.put("superMenuList", this.menuService.getSuperMenuList());
		if(id != 0){
			this.dicMgrService.deleteById(id);
		}
		
		return AJAX_SUCCESS;
	}
	
	
	
	

}
