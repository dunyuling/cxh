package com.aifeng.mgr.admin.ctl;

import java.util.List;
import java.util.Map;

import com.aifeng.constants.Constants;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.service.IAdminService;
import com.aifeng.mgr.admin.service.IMenuService;
import com.aifeng.ws.interceptor.Valid;
import org.apache.log4j.Logger;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.util.Md5;
import com.aifeng.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mgr")
public class LoginCtl extends BaseCtl {

    private Logger log = Logger.getLogger(LoginCtl.class);

    private static String LOGIN = "redirect:/";
    private static String LOGIN_SUCCESS = "redirect:/mgr/main.cs";

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IAdminService adminService;


    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login(String account, String pwd, String code, ModelMap mm) {
        System.out.println("login ------------------------");
        if (StringUtil.isBlank(account, pwd)) {
            log.error("登录信息错误");
            return LOGIN;
        }

        Admin user = this.adminService.findByAccount(account);

		if(user == null){
            log.error("账户信息不存在");
			return  LOGIN;
		}

        if (user.getPwd().equals(Md5.getMd5(pwd.trim() + account.trim()))) {
            this.set(Constants.SESSION_USER, user);   // 用户信息
            return LOGIN_SUCCESS;
        } else {
            log.error("密码错误");
            return LOGIN;
        }
    }


    @RequestMapping("main")
    public String main(ModelMap mm) {

        // 初始化左侧菜单及权限信息
        Admin user = (Admin) this.get(Constants.SESSION_USER);

        if (user == null) {
            return "redirect:/";
        }

        List<Map<String, Object>> permissions = this.adminService.getPermissions(user.getCode());

        this.set(Constants.SESSION_PERMISSIONS, permissions);  //权限信息

        mm.put("user", user);
        mm.put("roleName", permissions.get(0).get("role_name"));
        mm.put(Constants.SESSION_PERMISSIONS, permissions);
        return "main";
    }


    @RequestMapping("logout")
    public String login(ModelMap mm) {
        return "redirect:/";
    }
}
