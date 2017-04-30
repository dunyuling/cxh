package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.LogType;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.SystemLogDao;
import com.aifeng.mgr.admin.model.SystemLog;
import com.aifeng.mgr.admin.service.ISystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pro on 17-4-30.
 */
@Service
public class SystemLogService extends BaseService<SystemLog> implements ISystemLogService {

    private final SystemLogDao systemLogDao;

    @Autowired
    public SystemLogService(SystemLogDao systemLogDao) {
        this.systemLogDao = systemLogDao;
    }

    @Transactional
    public void save(long operate_id, LogType logType, String content) {
        SystemLog systemLog = new SystemLog();
        systemLog.setOperate_id(operate_id);
        systemLog.setLogType(logType);
        systemLog.setContent(content);
        this.systemLogDao.insert(systemLog);
    }
}