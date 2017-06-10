package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.mgr.admin.service.impl.ProxyAddressService;
import com.aifeng.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/pa")
public class ProxyAddressCtl extends BaseCtl {

    private final ProxyAddressService proxyAddressService;
    private final AdminService adminService;

    @Autowired
    public ProxyAddressCtl(ProxyAddressService proxyAddressService, AdminService adminService) {
        this.proxyAddressService = proxyAddressService;
        this.adminService = adminService;
    }

    @RequestMapping("list")
    public String list(Model model) {
        loadRole(adminService, model);
        return "console/pa/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        Object object = this.get(Constants.SESSION_USER);
        List<Map<String, Object>> list = new ArrayList<>();
        long total = 0;
        if (object instanceof Admin) {
            list = proxyAddressService.getPagerProxyAddress(page, pageSize);
            total = proxyAddressService.getProxyAddressCount();
        } else {
            Agent agent = (Agent) object;
            list = proxyAddressService.getAgentPagerProxyAddress(agent.getId(), page, pageSize);
            total = proxyAddressService.getAgentCount(agent.getId());
        }
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(Model model,String name) {
        loadRole(adminService, model);
        model.addAttribute("name", name);
        return "console/pa/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String name) {
        Object object = this.get(Constants.SESSION_USER);
        List<Map<String, Object>> list = new ArrayList<>();
        long total = 0;
        if (object instanceof Admin) {
            list = proxyAddressService.getQueryProxyAddress(page, pageSize, name);
            total = proxyAddressService.getQueryProxyAddressCount(name);
        } else {
            Agent agent = (Agent) object;
            list = proxyAddressService.getAgentQueryPagerProxyAddress(agent.getId(), page, pageSize, name);
            total = proxyAddressService.getAgentQueryCount(agent.getId(), name);
        }
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        Map<String, Object> map;
        if (StringUtil.isNotBlank(id)) {
            map = this.proxyAddressService.getProxyAddress(Long.valueOf(id.trim()));
            mm.put("temp", map);
        }
        return "console/pa/" + action;
    }

    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public String audit(long id, String province, String city, String area, String proxyStatus, String denyReason) {
        try {
            proxyAddressService.auditProxyAddress(id, province, city, area, proxyStatus, denyReason);
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
                        this.proxyAddressService.delAgent(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.proxyAddressService.delAgent(l_id);
                }
            }
        }
        return AJAX_SUCCESS;
    }
}