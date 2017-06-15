package com.aifeng.mgr.admin.ctl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aifeng.mgr.admin.service.IActionService;
import com.aifeng.mgr.admin.service.IMenuService;
import org.apache.log4j.Logger;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Action;
import com.aifeng.mgr.admin.model.Menu;
import com.aifeng.mgr.admin.vo.ZTree;
import com.aifeng.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/menu")
public class MenuCtl extends BaseCtl {

    Logger log = Logger.getLogger(MenuCtl.class);


//	@Autowired
//	RedisClientTemplate redisClientTemplate;

    @Autowired
    IMenuService menuService;

    @Autowired
    IActionService actionService;

    @RequestMapping("list")
    public String list(ModelMap mm, @RequestParam(required = false) String menuCode) {
        mm.put(MENU_CODE, menuCode);
        return "sys/menu/list";
    }

    @RequestMapping(value = "list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2() {
        List<Menu> list = this.menuService.getMenuList();

        List<ZTree> list2 = new ArrayList<ZTree>();
        ZTree zt = null;
        for (Menu m : list) {
            zt = new ZTree(m.getId(), m.getPid(), m.getName() + "-" + m.getCode(), m.getCode());
            zt.setOpen(true);
            list2.add(zt);
        }

        List<Map<String, Object>> list3 = this.actionService.getAllAction2();
        for (Map<String, Object> m : list3) {
            int id = Integer.valueOf(m.get("id").toString());
            int val = Integer.valueOf(m.get("val").toString());
            String name = m.get("name").toString();
            int menu_id = Integer.valueOf(m.get("menu_id").toString());

            zt = new ZTree(id * 100, menu_id, name + "-" + val);
            list2.add(zt);
        }
        return JSONObject.toJSONString(list2, features);
    }

    @RequestMapping("transfer")
    public String transfer(int id, String action, ModelMap mm) {
        Menu menu = null;
        if (id != 0) {
            menu = this.menuService.findById(id);
//			if("add".equals(action) && menu != null && menu.getPid() != 0){
//				menu = this.menuService.findById(menu.getPid());
//			}
            mm.put("menu", menu);
        }

        List<Menu> superMenuList = this.menuService.getSuperMenuList();
        mm.put("menuList", superMenuList);

        return "sys/menu/" + action;
    }

    @RequestMapping("transfer2")
    public String transfer2(int id, int menu_id, String action, ModelMap mm) {
        Menu menu = null;
        Action ac = null;
        if (id != 0) {
            ac = this.actionService.findById(id / 100);
            mm.put("ac", ac);
        }

        if (menu_id != 0) {
            menu = this.menuService.findById(menu_id);
            mm.put("menu", menu);
        }
        return "sys/menu/" + action;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Menu menu, ModelMap mm) {
        if (menu != null) {
            menu.setDisplay(true);
            menu.setCreateDate(DateUtil.getCurrentDate());
            this.menuService.addMenu(menu);
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "addAction", method = RequestMethod.POST)
    @ResponseBody
    public String addAction(Action ac, ModelMap mm) {
        if (ac != null) {
            this.actionService.addAction(ac);
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "editAction", method = RequestMethod.POST)
    @ResponseBody
    public String editAction(Action ac, ModelMap mm) {
        if (ac != null) {
            this.actionService.update(ac);
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(Menu menu, ModelMap mm) {
        if (menu != null) {
            menu.setDisplay(true);
            menu.setCreateDate(DateUtil.getCurrentDate());
            this.menuService.update(menu);
//			redisClientTemplate.set("menu_name", menu.getName());
//			System.out.println(redisClientTemplate.get("menu_name"));
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("modify")
    public String modify(Menu menu, ModelMap mm) {
        menu.setDisplay(true);
        this.menuService.update(menu);

        mm.put("superMenuList", this.menuService.getSuperMenuList());

        return "redirect:/list.cs";
    }

    @RequestMapping("del")
    @ResponseBody
    public String del(int id, ModelMap mm) {

//		mm.put("superMenuList", this.menuService.getSuperMenuList());
        if (id != 0) {
            this.menuService.deleteById(id);
        }
        return AJAX_SUCCESS;
    }


    @RequestMapping("delAction")
    @ResponseBody
    public String delAction(int id, ModelMap mm) {

//		mm.put("superMenuList", this.menuService.getSuperMenuList());
        if (id != 0) {
            this.actionService.deleteById(id / 100);
        }
        return AJAX_SUCCESS;
    }


}
