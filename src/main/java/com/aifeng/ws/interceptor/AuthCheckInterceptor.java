package com.aifeng.ws.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aifeng.ws.AccessTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import com.aifeng.util.AES;
import com.aifeng.util.PropUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSONObject;

public class AuthCheckInterceptor extends HandlerInterceptorAdapter {

	Logger log = Logger.getLogger(AuthCheckInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		
		
		HandlerMethod methodHandler=(HandlerMethod) handler;
		Valid valid= methodHandler.getMethodAnnotation(Valid.class);
		String data = request.getParameter("data");
		String errorMsg = null;
		if(valid != null) {
			String method = ((HttpServletRequest)request).getMethod();
			String url = ((HttpServletRequest)request).getRequestURL().toString();
			
			String timestamp = request.getParameter("timestamp");
			String plat = request.getParameter("plat");
			String v = request.getParameter("v");
//			String data = request.getParameter("data");
			String sign = request.getParameter("sign");
			String tk = request.getParameter("tk");
			tk = tk == null ? "":tk;
			
			if(StringUtils.isNumeric(timestamp)){
				int out = 5;
				if(System.currentTimeMillis()<(Long.valueOf(timestamp) + (out * 60 * 1000))){
					if(!validateSign(timestamp, plat, v, data, sign, tk)){
						log.info("签名缺失或请求参数与签名不符");
						errorMsg = "非法请求";
					}
					else{
						if(valid.tk()){
							if(!checkTk(tk)){
								log.info("token非法。data:" + data);
								errorMsg = "非法请求";
							}
						}
					}
				}else{
					errorMsg  = "请求已过期";
					log.info("请求已过期");
				}
			}else{
				errorMsg = "请求已过期";
				log.info("参数timestamp不是有效的数字");
			}
		}
				
		if (errorMsg != null) {
			JSONObject json = new JSONObject();
			json.put("msg", errorMsg);
			json.put("ret", 0);
			writeToJson((HttpServletResponse) response, json);
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
	/**
	 * 
	 * @param timestamp
	 * @param plat
	 * @param v
	 * @param data
	 * @param sign
	 * @param tk
	 * @return
	 */
	private boolean validateSign(String timestamp, String plat, String v,String data, String sign, String tk){
		
		String key = PropUtil.getString("ws.request.sign.key");
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append("timestamp").append(timestamp);
		builder.append("plat").append(plat);
		builder.append("v").append(v);
		builder.append("data").append(data);
//		builder.append("tk").append(tk);
		builder.append(key);
		if(sign != null && sign.equals(AES.getMd5(builder.toString()))){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 验证token是否可用
	 * @param sign
	 * @return
	 */
	private boolean checkTk(String tk){
//		ISignService signService =  (ISignService) SpringUtil.getBean("signService");
		String userId = AccessTokenUtil.getUserIdByTk(tk);
		if(userId != null){
			if(AccessTokenUtil.getTk(userId).equals(tk)){
				return true;
			}
		}
		return false;
	}
	
	
	private void writeToJson(HttpServletResponse response, JSONObject json) throws IOException{
		response.setContentType("application/x-json");  
        PrintWriter out= response.getWriter();
        out.print(json.toJSONString());  
        out.close();
	}
	
}
