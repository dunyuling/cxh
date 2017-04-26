package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.AuxiliaryInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17-4-26.
 */
@RequestMapping("/ai")
@Controller
public class AuxiliaryInformationCtl extends BaseCtl {

    private final
    AuxiliaryInformationService auxiliaryInformationService;

    @Autowired
    public AuxiliaryInformationCtl(AuxiliaryInformationService auxiliaryInformationService) {
        this.auxiliaryInformationService = auxiliaryInformationService;
    }

    @RequestMapping("to_edit")
    public String toEdit(Model model) {
        model.addAttribute("ai", auxiliaryInformationService.getOrMockFirst());
        return "console/ai/edit";
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String access_token,String corpID,String secret) {
        try {
            auxiliaryInformationService.edit(access_token,corpID,secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }

    //TODO 返回页面的改变
    /*@RequestMapping("edit")
    public String edit(String access_token,String corpID,String secret) {
        try {
            auxiliaryInformationService.edit(access_token,corpID,secret);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/ai/to_edit.cs";
    }*/
}
