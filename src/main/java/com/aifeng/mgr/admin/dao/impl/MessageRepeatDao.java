package com.aifeng.mgr.admin.dao.impl;

import com.aifeng.core.dao.impl.BaseDao;
import com.aifeng.mgr.admin.dao.IMessageRepeatDao;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.model.MessageRepeat;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-28.
 */
@Repository
public class MessageRepeatDao extends BaseDao<MessageRepeat> implements IMessageRepeatDao {

    public MessageRepeat getFirst() {
        String sql = "from MessageRepeat";
        List<MessageRepeat> messageRepeatList = (List<MessageRepeat>) this.findByHql(sql,null);
        if(!messageRepeatList.isEmpty()) {
            return messageRepeatList.get(0);
        }
        return new MessageRepeat();
    }
}