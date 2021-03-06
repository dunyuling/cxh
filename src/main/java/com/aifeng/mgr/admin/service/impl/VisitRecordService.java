package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.Constants;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.constants.InsuranceType;
import com.aifeng.mgr.admin.dao.impl.VisitRecordDao;
import com.aifeng.mgr.admin.model.VisitRecord;
import com.aifeng.mgr.admin.service.IVisitRecordService;
import com.aifeng.util.Util;
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

    private final VisitRecordDao visitRecordDao;
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

        agentMessageService.visitAgentMessage(member_id);
    }

    @Transactional
    public List<Map<String, Object>> getVisitRecord(long member_id) {
        return visitRecordDao.getMessageVisitRecord(member_id);
    }

    @Transactional
    public List<Map<String, Object>> getVisitRecordByAgentMessageId(long am_id) {
        return visitRecordDao.getMessageVisitRecordByAgentMessageId(am_id);
    }

    private String ids = "";
    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void visitRemind() {
        ids = "";
        List<Map<String, Object>> list = visitRecordDao.getNeedRemindInCurDate();
        list.forEach(m -> {
            try {
                long id = Long.parseLong(m.get("id").toString());
                ids += id + ",";
                System.out.println("id: " + m.get("id") + "\t -----------++++++++");
                messageService.sendMsg(m.get("userid").toString(), loadMessage(m));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        System.out.println("ids: " + ids);
        if (!ids.isEmpty()) {
            ids = ids.substring(0, ids.lastIndexOf(","));
            String sql = "update visit_record vr set vr.remind = true where vr.id in (" + ids + ")";
            visitRecordDao.sqlUpdate(sql);
        }
    }

    private String loadMessage(Map<String, Object> map) {
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
