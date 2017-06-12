package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.constants.ImgPath;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.*;
import com.aifeng.util.StringUtil;
import com.aifeng.util.Util;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private final RechargeService rechargeService;
    private final RefundService refundService;
    private final AdminService adminService;
    private final AuditCancelService auditCancelService;

    @Autowired
    public AgentCtl(AgentService agentService, RechargeService rechargeService, RefundService refundService, AdminService adminService, AuditCancelService auditCancelService) {
        this.agentService = agentService;
        this.rechargeService = rechargeService;
        this.refundService = refundService;
        this.adminService = adminService;
        this.auditCancelService = auditCancelService;
    }

    @RequestMapping("list")
    public String list(Model model) {
        loadRole(adminService, model);
        return "console/agent/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        String addr = getAddr();
        List<Map<String, Object>> list = agentService.getPagerAgent(page, pageSize, addr);
        long total = agentService.getTotal(addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(String mobile, String IDCard, String expire_days, Model model) {
        loadRole(adminService, model);
        model.addAttribute("mobile", mobile).addAttribute("IDCard", IDCard).addAttribute("expire_days", expire_days);
        return "console/agent/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String mobile, String IDCard, String expire_days) {
        String addr = getAddr();
        List<Map<String, Object>> list = agentService.getPagerAgent(page, pageSize, mobile, IDCard, expire_days, addr);
        long total = agentService.getQueryAgentCount(mobile, IDCard, expire_days, addr);
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
    public String edit(HttpServletRequest request, long id, String name, String IDCard, String mobile, String corpName, String expireDate) {
        try {
            String licenceImg = Util.editImg(request, ImgPath.wxAgentBusinessCardPath);
            agentService.editAgent(id, name, IDCard, mobile, corpName, licenceImg, expireDate);
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

    @RequestMapping("recharge")
    @ResponseBody
    public String recharge(long id, int amount) {
        try {
            long operate_id = ((Admin) get(Constants.SESSION_USER)).getId();
            rechargeService.save(operate_id, id, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("refund")
    @ResponseBody
    public String refund(long id, int amount) {
        try {
            long operate_id = ((Admin) get(Constants.SESSION_USER)).getId();
            refundService.save(operate_id, id, amount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("auditCancel")
    @ResponseBody
    public String auditCancel(long id) {
        try {
            agentService.auditCancel(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("reAudit")
    @ResponseBody
    public String reAudit(long id) {
        try {
            agentService.reAudit(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("auditCancelFee")
    @ResponseBody
    public String auditCancelFee(long id) {
        try {
            long operate_id = ((Admin) get(Constants.SESSION_USER)).getId();
            auditCancelService.save(operate_id, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }
}