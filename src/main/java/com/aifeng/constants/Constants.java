package com.aifeng.constants;

public interface Constants {

    String SESSION_USER = "sessionUser";
    String SESSION_PERMISSIONS = "permissions";
//    String SESSION_ROLE_NAME = "roleName";

    String HTTP_IMG_PREFIX = "http://localhost:8080";

    int wx_access_token_expire = 7200; //过期时间两个小时

    String getAccessToken = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=wx6b9738b957e2c065&corpsecret=tMxFn6Ec6iocXjui_muaSWvQ3CJcn2LjuwVuT9Gkhb3zBwR_KZiGLbQRYA45i969";
    String getCode = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6b9738b957e2c065&redirect_uri=http%3A%2F%2F192.168.50.108%3A8080%2Fwx%2Fget_code.cs&response_type=code&scope=snsapi_base&agentid=0&state=123346#wechat_redirect";
    String getUserId = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=SWO6gqpSbkuu1KAuSOE6F4ddLQVqQzYXW0yI8yc1L5tZZEPwn4T5za1THuhe6ed8&code=riRg8WQotd1bIq1cbqqmfdCuhVsn5RO7J9EoZjVUdt4";
    String secondAuth = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?access_token=SWO6gqpSbkuu1KAuSOE6F4ddLQVqQzYXW0yI8yc1L5tZZEPwn4T5za1THuhe6ed8&userid=lhg0";
    String sendMeg = "https://qyapi.weixin.qq.com/cgi-bin/message/send";
    String host = "http://192.168.50.108:8080/";

    int message_repeat_gap = 3600; //一个小时
    int message_repeat_total_times = 10;

    String wxMsgTitle = "南阳三六零度 车险询价";

}
