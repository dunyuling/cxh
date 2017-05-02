package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.ImgPath;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.AgentService;
import com.aifeng.util.StringUtil;
import com.aifeng.util.Util;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/agent")
public class AgentCtl extends BaseCtl {

    private final AgentService agentService;

    @Autowired
    public AgentCtl(AgentService agentService) {
        this.agentService = agentService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/agent/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<Map<String,Object>> list = agentService.getPagerAgent(page, pageSize);
        long total = agentService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        Agent agent;
        if (StringUtil.isNotBlank(id)) {
            agent = this.agentService.findById(Long.valueOf(id.trim()));
            mm.put("agent", agent);
        }
        return "console/agent/" + action;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(HttpServletRequest request, long id, String name, String IDCard, String corpName, String expireDate) {
        try {
            String licenceImg = Util.editImg(request, ImgPath.wxAgentBusinessCardPath);
            agentService.editAgent(id, name, IDCard, corpName, licenceImg, expireDate);
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
                        this.agentService.delAgent(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.agentService.delAgent(l_id);
                }
            }
        }

        return AJAX_SUCCESS;
    }

}