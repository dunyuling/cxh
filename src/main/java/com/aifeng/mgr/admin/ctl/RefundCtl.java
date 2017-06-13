package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.RefundService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
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
    public String list(int agentId, Model model) {
        model.addAttribute("agentId", agentId);
        return "console/refund/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(HttpServletRequest request, int page, int pageSize) {
        long agentId = Long.parseLong(request.getParameter("agentId"));
        List<Map<String, Object>> list = new ArrayList<>();
        long total = 0;
        if (agentId != 0) {
            list = refundService.getAgentPagerRefund(agentId, page, pageSize);
            total = refundService.getAgentTotal(agentId);
        } else {
            String addr = getAddr();
            list = refundService.getPagerRefund(page, pageSize, addr);
            total = refundService.getTotal(addr);
        }

        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(long agentId, String name, Model model) {
        model.addAttribute("agentId", agentId).addAttribute("name", name);
        return "console/refund/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String name) {
        //目前根据代理商名字查询，代理商登录则不提供查询功能
        String addr = getAddr();
        List<Map<String, Object>> list = refundService.queryRefunds(page, pageSize, name, addr);
        int total = refundService.queryTotal(name, addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

}