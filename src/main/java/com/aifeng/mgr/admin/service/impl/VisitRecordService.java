package com.aifeng.mgr.admin.service.impl;

import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.VisitRecordDao;
import com.aifeng.mgr.admin.model.VisitRecord;
import com.aifeng.mgr.admin.service.IVisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-11.
 */
@Service
public class VisitRecordService extends BaseService<VisitRecord> implements IVisitRecordService {

    private final
    VisitRecordDao visitRecordDao;
    private final AgentMessageService agentMessageService;

    @Autowired
    public VisitRecordService(VisitRecordDao visitRecordDao, AgentMessageService agentMessageService) {
        this.visitRecordDao = visitRecordDao;
        this.agentMessageService = agentMessageService;
    }

    @Transactional
    public void save(long member_id, String situation, String remark, Date nextVisitDate) {
        int times = visitRecordDao.getMessageRecordCount(member_id);
        VisitRecord visitRecord = new VisitRecord();
        visitRecord.setTimes(times + 1);
        visitRecord.setSituation(situation);
        visitRecord.setRemark(remark);
        visitRecord.setNextVisitDate(nextVisitDate);
        visitRecord.setMemberId(member_id);
        visitRecordDao.insert(visitRecord);

        agentMessageService.visit(member_id);
    }

    @Transactional
    public List<Map<String, Object>> getMemberVisitRecord(long member_id) {
        return visitRecordDao.getMessageVisitRecord(member_id);
    }

    @Transactional
    public List<Map<String, Object>> getMemberVisitRecordByAmId(long am_id) {
        return visitRecordDao.getMessageVisitRecordByAmId(am_id);
    }


    @Transactional
    public void getInt() {
        int count = visitRecordDao.getMessageRecordCount(65);
        System.out.println(count);
    }
}
