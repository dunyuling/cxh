package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.Constants;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.dao.impl.VisitRecordDao;
import com.aifeng.mgr.admin.model.VisitRecord;
import com.aifeng.mgr.admin.service.IVisitRecordService;
import com.aifeng.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
    private final MessageService messageService;

    @Autowired
    public VisitRecordService(VisitRecordDao visitRecordDao, AgentMessageService agentMessageService, MessageService messageService) {
        this.visitRecordDao = visitRecordDao;
        this.agentMessageService = agentMessageService;
        this.messageService = messageService;
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


    String ids = "";
    @Scheduled(initialDelay = 1000 * 60, fixedDelay = 1000 * 60 * 60 * 24)
    public void visitRemind() {
        List<Map<String, Object>> list = visitRecordDao.getNeedRemindInThreeDays();
        list.forEach(m -> {
            try {
                long id = Long.parseLong(m.get("id").toString());
                ids += id + ",";
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("ids: " + ids);

        for (Map<String, Object> m : list) {
            System.out.println("id: " + m.get("id") + "\t -----------++++++++");
            messageService.sendMsg(m.get("userid").toString(), loadMsg(m));
        }


        ids = ids.substring(0, ids.lastIndexOf(","));
        String sql = "update visit_record vr set vr.remind = true where vr.id in (" + ids + ")";
        visitRecordDao.sqlUpdate(sql);
    }

    public void test() {
//        String sql = "update visit_record vr set vr.remind = true where vr.id in (23,24)";
//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//        Query query = session.createSQLQuery(sql);
//        query.executeUpdate();
//        tx.commit();
//        session.close();
    }

    private String loadMsg(Map<String, Object> map) {
        String type = InsuranceType.valueOf(map.get("type").toString()).getType();
        String nextDateStr = map.get("next_visit_date").toString();
        String memberCreateDate = map.get("m_create_date").toString();


        String zone = map.get("province") + " " + map.get("city") + " " + map.get("area");
        String content = "第" + map.get("times") + "次回访的下次回访提醒" +
                "\n回访时间" + Util.date2String(Util.str2Date(nextDateStr, "yyyy-MM-dd"), "yyyy-MM-dd") +
                "\n相应信息:" +
                "\n____________________________" +
                "\n\n" + Constants.wxMsgTitle +
                "\n时间: " + Util.date2String(Util.str2Date(memberCreateDate, "yyyy-MM-dd"), "yyyy-MM-dd") +
                "\n姓名: " + map.get("name") + "" +
                "\n电话: " + map.get("mobile") +
                "\n地区: " + zone +
                "\n咨询类型: " + type +
                "\n您处理该信息后，请点击:" + Constants.host + "wx/detail.cs?id=" + map.get("member_id");
        System.out.println("content: " + content);
        return content;
    }
}
