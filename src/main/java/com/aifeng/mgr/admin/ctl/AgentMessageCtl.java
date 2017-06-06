package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.mgr.admin.service.impl.AgentMessageService;
import com.aifeng.mgr.admin.service.impl.VisitRecordService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/am")
public class AgentMessageCtl extends BaseCtl {

    private final AgentMessageService agentMessageService;
    private final VisitRecordService visitRecordService;
    private final AdminService adminService;

    @Autowired
    public AgentMessageCtl(AgentMessageService agentMessageService, VisitRecordService visitRecordService, AdminService adminService) {
        this.agentMessageService = agentMessageService;
        this.visitRecordService = visitRecordService;
        this.adminService = adminService;
    }

    @RequestMapping("list")
    public String list(int agentId, Model model) {
        model.addAttribute("agentId", agentId);
        loadRole(adminService, model);
        return "console/am/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(HttpServletRequest request, int page, int pageSize) {
        long agentId = Long.parseLong(request.getParameter("agentId"));
        List<Map<String, Object>> list = new ArrayList<>();
        long total = 0;
        if (agentId != 0) {
            list = agentMessageService.getAgentPagerAm(agentId, page, pageSize);
            total = agentMessageService.getAgentTotal(agentId);
        } else {
            list = agentMessageService.getPagerAm(page, pageSize);
            total = agentMessageService.getTotal();
        }

        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap model) {
        model.addAttribute("id", id);
        return "console/am/" + action;
    }

    @RequestMapping(value = "view", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String view2(long id) {
        List<Map<String, Object>> visitRecordList = visitRecordService.getMemberVisitRecordByAmId(id);
        JSONObject json = new JSONObject();
        json.put("total", visitRecordList.size());
        json.put("rows", visitRecordList);
        return JSONObject.toJSONString(json, features);
    }

}