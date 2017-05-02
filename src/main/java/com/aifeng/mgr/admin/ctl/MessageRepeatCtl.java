package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.MessageRepeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17-4-26.
 */
@RequestMapping("/mr")
@Controller
public class MessageRepeatCtl extends BaseCtl {

    private final
    MessageRepeatService messageRepeatService;

    @Autowired
    public MessageRepeatCtl(MessageRepeatService messageRepeatService) {
        this.messageRepeatService = messageRepeatService;
    }

    @RequestMapping("to_edit")
    public String toEdit(Model model) {
        model.addAttribute("mr", messageRepeatService.getOrMockFirst());
        return "console/mr/edit";
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(int amount, int gap, int totalTimes) {
        try {
            messageRepeatService.edit(amount, gap, totalTimes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }
}
