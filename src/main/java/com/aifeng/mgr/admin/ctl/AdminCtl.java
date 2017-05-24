package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.init_address.InitAddress;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/cs")
public class AdminCtl extends BaseCtl {

    private final AdminService adminService;

    @Autowired
    public AdminCtl(AdminService adminService) {
        this.adminService = adminService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/cs/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = adminService.getPagerCustomerService(page, pageSize);
        long total = adminService.getCustomerServiceTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        if (StringUtil.isNotBlank(id)) {
            mm.put("temp", this.adminService.getSingleCustomerService(Long.valueOf(id.trim())));
        }
        return "console/cs/" + action;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String addCustomerService(String name, String pwd, String province, String phone, int sex) {
        try {
            boolean added = adminService.addCustomerService(name, pwd, province, phone, sex);
            if (!added) return AJAX_RETURN;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(int id, String name, String pwd, String province, String phone, int sex) {
        try {
            boolean edit = adminService.editCustomerService(id, name, pwd, province, phone, sex);
            if (!edit) return AJAX_RETURN;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("del")
    @ResponseBody
    public String del(String id) {
        if (StringUtil.isNotBlank(id)) {
            if (id.contains(",")) {
                String[] ids = id.split(",");
                for (String id_ : ids) {
                    long l_id = Long.parseLong(id_);
                    if (l_id != 0) {
                        this.adminService.delCustomerService(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.adminService.delCustomerService(l_id);
                }
            }
        }
        return AJAX_SUCCESS;
    }
}