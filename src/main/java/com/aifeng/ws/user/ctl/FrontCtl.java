package com.aifeng.ws.user.ctl;

import com.aifeng.mgr.admin.service.impl.MemberService;
import com.aifeng.mgr.admin.service.impl.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

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

    @RequestMapping(value = "register_member", produces = "text/plain;charset=utf-8;")
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

    @RequestMapping(value = "index/{type}", produces = "text/plain;charset=utf-8;")
    public String index(@PathVariable(value = "type") String type) {
        return type.equals("mobile") ? "/front/mobile/index" : "/front/index";
    }

    @RequestMapping(value = "about/{type}", produces = "text/plain;charset=utf-8;")
    public String about(@PathVariable(value = "type") String type) {
        return type.equals("mobile") ? "/front/mobile/about" : "/front/about";
    }

    @RequestMapping("get_news/{type}")
    public String getNews(@RequestParam(value = "page", defaultValue = "1") int page,
                          @PathVariable(value = "type") String type,
                          Model model) {
        try {
            int pageSize = 10;
            List<Map<String, Object>> newses = newsService.getNews(page, pageSize);
            int pageCount = newsService.getPageCount(pageSize);
            model.addAttribute("newses", newses);
            model.addAttribute("current", page);
            model.addAttribute("pageCount", pageCount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !type.equals("mobile") ? "/front/news" : "/front/mobile/news";
    }

    @RequestMapping("get_news_detail/{type}")
    public String getNewsDetail(@RequestParam(value = "id") long id,
                                @PathVariable(value = "type") String type,
                                Model model) {
        try {
            model.addAttribute("news", newsService.getOne(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return !type.equals("mobile") ? "/front/news_detail" : "/front/mobile/news_detail";
    }

}
