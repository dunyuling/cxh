package com.aifeng.mgr.admin.ctl;

import com.aifeng.mgr.admin.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by pro on 17-5-2.
 */
@Controller
@RequestMapping("/msg")
public class MessageCtl {

    @Autowired
    MessageService messageService;

    @RequestMapping("send")
    public void sendMsg() {
        messageService.sendMsg();
    }
}
