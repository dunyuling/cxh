package com.aifeng.mgr.admin.service.impl;

import com.aifeng.constants.Constants;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.AuxiliaryInformationDao;
import com.aifeng.mgr.admin.dao.impl.MessageRepeatDao;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.model.Message;
import com.aifeng.mgr.admin.model.MessageRepeat;
import com.aifeng.mgr.admin.service.IMessageRepeatService;
import com.sun.corba.se.impl.orbutil.closure.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class MessageRepeatService extends BaseService<MessageRepeat> implements IMessageRepeatService {


    private final
    MessageRepeatDao messageRepeatDao;

    @Autowired
    public MessageRepeatService(MessageRepeatDao messageRepeatDao) {
        this.messageRepeatDao = messageRepeatDao;
    }

    @Transactional
    public MessageRepeat getOrMockFirst() {
        return messageRepeatDao.getFirst();
    }

    @Transactional
    public void edit(int gap, int totalTimes) {
        MessageRepeat messageRepeat = new MessageRepeat();
        messageRepeat.setGap(gap);
        messageRepeat.setTotalTimes(totalTimes);
        messageRepeat.setUpdateTime(new Date());
        this.messageRepeatDao.insert(messageRepeat);
    }


}