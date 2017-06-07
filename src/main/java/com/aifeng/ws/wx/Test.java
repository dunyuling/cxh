package com.aifeng.ws.wx;

import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.util.Md5;
import com.aifeng.util.PwdReminderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-17.
 */
public class Test {

    //    private static String url = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=AygpKeRE_cq8PerL474s0MBCYfq-pyZxFfpp5OSv5ViRrEtneLhDylQEtdgGloJv";
//    private static String url = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=AygpKeRE_cq8PerL474s0MBCYfq-pyZxFfpp5OSv5ViRrEtneLhDylQEtdgGloJv&userid=lhg0";
//    private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd3c4992eaa887396&redirect_uri=http%3A%2F%2F192.168.50.108%3A8080%2F%23%21%2F&response_type=code&scope=snsapi_base&state=123456789#wechat_redirect";
//    private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd3c4992eaa887396&redirect_uri=http%3A%2F%2F192.168.50.108%3A8080%2Fmobile%2Fad.json&response_type=code&scope=snsapi_base&state=123456789#wechat_redirect";
    private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd3c4992eaa887396&redirect_uri=http%3A%2F%2F192.168.50.108%3A8080%2Fmobile%2Fad.json&response_type=code&scope=snsapi_base&state=123456789#wechat_redirect";

    public static void main(String[] args) {
        System.out.println(Md5.getMd5("123456"));

//        String generatedString = RandomStringUtils.random(6, false, true);
//        System.out.println(generatedString.toLowerCase());
//        AuxiliaryInformation auxiliaryInformation = new AuxiliaryInformation();
//        auxiliaryInformation.config("", "", "", "车险汇", "", "SMS_70285207");
//        PwdReminderUtil.send(auxiliaryInformation, "代理商", "lhg", generatedString.toLowerCase(), "13818280352");
    }

    public static void send1() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new StringHttpMessageConverter());


            Map<String, String> urlMap = new HashMap<>();
            urlMap.put("access_token", "AygpKeRE_cq8PerL474s0MBCYfq-pyZxFfpp5OSv5ViRrEtneLhDylQEtdgGloJv");


            RequestBody requestBody = new RequestBody();
            requestBody.setTouser("lhg");
            requestBody.setMsgtype("text");
            requestBody.setAgentid(0);

            Map<String, Object> map = new HashMap<>();
            map.put("content", "a");


//            https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxd3c4992eaa887396&redirect_uri=http%3A%2F%2Fwww.baidu.com&response_type=code&scope=snsapi_base&state=12035454545#wechat_redirect


            requestBody.setText(map);
            requestBody.setSafe(0);


//            Map<String, String> map = new HashMap<>();
//            map.put("touser", "lhg");
//            map.put("msgtype", "text");
//            map.put("agentid", "0");
//            map.put("safe", "0");
//
//            Map<String, String> map1 = new HashMap<>();
//            map1.put("content", "a");
//            map.put("text", new ObjectMapper().writeValueAsString(map1));
//            System.out.println(new ObjectMapper().writeValueAsString(map));


//            ResponseEntity<ResponseType> response = restTemplate.getForEntity(url, null, ResponseType.class);
//            System.out.println(response.getBody().getErrmsg());


            String s = URLEncoder.encode("http://192.168.50.108:8080/#!/", "utf-8");
//            String s = URLEncoder.encode("http://www.baidu.com","utf-8");
            System.out.println("s: " + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String convertMapToString() {
        String result = "";

        // write your code here
        ObjectMapper mapper = new ObjectMapper(); // can reuse, share globally
        try {
            // 读取JSON数据
            Map<String, Object> userData = new HashMap<>();
            // 写入JSON数据
            Map<String, String> nameStruct = new HashMap<>();
            nameStruct.put("first", "Joe");
            nameStruct.put("last", "Hankcs");
            userData.put("name", nameStruct);
            userData.put("gender", "MALE");
            userData.put("verified", Boolean.FALSE);
            userData.put("userImage", "Rm9vYmFyIQ==");

            result = mapper.writeValueAsString(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
/*

{
   "touser": "lhg",
   "msgtype": "text",
   "agentid": 0,
   "text": {
       "content": "南阳三六零度   客户咨询提醒\n\n时间：2017年02月21日\n姓名：王\n电话：18603728109\nID:105574\n咨询类型：注册公司\n诉求:注册公司\n您处理该消息后，请点击:http://cwoms.cn/AL92F4k"
   },
   "safe":0
}

 */

// access_token OfSAz2WDFW2Jxywt17yC0uX2n93oO5AUo6wfeB8s_yNXq_obBYe5TC3JMYxB2RJj