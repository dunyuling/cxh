package com.aifeng.mgr.admin.ctl;

import com.aifeng.mgr.admin.constants.ImgPath;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.News;
import com.aifeng.mgr.admin.service.impl.NewsService;
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

/**
 * Created by pro on 17-4-26.
 */
@RequestMapping("/news")
@Controller
public class NewsCtl extends BaseCtl {


    private final
    NewsService newsService;

    @Autowired
    public NewsCtl(NewsService newsService) {
        this.newsService = newsService;
    }

    @RequestMapping("list")
    public String list() {
        return "console/news/list";
    }

    @RequestMapping(value = "/list2", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String list2(int page, int pageSize) {
        List<News> list = newsService.getByPage(page, pageSize);
        long total = newsService.getTotal();
        JSONObject json = new JSONObject();
        json.put("rows", list);
        json.put("total", total);
        return JSONObject.toJSONString(json, features);
    }

    @RequestMapping("transfer")
    public String transfer(String id, String action, ModelMap mm) {
        if (StringUtil.isNotBlank(id)) {
            News news = this.newsService.findById(Long.valueOf(id.trim()));
            mm.put("news", news);
        }
        return "console/news/" + action;
    }


    @RequestMapping(value = "add", method = RequestMethod.POST)
    @ResponseBody
    public String add(HttpServletRequest request, String title, String description ,String remark) {
        try {
            String descPath = Util.uploadImg(request, ImgPath.newsDescPath);
            newsService.saveNews(title,description,descPath,remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    @ResponseBody
    public String edit(HttpServletRequest request, long id ,String title, String description ,String remark) {
        try {
            String descPath = Util.editImg(request,ImgPath.newsDescPath);
            newsService.editNews(id,title,description,descPath,remark);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    @RequestMapping("del")
    @ResponseBody
    public String del(String id) {
        if(StringUtil.isNotBlank(id)) {
            if(id.contains(",")) {
                String[] ids = id.split(",");
                for(String id_ : ids) {
                    long l_id = Long.parseLong(id_);
                    if (l_id != 0) {
                        this.newsService.delNews(l_id);
                    }
                }
            } else {
                long l_id = Long.parseLong(id);
                if (l_id != 0) {
                    this.newsService.delNews(l_id);
                }
            }
        }
        return AJAX_SUCCESS;
    }
}