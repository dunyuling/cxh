package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMessageDao;
import com.aifeng.mgr.admin.model.Message;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class MessageDao extends BaseDao<Message> implements IMessageDao {

    public List<Map<String, Object>> getMessage(int page, int pageSize) {
        String str = "select m.id,ag.name, m.content, a.province,a.city,a.area from message m " +
                "left join proxy_address pa on m.proxyAddress_id = pa.id " +
                "left join address_fee af on pa.af_id = af.id " +
                "left join agent ag on pa.agent_id = ag.id " +
                "left join address a on af.address_id = a.id " +
                "order by m.createDate desc " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }
}