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
    private String[] excludes;// 不使用过滤器的部分，可以不登录使用的内容，需要在spring-mvc中进行配置
    private Logger log = Logger.getLogger(SessionInterceptor.class);
    private boolean agent = false;

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

        //非后台
        if(url.contains("wx") || url.contains("front")) {
            return super.preHandle(request, response, handler);
        }

        //后台管理员或者客服
        if (url.contains("login.cs")) {
            agent = false;
            return super.preHandle(request, response, handler);
        }

        //后台代理商
        if (url.contains("agentLogin.cs")) {
            agent = true;
            return super.preHandle(request, response, handler);
        }

        HttpSession session = request.getSession();
        Object objUser = session.getAttribute(Constants.SESSION_USER);
        if (objUser == null) {// 未登录，或者登录超时
            if (excludes != null) {
                for (String exclude : excludes) {
                    if (url.startsWith(contextPath + exclude)) { // 不过滤内容
                        return true;
                    } else {
                        continue;
                    }
                } // 需要登陆的跳转
            }
            response.sendRedirect(agent ? contextPath + "/agent_login.jsp" : contextPath + "/login.jsp");
            return false;
        }
        log.info(url);
        return super.preHandle(request, response, handler);
    }

    public void setExcludes(String[] excludes) {
        this.excludes = excludes;
    }
}
