package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentMessageDao;
import com.aifeng.mgr.admin.model.AgentMessage;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@Repository
public class AgentMessageDao extends BaseDao<AgentMessage> implements IAgentMessageDao {

    public List<AgentMessage> getAllNeedRepeat(int totalTimes) {
        Map<String, Object> params = new HashMap<>();
        params.put("totalTimes", totalTimes);
        String sql = "from AgentMessage where times <:totalTimes";
        return (List<AgentMessage>) this.findByHql(sql, params);
    }

    public List<Map<String, Object>> getAm(int page, int pageSize) {
        String str = "select am.id,a.name,m.content,am.times,am.visit,am.visitDate from agent_message am " +
                "left join agent a on am.agent_id = a.id " +
                "left join message m on am.message_id = m.id " +
                "limit " + pageSize + " offset " + (page - 1) * pageSize + ";";
        return this.findBySql(str);
    }

    public AgentMessage findByMemberId(long member_id) {
        Map<String, Object> params = new HashMap<>();
        params.put("member_id", member_id);
        String sql = "from AgentMessage where member_id=:member_id";
        return this.findOneByHql(sql, params);
    }
}

//    select am.id,a.name,m.content,am.times,am.readed,am.readDate from agent_message am
//        left join agent a on am.agent_id = a.id
//        left join message m on am.message_id = m.id