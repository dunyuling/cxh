package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.ProblemService;
import com.aifeng.util.StringUtil;
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
@RequestMapping("/problem")
public class ProblemCtl extends BaseCtl {

    private final ProblemService problemService;

    @Autowired
    public ProblemCtl(ProblemService problemService) {
        this.problemService = problemService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/problem/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String, Object>> list = problemService.getPagerProblems(page, pageSize);
        long total = problemService.getTotal();
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
            long customerService_id = ((Admin) get(Constants.SESSION_USER)).getId();
            problemService.save(title, description, customerService_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(long id, String title, String description) {
        try {
            long customerService_id = ((Admin) get(Constants.SESSION_USER)).getId();
            problemService.edit(id, title, description, customerService_id);
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