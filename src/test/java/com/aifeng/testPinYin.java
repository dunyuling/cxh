package com.aifeng;

import com.aifeng.util.PinYinUtil;
import com.aifeng.util.Md5;
import com.alibaba.fastjson.JSONObject;
import opensource.jpinyin.PinyinFormat;
import org.junit.Test;

public class testPinYin extends HttpBase{

//	@Test
	public void test() {
		String name =  "范许超";
		System.out.println(PinYinUtil.convertToPinyinString(name,"", PinyinFormat.WITHOUT_TONE));
		System.out.println(PinYinUtil.getShortPinyin(name));
		
	}
	
	@Test
	public void test02(){
		String sa = new String("zyaf_coretimestamp1484835406312platandroidv1.0data{\"areaInfo\":\"河北 石家庄市 井陉县\"}tk42970b233b3eb6698bb5fd3669b922c2dbd6289ac956da029fe8c180f18940b3zyaf_core");
		StringBuilder sb = new StringBuilder();
//		sb.append("")
		JSONObject jo = new JSONObject();
		jo.put("areaInfo", "河北 石家庄市 井陉县");
//		JSONObject jo2 = new JSONObject();
//		jo2.put("data", jo);
//		String s = "{\"data\":{\"areaInfo\":\"河北 石家庄市 井陉县\"}}";
//		String sign = this.getSign("1484835406312", "android", "1.0", sa);
//		System.out.println(jo2.toJSONString());
//		System.out.println(sa.replaceAll("\\", ""));
//		System.out.println(JSON.parseObject(s));
//		System.out.println(Md5.getMd5(sa.toString()));
		
		sb.append("data").append(jo);
//		System.out.println(sb);
		
		String ss = "data{\"areaInfo\":\"河北 石家庄市 井陉县\"}";
		System.out.println(sb + "   加密后：          " + Md5.getMd5(sb.toString()));
		System.out.println(ss + "   加密后：          " + Md5.getMd5(ss.toString()));
//		System.out.println(sb);
	}

}
