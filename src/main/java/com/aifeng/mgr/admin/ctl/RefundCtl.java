package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.RefundService;
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
@RequestMapping("/refund")
public class RefundCtl extends BaseCtl {

    private final RefundService refundService;

    @Autowired
    public RefundCtl(RefundService refundService) {
        this.refundService = refundService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/refund/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = refundService.getPagerRefund(page, pageSize);
        long total = refundService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

}