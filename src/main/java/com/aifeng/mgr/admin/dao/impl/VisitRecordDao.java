package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IVisitRecordDao;
import com.aifeng.mgr.admin.model.VisitRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-5-11.
 */
@Repository
public class VisitRecordDao extends BaseDao<VisitRecord> implements IVisitRecordDao {

    public int getMessageRecordCount(long member_id) {
        String sql = "select id from visit_record where member_id = " + member_id;
        return this.findBySql(sql).size();
    }

    public List<Map<String, Object>> getMessageVisitRecord(long member_id) {
        String sql = "select id,situation,remark,visit_date as visitDate,next_visit_date as nextVisitDate ,times from visit_record " +
                " where member_id = " + member_id +
                " order by times desc ";
        return this.findBySql(sql);
    }

    public List<Map<String, Object>> getMessageVisitRecordByAmId(long am_id) {
        String sql = "select vr.id,vr.situation,vr.remark,vr.visit_date as visitDate,vr.next_visit_date as nextVisitDate ,vr.times from agent_message am " +
                "left join visit_record vr on am.member_id = vr.member_id " +
                " where am.id = " + am_id +
                " order by vr.times desc ";
        return this.findBySql(sql);
    }

    public int test() {
        String sql = "select id from visit_record where member_id = " + 65;
        List<Map<String, Object>> list = this.findBySql(sql);
        return list.size();
    }
}