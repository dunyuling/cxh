package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.INewsDao;
import com.aifeng.mgr.admin.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-26.
 */
@Repository
public class NewsDao extends BaseDao<News> implements INewsDao {

    public List<Map<String, Object>> findAll(int page, int pageSize) {
        String str = "select id,title,img,description,content,createDate from news " +
                "order by createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }
}