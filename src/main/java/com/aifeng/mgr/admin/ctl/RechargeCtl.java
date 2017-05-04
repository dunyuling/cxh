package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.FeeDeductionService;
import com.aifeng.mgr.admin.service.impl.RechargeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-30.
 */
@Controller
@RequestMapping("/recharge")
public class RechargeCtl extends BaseCtl {

    private final RechargeService rechargeService;

    @Autowired
    public RechargeCtl(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/recharge/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = rechargeService.getPagerRecharge(page, pageSize);
        long total = rechargeService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

}