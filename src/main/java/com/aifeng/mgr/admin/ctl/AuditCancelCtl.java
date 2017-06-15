package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.AuditCancelService;
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
@RequestMapping("/audit_cancel")
public class AuditCancelCtl extends BaseCtl {

    private final AuditCancelService auditCancelService;

    @Autowired
    public AuditCancelCtl(AuditCancelService auditCancelService) {
        this.auditCancelService = auditCancelService;
    }

    @RequestMapping("list")
    public String list(int agentId, Model model) {
        model.addAttribute("agentId", agentId);
        return "console/ac/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(HttpServletRequest request, int page, int pageSize) {
        long agentId = Long.parseLong(request.getParameter("agentId"));
        List<Map<String, Object>> list;
        long total;
        if (agentId != 0) {
            list = auditCancelService.getAgentAuditCancel(agentId, page, pageSize);
            total = auditCancelService.getAgentCount(agentId);
        } else {
            String addr = getAddr();
            list = auditCancelService.getAuditCancel(page, pageSize, addr);
            total = auditCancelService.getCount(addr);
        }

        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(long agentId, String name, Model model) {
        model.addAttribute("agentId", agentId).addAttribute("name", name);
        return "console/ac/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String name) {
        //目前根据代理商名字查询，代理商登录则不提供查询功能
        String addr = getAddr();
        List<Map<String, Object>> list = auditCancelService.queryAuditCancel(page, pageSize, name, addr);
        int total = auditCancelService.queryCount(name, addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }
}