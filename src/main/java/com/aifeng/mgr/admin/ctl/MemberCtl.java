package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.constants.Status;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.MemberService;
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

@Controller
@RequestMapping("/member")
public class MemberCtl extends BaseCtl {

    private final MemberService memberService;

    @Autowired
    public MemberCtl(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/member/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        String addr = getAddr();
        List<Map<String, Object>> list = memberService.getMember(page, pageSize, addr);
        long total = memberService.getCount(addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("query")
    public String query(String mobile, String name, Model model) {
        model.addAttribute("mobile", mobile).addAttribute("name", name);
        return "console/member/query";
    }

    @RequestMapping(value = "/query2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String query2(int page, int pageSize, String name, String mobile) {
        String addr = getAddr();
        List<Map<String, Object>> list = memberService.queryMember(page, pageSize, name, mobile, addr);
        long total = memberService.queryCount(name, mobile, addr);
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        Map<String, Object> temp;
        if (StringUtil.isNotBlank(id)) {
            temp = this.memberService.getSingleMember(Long.valueOf(id.trim()));
            mm.put("temp", temp);
        }
        return "console/member/" + action;
    }

    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public String audit(long id, String status, String denyReason) {
        try {
            System.out.println("---");
            Admin admin = (Admin) this.get(Constants.SESSION_USER);
            memberService.audit(id, status, denyReason, admin.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "error", method = RequestMethod.POST)
    @ResponseBody
    public String error(long id) {
        try {
            Admin admin = (Admin) this.get(Constants.SESSION_USER);
            memberService.error(id, Status.ERROR.toString(), "", admin.getId());
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
                        this.memberService.delMember(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.memberService.delMember(l_id);
                }
            }
        }
        return AJAX_SUCCESS;
    }
}