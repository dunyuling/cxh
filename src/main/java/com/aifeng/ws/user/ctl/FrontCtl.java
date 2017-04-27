package com.aifeng.ws.user.ctl;

import com.aifeng.mgr.admin.service.impl.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by pro on 17-4-27.
 */
@Controller
@RequestMapping("/front/")
public class FrontCtl {

    @Autowired
    MemberService memberService;

    @RequestMapping(value = "register_member",produces = "text/plain;charset=utf-8;")
    @ResponseBody
    public String registerMember(HttpServletRequest request, Model model) {
        try {
            String province = request.getParameter("province");
            String city = request.getParameter("city");
            String area = request.getParameter("area");
            String name = request.getParameter("name");
            String mobile = request.getParameter("mobile");
            int index = Integer.parseInt(request.getParameter("index"));

            String msg = memberService.registerMember(province, city, area, name, mobile, index);
            return msg;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "注册失败";
    }

    @RequestMapping("get_news")
    public void getNews() {

    }

}
