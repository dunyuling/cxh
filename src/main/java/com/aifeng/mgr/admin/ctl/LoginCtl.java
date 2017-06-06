package com.aifeng.mgr.admin.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.mgr.admin.model.Admin;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.AdminService;
import com.aifeng.mgr.admin.service.impl.AgentService;
import com.aifeng.util.Md5;
import com.aifeng.util.StringUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/mgr")
public class LoginCtl extends BaseCtl {

    private Logger log = Logger.getLogger(LoginCtl.class);

    private final AdminService adminService;
    private final AgentService agentService;

    @Autowired
    public LoginCtl(AdminService adminService, AgentService agentService) {
        this.adminService = adminService;
        this.agentService = agentService;
    }


    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String account, String pwd) {
        System.out.println("login ------------------------");
        if (StringUtil.isBlank(account, pwd)) {
            log.error("登录信息错误");
            return "login";
        }

        Admin user = this.adminService.findByAccount(account);
        if (user == null) {
            log.error("账户信息不存在");
            return "login";
        }

        if (user.getPwd().equals(Md5.getMd5(pwd.trim() + account.trim()))) {
            this.set(Constants.SESSION_USER, user);   // 用户信息
            return "redirect:/mgr/main.cs";
        } else {
            log.error("密码错误");
            return "login";
        }
    }

    @RequestMapping("main")
    public String main(ModelMap mm) {
        try {
            // 初始化左侧菜单及权限信息
            Admin user = (Admin) this.get(Constants.SESSION_USER);
            if (user == null) {
                return "redirect:/mgr/toLogin.cs";
            }
            List<Map<String, Object>> permissions = this.adminService.getPermissions(user.getCode());
            this.set(Constants.SESSION_PERMISSIONS, permissions);  //权限信息

            mm.put("user", user);
            mm.put("roleName", permissions.get(0).get("role_name"));
            mm.put(Constants.SESSION_PERMISSIONS, permissions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "main";
    }

    @RequestMapping("agent2Login")
    public String agent2Login() {
        return "agent_login";
    }

    @RequestMapping(value = "agentLogin", method = RequestMethod.POST)
    public String agentLogin(String mobile, String pwd) {
        System.out.println("agent login ------------------------");
        if (StringUtil.isBlank(mobile, pwd)) {
            log.error("登录信息错误");
            return "agent_login";
        }

        Agent agent = agentService.findByMobile(mobile);
        if (agent == null) {
            log.error("账户信息不存在");
            return "agent_login";
        }

        if (agent.getPwd().equals(Md5.getMd5(pwd.trim()))) {
            this.set(Constants.SESSION_USER, agent);   // 用户信息
            return "redirect:/mgr/agentMain.cs";
        } else {
            log.error("密码错误");
            return "agent_login";
        }
    }

    @RequestMapping("agentMain")
    public String agentMain(ModelMap mm) {
        // 初始化左侧菜单及权限信息
        Agent user = (Agent) this.get(Constants.SESSION_USER);
        if (user == null) {
            return "redirect:/mgr/agent2Login.cs";
        }
        List<Map<String, Object>> permissions = this.adminService.getPermissions(Constants.agent_code);
        this.set(Constants.SESSION_PERMISSIONS, permissions);  //权限信息

        mm.put("user", user);
        mm.put("roleName", permissions.get(0).get("role_name"));
        mm.put(Constants.SESSION_PERMISSIONS, permissions);
        return "agent_main";
    }

    @RequestMapping("logout")
    public String login() {
        Object o = this.get(Constants.SESSION_USER);
        if (o instanceof Admin) {
            this.set(Constants.SESSION_USER, null);   // 用户信息
            return "redirect:/mgr/toLogin.cs";
        } else {
            this.set(Constants.SESSION_USER, null);   // 用户信息
            return "redirect:/mgr/agent2Login.cs";
        }
    }


    @RequestMapping("to_edit_main_pwd")
    public String toEditMainPwd(int id, Model model) {
        model.addAttribute("id", id);
        return "edit_main_pwd";
    }

    @RequestMapping("edit_main_pwd")
    @ResponseBody
    public String editMainPwd(int id, String pwd, String oldPwd) {
        try {
            Admin admin = adminService.findById(id);
            if (admin.getPwd().equals(Md5.getMd5(oldPwd.trim() + admin.getAccount().trim()))) {
                adminService.editMainPwd(id, pwd);
                return "success";
            } else {
                return "oldpwd is wrong";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }

    @RequestMapping("to_edit_agent_pwd")
    public String toEditAgentPwd(int id, Model model) {
        model.addAttribute("id", id);
        return "edit_agent_pwd";
    }

    @RequestMapping("edit_agent_pwd")
    @ResponseBody
    public String editAgentPwd(long id, String pwd, String oldPwd) {
        try {
            if (agentService.findById(id).getPwd().equals(Md5.getMd5(oldPwd))) {
                agentService.editAgentPwd(id, pwd);
                return "success";
            } else {
                return "oldpwd is wrong";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "failure";
        }
    }
}