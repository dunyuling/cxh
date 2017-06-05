package com.aifeng.mgr.admin.ctl;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.service.impl.AuxiliaryInformationService;
import com.aifeng.util.Util;
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
    public String edit(String access_token, String corpID, String secret, String sign, String template) {
        try {
            auxiliaryInformationService.edit(Util.trim(access_token), Util.trim(corpID), Util.trim(secret), Util.trim(sign), Util.trim(template));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AJAX_SUCCESS;
    }
}