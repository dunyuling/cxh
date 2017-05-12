package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.AgentMessageService;
import com.aifeng.mgr.admin.service.impl.VisitRecordService;
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
@RequestMapping("/am")
public class AgentMessageCtl extends BaseCtl {

    private final AgentMessageService agentMessageService;
    private final VisitRecordService visitRecordService;

    @Autowired
    public AgentMessageCtl(AgentMessageService agentMessageService, VisitRecordService visitRecordService) {
        this.agentMessageService = agentMessageService;
        this.visitRecordService = visitRecordService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/am/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = agentMessageService.getPagerAm(page, pageSize);
        long total = agentMessageService.getTotal();
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