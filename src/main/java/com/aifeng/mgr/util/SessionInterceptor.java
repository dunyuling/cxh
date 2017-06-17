package com.aifeng.mgr.util;

import com.aifeng.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: SessionInterceptor
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年10月3日 上午12:28:30
 */
public class SessionInterceptor extends HandlerInterceptorAdapter {
    private String[] excludes = new String[]{"wx",
            "front",
            "toLogin.cs",
            "login.cs",
            "customerservice2Login.cs",
            "customerserviceLogin.cs",
            "agentLogin.cs",
            "agent2Login.cs",
            "to_pwd_reminder.cs",
            "pwd_reminder.cs",
            "retrieval_pwd.cs"};
    private Logger log = Logger.getLogger(SessionInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String contextPath = request.getContextPath();
        String url = request.getRequestURI();
        String referer = request.getHeader("Referer");// 获取地址来源，如果是在地址栏直接输入，那么这个值就为空，否则是从连接过来
        // 如果在地址栏直接输入login.shtml，那么要进行跳转
        /*if (StringUtil.isBlank(referer) && url.indexOf("login.cs") != -1) {
            response.sendRedirect(contextPath + "/mgr/main.cs");
        }*/

        HttpSession session = request.getSession();
        for (String exclude : excludes) {
            if (url.contains(exclude)) { // 不过滤内容
                return super.preHandle(request, response, handler);
            }
        }

        Object objUser = session.getAttribute(Constants.SESSION_USER);
//        if (objUser == null) {// 未登录，或者登录超时
//            if (referer == null) {
//                response.sendRedirect(url.contains("agent") ? contextPath + "/agent_login.jsp" : (url.contains("customerservice") ? contextPath + "/customerservice_login.jsp" : contextPath + "/login.jsp"));
//            } else {
//                response.sendRedirect(referer.contains("agent") ? contextPath + "/agent_login.jsp" : (referer.contains("customerservice") ? contextPath + "/customerservice_login.jsp" : contextPath + "/login.jsp"));
//            }
//            return false;
//        }

        if (objUser == null) {// 未登录，或者登录超时
            if (referer == null) {
                response.sendRedirect(url.contains("agent") ? "/mgr/agent2Login.cs" : (url.contains("customerservice") ? "/mgr/customerservice2Login.cs" : "/mgr/toLogin.cs"));
            } else {
                response.sendRedirect(url.contains("agent") ? "/mgr/agent2Login.cs" : (url.contains("customerservice") ? "/mgr/customerservice2Login.cs" : "/mgr/toLogin.cs"));
            }
            return false;
        }
        log.info(url);
        return super.preHandle(request, response, handler);
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }

}
