package com.aifeng.ws;

import java.util.Calendar;

import com.aifeng.redis.SpringRedisUtil;
import com.aifeng.util.AES;
import com.aifeng.util.DateUtil;
import com.aifeng.util.PropUtil;

public class AccessTokenUtil {

	private static String TKSIGNKEY = PropUtil.getString("ws.request.tk.key");
//	private static String TKSIGNKEY = "cs_core2";
	private static int TKEXPIRE = PropUtil.getInt("ws.request.tk.expire");
	public static String genTk(String userId){
		String tk =  ( "tk" + userId +  DateUtil.formatTime(Calendar.getInstance().getTime())).replaceAll("[-:\\s]", "");
		try {
//			System.out.println(tk);
			return AES.Encrypt(tk, AES.getMd5(TKSIGNKEY));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) throws Exception {
//		String tk = AccessTokenUtil.getAccessToken(UUID.randomUUID().toString()).replaceAll("-", "");
//		 String ck = "1234567890123456";
//		AES.Encrypt(tk,ck);
//		System.out.println(tk.length());
		String userId = "75f4682d182c4540860082c7cc5153b2"; 
//		String tk = ( "tk" + userId +  DateUtil.formatTime(Calendar.getInstance().getTime())).replaceAll("[-:\\s]", "");
//		String cKey = "cs_core";
//        // 需要加密的字串
//        String cSrc = tk;
////        String cSrc = "123456789";
//        String md5Pass = AES.getMd5(cKey);
//        System.out.println("key : " + md5Pass);
//        
//        System.out.println(cSrc);
//        // 加密
//        long lStart = System.currentTimeMillis();
//        String enString = AES.Encrypt(cSrc, md5Pass);
//        
//        System.out.println("加密后的字串是：" + enString);
//
//        long lUseTime = System.currentTimeMillis() - lStart;
//        System.out.println("加密耗时：" + lUseTime + "毫秒");
//        // 解密
//        lStart = System.currentTimeMillis();
//        String DeString = AES.Decrypt(enString, md5Pass);
//        System.out.println("解密后的字串是：" + DeString);
//        lUseTime = System.currentTimeMillis() - lStart;
//        System.out.println("解密耗时：" + lUseTime + "毫秒");
		
//		String userId = UUID.randomUUID().toString().replaceAll("-", "");
//		String tk = genTk(userId);
//		System.out.println();
//		System.out.println(getUserIdByTk(tk));
//		System.out.println(PropUtil.getString("ws.request.tk.sign.key"));
//		String tk = getAccessToken();
//		putTk(userId, tk);
//		System.out.println(getTk(userId));
//		System.out.println(getUserIdByTk(tk));
		System.out.println(14 * 24 * 60 * 60);
	}
	
	/**
	 * 
	 * @param tk
	 * @return
	 */
	public static String getUserIdByTk(String tk){
		String v = null;
		try {
			v = AES.Decrypt(tk, AES.getMd5(TKSIGNKEY));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		if(v != null && v.length() > 34){
			return v.substring(2, 34);
		}
		
		return null;
	}
	
	/**
	 * 存放tk到缓存服务器
	 * @param userId
	 * @param tk
	 */
	public static void putTk(String userId, String tk){
		SpringRedisUtil.set("tk#" + userId, tk, TKEXPIRE);
	}
	
	/**
	 * 从缓存服务器获取tk
	 * @param userId
	 * @return
	 */
	public static String getTk(String userId){
		return SpringRedisUtil.get("tk#" + userId);
	}
	
}
