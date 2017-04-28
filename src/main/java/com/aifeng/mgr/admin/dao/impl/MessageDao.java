package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMessageDao;
import com.aifeng.mgr.admin.model.Message;
import org.springframework.stereotype.Repository;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class MessageDao extends BaseDao<Message> implements IMessageDao {
}