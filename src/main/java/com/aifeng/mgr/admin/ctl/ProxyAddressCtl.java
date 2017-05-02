package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.ProxyAddressService;
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
@RequestMapping("/pa")
public class ProxyAddressCtl extends BaseCtl {

    private final ProxyAddressService proxyAddressService;

    @Autowired
    public ProxyAddressCtl(ProxyAddressService proxyAddressService) {
        this.proxyAddressService = proxyAddressService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/pa/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = proxyAddressService.getPagerProxyAddress(page, pageSize);
        long total = proxyAddressService.getTotal();
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
    public String audit(long id, String proxyStatus, String denyReason) {
        try {
            proxyAddressService.auditProxyAddress(id, proxyStatus, denyReason);
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