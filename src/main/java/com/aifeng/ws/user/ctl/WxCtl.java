package com.aifeng.ws.user.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.service.impl.AuxiliaryInformationService;
import com.aifeng.ws.wx.UserResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by pro on 17-4-27.
 */
@RequestMapping("/wx")
@Controller
public class WxCtl {

    @Autowired
    private AuxiliaryInformationService auxiliaryInformationService;

    @RequestMapping("get_code")
    public String getCode(HttpServletRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        AuxiliaryInformation ai = auxiliaryInformationService.getOrMockFirst();
        String access_token = ai.getAccess_token();
        if (access_token.isEmpty() || expire(System.currentTimeMillis(), ai.getUpdateDate().getTime())) {
            ResponseEntity<UserResponse> atResponse = restTemplate.getForEntity(loadAccessTokenUrl(ai.getCorpID(),ai.getSecret()),UserResponse.class);
            System.out.println("===" + atResponse);
            access_token = atResponse.getBody().getAccess_token();
        }

        String user_id = "";
        String code = request.getParameter("code");
        String codeStr = restTemplate.getForEntity(loadUserIdUrl(access_token,code), String.class).getBody();
        codeStr = codeStr.replace("UserId","user_id").replace("DeviceId","device_id");
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserResponse userResponse = mapper.readValue(codeStr, UserResponse.class);
            user_id = userResponse.getUser_id();
            System.out.println("---");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ResponseEntity<String> stringResponseEntity = restTemplate.getForEntity(loadSecondAuthUrl(access_token,user_id),String.class);
        System.out.println("===");
        return "success";
    }

    private String loadAccessTokenUrl(String corpid, String corpsecret) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?";
        return pre + "corpid=" + corpid + "&corpsecret=" + corpsecret;
    }

    private String loadCodeUrl(String appid) {
        String redirectUrl = Constants.host + "/wx/get_code.cs";
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        String pre = "https://open.weixin.qq.com/connect/oauth2/authorize?";
        return pre + "appid=" + appid +
                "&redirect_uri=" + redirectUrl +
                "&response_type=code" +
                "&scope=snsapi_base" +
                "&agentid=0" +
                "&state=123346" +
                "#wechat_redirect";
    }

    private String loadUserIdUrl(String access_token, String code) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?";
        return pre + "access_token=" + access_token + "&code=" + code;
    }

    private String loadSecondAuthUrl(String access_token,String userid) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?";
        return pre + "access_token=" + access_token + "&userid=" + userid;
    }

    private boolean expire(long time1, long time2) {
        return time1 - time2 > 6900 * 1000; //为保证方便，有效期缩小五分钟
    }
}
//code kGtu29uT87ZGFI0kR3MIAHvsysyGD6W5C-bS9p_SlTc
//access_token SWO6gqpSbkuu1KAuSOE6F4ddLQVqQzYXW0yI8yc1L5tZZEPwn4T5za1THuhe6ed8
