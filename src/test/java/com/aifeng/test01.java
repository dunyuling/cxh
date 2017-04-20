package com.aifeng;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSONObject;

public class test01  extends HttpBase {


	@Before
	public void beforeMethod(){
		plat 			= "android";
		v 				= "1.0";
		timestamp 		= String.valueOf(Calendar.getInstance().getTimeInMillis());
		this.tk = "6c74b8f131a6183aa8b6b392454f68d643d06e59c65c8a5fccec0ffc58ac1cd994ace596ddbbdbbf1c383c64b6392140067d939d2e3276052ca5ef3276030a14";
	}
	
//	@After
	public void afterMethod(){
		System.out.println(result);
	}
	
	
//	@Test 
	public void  getCommentVideos(){
		this.url = "http://127.0.0.1:8080/cs_core/test/login.cs";
		
		JSONObject json = new JSONObject();
		json.put("page",1);
		json.put("type", 4);
		
		this.data = json.toString();
		doReqWithParams();
	}
	
	
//	@Test 
	public void  testmsg(){
		this.url = "http://127.0.0.1:8080/cs_core/notify/toBroadCastMsg.cs";
		
		JSONObject json = new JSONObject();
		
		json.put("msg", "你有一条消息需要处理");
		
		this.data = json.toString();
		doReqWithParams();
	}
	
//	@Test 
	public void testCreateWdzOrder(){
		this.url = "http://localhost:8080/hzy/rest/order/createWdzOrder.cs";
		JSONObject json = new JSONObject();
		json.put("remark", "尽快发货吖");
		json.put("skuId", "1");
		json.put("num", "10");
		json.put("addressId", "1");
		json.put("from", "2");
		json.put("sample", "0");
		json.put("deliveryDate", "2017-03-10");
		this.data = json.toString();
		doReqWithParams_Mul();
	}
	
	@Test
	public void testbin(){
		int AU =  Integer.MAX_VALUE;
//		int a =  0x0000000000000000;
//		long b =  1 << 1 +  2 << 1 + 3 << 1 + 4 << 1 + 5 << 1+ 6 << 1+ 7 << 1;
//		long a = Long.MAX_VALUE;
		byte[] a = { 0x00, 0x00, 0x00,0x00, 0x00, 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00, 0x00, 0x00,0x00, 0x00, 0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00};
		System.out.println(a[15]);
//		System.out.println(Integer.toBinaryString(a & AU));
//		System.out.println(Integer.toBinaryString(a & AU));
//		System.out.println(a);
//		int AU =  Integer.MAX_VALUE;
//		System.out.println(AU);
//		System.out.println( Integer.toBinaryString(AU));
	}
	
	
	
	
	
	
	
}
