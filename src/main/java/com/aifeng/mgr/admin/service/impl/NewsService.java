package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.NewsDao;
import com.aifeng.mgr.admin.model.News;
import com.aifeng.mgr.admin.service.INewsService;
import com.aifeng.util.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-4-24.
 */
@Service
public class NewsService extends BaseService<News> implements INewsService {

    private final NewsDao newsDao;

    @Autowired
    public NewsService(NewsDao newsDao) {
        this.newsDao = newsDao;
    }

    @Transactional
    public long getTotal() {
        return newsDao.countAll();
    }

    @Transactional
    public int getTotalPage(int pageSize) {
        long total = newsDao.countAll();
        long mod = total % pageSize;
        long divide = total / pageSize;
        return (int)(mod == 0 ? divide : (divide + 1));
    }

    @Transactional
    public List<News> getByPage(int page, int pageSize) {
        Pager pager = new Pager(page,pageSize);
        return newsDao.findAll(pager);
    }

    @Transactional
    public void saveNews(String title, String desc ,String img,String content) {
        News news = new News();
        news.setTitle(title);
        news.setDescription(desc);
        if (!img.isEmpty()) {
            news.setImg(img);
        }
        news.setContent(content);
        newsDao.insert(news);
    }

    @Transactional
    public void editNews(long id, String title, String desc ,String img,String content) {
        News news = newsDao.findById(id);
        news.setTitle(title);
        news.setDescription(desc);
        if (img != null && !img.isEmpty()) {
            news.setImg(img);
        }
        news.setContent(content);
        news.setUpdateDate(new Date());
        newsDao.update(news);
    }

    @Transactional
    public void delNews(long id) {
        newsDao.deleteByWhere(" where id = " + id);
    }
}