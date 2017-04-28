package com.aifeng.ws.user.ctl;

import com.aifeng.ws.wx.UserResponse;
import org.apache.commons.collections.map.MultiValueMap;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@RequestMapping("/wx")
@Controller
public class WxCtl {

    private static String getUserId = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo";

    private static String access_token = "mZHPbLKL5lO6gSF5m4qWFJ44NqPWRkZStQV2IuYYloHtODJJIvvB12B0HgjgFz6e";

    @RequestMapping("get_code")
    public String getCode(HttpServletRequest request) {
        String code = request.getParameter("code");
        RestTemplate restTemplate = new RestTemplate();

        String userUrl = getUserId + "?access_token=" + access_token + "&code=" + code;
//        System.out.println("userUrl: " + userUrl);
//        ResponseEntity<UserResponse> response = restTemplate.getForEntity(userUrl, UserResponse.class);

//        Map<String, String> map = new HashMap<>();
//        map.put("access_token", access_token);
//        map.put("code", code);
        ResponseEntity<String> response = restTemplate.getForEntity(userUrl,String.class);

//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        MultiValueMap multiValueMap = new MultiValueMap();
//        multiValueMap.put("access_token", access_token);
//        multiValueMap.put("code", code);
//        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
//        ResponseEntity<String> response = restTemplate.exchange(userUrl, HttpMethod.GET, entity, String.class);

        System.out.println("===");
        return "success";
    }
}
//code kGtu29uT87ZGFI0kR3MIAHvsysyGD6W5C-bS9p_SlTc
//access_token SWO6gqpSbkuu1KAuSOE6F4ddLQVqQzYXW0yI8yc1L5tZZEPwn4T5za1THuhe6ed8
