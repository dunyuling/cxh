package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.impl.FeeDeductionService;
import com.aifeng.mgr.admin.service.impl.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by pro on 17-4-30.
 */
@Controller("/rc")
public class RechargeCtl extends BaseCtl {

    private final RechargeService rechargeService;

    @Autowired
    public RechargeCtl(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @RequestMapping("recharge")
    @ResponseBody
    public String recharge(long agent_id, int amount) {
        Admin admin = (Admin) get(Constants.SESSION_USER);
        rechargeService.save(admin.getId(), agent_id, amount);
        return "";
    }

}