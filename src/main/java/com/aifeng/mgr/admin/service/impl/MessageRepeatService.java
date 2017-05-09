package com.aifeng.mgr.admin.service.impl;

import com.aifeng.DemoServicePropertiesExample;
import com.aifeng.TestDemoService;
import com.aifeng.core.service.impl.BaseService;
import com.aifeng.mgr.admin.dao.impl.MessageRepeatDao;
import com.aifeng.mgr.admin.model.MessageRepeat;
import com.aifeng.mgr.admin.service.IMessageRepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by pro on 17-4-28.
 */
@Service
public class MessageRepeatService extends BaseService<MessageRepeat> implements IMessageRepeatService {


    private final
    MessageRepeatDao messageRepeatDao;
    private final TestDemoService testDemoService;

    @Autowired
    public MessageRepeatService(MessageRepeatDao messageRepeatDao, TestDemoService testDemoService) {
        this.messageRepeatDao = messageRepeatDao;
        this.testDemoService = testDemoService;
    }

    @Transactional
    public MessageRepeat getOrMockFirst() {
        return messageRepeatDao.getFirst();
    }

    @Transactional
    public void edit(int gap, int totalTimes) {
        if (testDemoService.isRunning())
            testDemoService.destroy();
        else
            testDemoService.start();
        MessageRepeat messageRepeat = messageRepeatDao.getFirst();
        messageRepeat.setGap(gap);
        messageRepeat.setTotalTimes(totalTimes);
        messageRepeat.setUpdateTime(new Date());
        this.messageRepeatDao.insert(messageRepeat);
//        DemoServicePropertiesExample.stop.set(false);
    }
}