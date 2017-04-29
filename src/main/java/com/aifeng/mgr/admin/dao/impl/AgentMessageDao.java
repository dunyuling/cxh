package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IAgentMessageDao;
import com.aifeng.mgr.admin.model.AgentMessage;
import com.aifeng.mgr.admin.model.MessageRepeat;
import org.springframework.stereotype.Repository;

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
}