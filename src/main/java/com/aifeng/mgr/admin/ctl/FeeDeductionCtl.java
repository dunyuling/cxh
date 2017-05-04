package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.FeeDeductionService;
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
@RequestMapping("/fd")
public class FeeDeductionCtl extends BaseCtl {

    private final FeeDeductionService feeDeductionService;

    @Autowired
    public FeeDeductionCtl(FeeDeductionService feeDeductionService) {
        this.feeDeductionService = feeDeductionService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/fd/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = feeDeductionService.getPagerFeeDeduction(page, pageSize);
        long total = feeDeductionService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

}