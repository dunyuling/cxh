package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.INewsDao;
import com.aifeng.mgr.admin.model.News;
import org.springframework.stereotype.Repository;

/**
 * Created by pro on 17-4-26.
 */
@Repository
public class NewsDao extends BaseDao<News> implements INewsDao {
}