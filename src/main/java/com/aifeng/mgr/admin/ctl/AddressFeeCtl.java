package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Address;
import com.aifeng.mgr.admin.service.impl.AddressFeeService;
import com.aifeng.mgr.admin.service.impl.AddressService;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-24.
 */
@Controller
@RequestMapping("/addr")
public class AddressFeeCtl extends BaseCtl {

    Logger log = Logger.getLogger(AddressFeeCtl.class);

    private final AddressService addressService;
    private final AdminService adminService;
    private final AddressFeeService addressFeeService;

    @Autowired
    public AddressFeeCtl(AddressService addressService, AdminService adminService, AddressFeeService addressFeeService) {
        this.addressService = addressService;
        this.adminService = adminService;
        this.addressFeeService = addressFeeService;
    }

    @RequestMapping("list")
    public String list(Model model) {
        loadRole(adminService, model);
        return "console/addr/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        String addr = getAddr();
        List<Map<String, Object>> list = addressService.getAddress(page, pageSize, addr);
        int total = addressService.getCount(addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(String province, String city, String area, Model model) {
        loadRole(adminService, model);
        model.addAttribute("province", province).addAttribute("city", city).addAttribute("area", area);
        return "console/addr/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String province, String city, String area) {
        String addr = getAddr();
        List<Map<String, Object>> list = addressService.getAddressFee(page, pageSize, province, city, area, addr);
        int total = addressService.getAddressFeeCount(province, city, area, addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        Address address;
        if (StringUtil.isNotBlank(id)) {
            address = this.addressService.findById(Long.valueOf(id.trim()));
            int amount = addressFeeService.getAddressFee(address.getId()).getAmount();
            mm.put("address", address);
            mm.put("amount", amount);
        }
        return "console/addr/" + action;
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(String province, String city, String area, int amount) {
        if (!StringUtil.isBlank(province, city, area)) {
            addressService.saveAddress(province, city, area, amount);
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(long id, String province, String city, String area, int amount) {
        if (StringUtil.isNotBlank(province, city, area)) {
            this.addressService.editAddress(id, province, city, area, amount);
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
                        this.addressService.delAddress(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.addressService.delAddress(l_id);
                }
            }
        }

        return AJAX_SUCCESS;
    }
}