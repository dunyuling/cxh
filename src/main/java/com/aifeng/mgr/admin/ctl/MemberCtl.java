package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.MemberService;
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
        List<Map<String, Object>> list = memberService.getPageMember(page, pageSize);
        long total = memberService.getTotal();
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

    //不提供修改功能，后台可以删除，会员重新注册
    /*@RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(long id, String name, String mobile, String type, String province, String city, String area) {
        try {
            memberService.edit(id, name, mobile, type, province, city, area);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }*/

    @RequestMapping(value = "audit", method = RequestMethod.POST)
    @ResponseBody
    public String audit(long id, String status, String denyReason) {
        try {
            memberService.audit(id, status, denyReason);
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