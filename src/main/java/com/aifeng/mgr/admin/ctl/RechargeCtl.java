package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.RechargeService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/recharge")
public class RechargeCtl extends BaseCtl {

    private final RechargeService rechargeService;

    @Autowired
    public RechargeCtl(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @RequestMapping("list")
    public String list(int agentId, Model model) {
        model.addAttribute("agentId", agentId);
        return "console/recharge/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(HttpServletRequest request, int page, int pageSize) {
        long agentId = Long.parseLong(request.getParameter("agentId"));
        List<Map<String, Object>> list;
        int total;
        if (agentId != 0) {
            list = rechargeService.getAgentPagerRecharge(agentId, page, pageSize);
            total = rechargeService.getAgentTotal(agentId);
        } else {
            String addr = getAddr();
            list = rechargeService.getPagerRecharge(page, pageSize, addr);
            total = rechargeService.getTotal(addr);
        }

        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(long agentId, String name, Model model) {
        model.addAttribute("agentId", agentId).addAttribute("name", name);
        return "console/recharge/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String name) {
        //目前根据代理商名字查询，代理商登录则不提供查询功能
        String addr = getAddr();
        List<Map<String, Object>> list = rechargeService.queryRecharge(page, pageSize, name, addr);
        int total = rechargeService.queryTotal(name, addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

}