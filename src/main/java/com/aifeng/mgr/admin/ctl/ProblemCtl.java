package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.ProblemService;
import com.aifeng.util.StringUtil;
import com.alibaba.fastjson.JSONObject;
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
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/problem")
public class ProblemCtl extends BaseCtl {

    private final ProblemService problemService;

    @Autowired
    public ProblemCtl(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping("admin_list")
    public String adminList() {
        return "console/problem/admin_list";
    }

    @RequestMapping(value = "/admin_list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminList2(int page, int pageSize) {
        List<Map<String, Object>> list = problemService.getPagerProblems(page, pageSize);
        long total = problemService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("admin_query")
    public String adminQuery(String agent_name, String cs_name, Model model) {
        model.addAttribute("agent_name", agent_name).addAttribute("cs_name", cs_name);
        return "console/problem/admin_query";
    }

    @RequestMapping(value = "/admin_query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String adminQuery2(int page, int pageSize, String agent_name, String cs_name) {
        List<Map<String, Object>> list = problemService.queryProblems(page, pageSize, agent_name, cs_name);
        long total = problemService.queryTotal(agent_name, cs_name);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("cs_list")
    public String csList() {
        return "console/problem/cs_list";
    }

    @RequestMapping(value = "/cs_list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String csList2(int page, int pageSize) {
        int id = ((Admin) this.get(Constants.SESSION_USER)).getId();
        List<Map<String, Object>> list = problemService.getCsPagerProblems(id, page, pageSize);
        long total = problemService.getCsCount(id);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("agent_list")
    public String agentList() {
        return "console/problem/agent_list";
    }

    @RequestMapping(value = "/agent_list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String agentList2(int page, int pageSize) {
        long id = ((Agent) this.get(Constants.SESSION_USER)).getId();
        List<Map<String, Object>> list = problemService.getAgentPagerProblems(id, page, pageSize);
        long total = problemService.getAgentCount(id);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        Map<String, Object> map;
        if (StringUtil.isNotBlank(id)) {
            map = this.problemService.getSingleProblem(Long.valueOf(id.trim()));
            mm.put("temp", map);
        }
        return "console/problem/" + action;
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(String title, String description) {
        try {
            Object obj = this.get(Constants.SESSION_USER);
            if (obj instanceof Agent) {
                problemService.save(title, description, 0, ((Agent) (obj)).getId());
            } else {
                problemService.save(title, description, ((Admin) (obj)).getId(), 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(long id, String title, String description) {
        try {
            problemService.edit(id, title, description);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "solve", method = RequestMethod.POST)
    @ResponseBody
    public String solve(long id, String solution) {
        try {
            problemService.solve(id, solution);
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
                        this.problemService.delProblem(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.problemService.delProblem(l_id);
                }
            }
        }
        return AJAX_SUCCESS;
    }
}