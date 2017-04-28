package com.aifeng.ws.user.ctl;

import com.aifeng.mgr.admin.model.News;
import com.aifeng.mgr.admin.service.impl.MemberService;
import com.aifeng.mgr.admin.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by pro on 17-4-27.
 */
@Controller
@RequestMapping("/front/")
public class FrontCtl {

    private final
    MemberService memberService;
    private final
    NewsService newsService;

    @Autowired
    public FrontCtl(MemberService memberService, NewsService newsService) {
        this.memberService = memberService;
        this.newsService = newsService;
    }


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
    public String getNews(@RequestParam(value="page",defaultValue = "0") int page, Model model) {
        try {
            int pageSize = 1;
            List<News> newses = newsService.getByPage(page,pageSize);
            int totalPage = newsService.getTotalPage(pageSize);
            model.addAttribute("newses", newses);
            model.addAttribute("totalPage",totalPage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/front/news";
    }

    @RequestMapping("get_news_detail")
    public String getNewsDetail(@RequestParam(value="id") long id,Model model) {
        try {
            model.addAttribute("news",newsService.getOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/front/news_detail";
    }

}
