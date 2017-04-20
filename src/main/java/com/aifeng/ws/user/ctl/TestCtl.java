package com.aifeng.ws.user.ctl;

import java.util.UUID;

import com.aifeng.ws.AccessTokenUtil;
import com.aifeng.ws.interceptor.Valid;
import com.aifeng.core.ctl.BaseCtl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/test")
public class TestCtl extends BaseCtl{

	@Valid(tk=false)
	@RequestMapping(value="/login",method = RequestMethod.POST)
//	@ResponseBody
	public String login(){
		
		String userId = UUID.randomUUID().toString().replaceAll("-", "");
		
		String tk = AccessTokenUtil.genTk(userId);
		AccessTokenUtil.putTk(userId, tk);
		
		return "";
	}
	
}
