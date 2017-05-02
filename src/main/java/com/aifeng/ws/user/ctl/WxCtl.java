package com.aifeng.ws.user.ctl;

import com.aifeng.constants.Constants;
import com.aifeng.constants.ImgPath;
import com.aifeng.mgr.admin.model.AuxiliaryInformation;
import com.aifeng.mgr.admin.service.impl.AgentService;
import com.aifeng.mgr.admin.service.impl.AuxiliaryInformationService;
import com.aifeng.util.Util;
import com.aifeng.ws.wx.UserResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@RequestMapping("/wx")
@Controller
public class WxCtl {

    private final AuxiliaryInformationService auxiliaryInformationService;
    private final
    AgentService agentService;
    private final RestTemplate restTemplate;

    @Autowired
    public WxCtl(AgentService agentService, AuxiliaryInformationService auxiliaryInformationService, RestTemplate restTemplate) {
        this.agentService = agentService;
        this.auxiliaryInformationService = auxiliaryInformationService;
        this.restTemplate = restTemplate;
    }

    @RequestMapping(value = "agent_info", produces = "text/plain;charset=utf-8;")
    @ResponseBody
    public String agentInfo(HttpServletRequest request) {
        String result = "代理已成功添加，请等待运营认证";
        try {
            String licenceImg = Util.uploadImg(request, ImgPath.wxAgentBusinessCardPath, "file");
            String name = request.getParameter("name");
            String mobile = request.getParameter("mobile");
            String IDCard = request.getParameter("IDCard");
            String addr = request.getParameter("addr");
            String corpName = request.getParameter("corpName");
            String expireDate = request.getParameter("expireDate");
            String user_id = request.getParameter("user_id");
            Map<String, Object> map = agentService.saveAgent(name, mobile, IDCard, corpName, licenceImg, expireDate, addr, user_id);

            if ((boolean) map.get("success")) {
                String access_token = request.getParameter("access_token");

                restTemplate.getForEntity(Util.loadSecondAuthUrl(access_token, user_id), String.class);
            }
            result = (String) map.get("result");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @RequestMapping("get_code")
    public String getCode(HttpServletRequest request, Model model) {
        /*AuxiliaryInformation ai = auxiliaryInformationService.getOrMockFirst();
        String access_token = ai.getAccess_token();
        if (access_token.isEmpty() || expire(System.currentTimeMillis(), ai.getUpdateDate().getTime())) {
            ResponseEntity<UserResponse> atResponse = restTemplate.getForEntity(loadAccessTokenUrl(ai.getCorpID(), ai.getSecret()), UserResponse.class);
            System.out.println("===" + atResponse);
            access_token = atResponse.getBody().getAccess_token();
            auxiliaryInformationService.updateAccessToken(ai, access_token);
        }*/

        String access_token = auxiliaryInformationService.getAccessToken();

        String user_id = "";
        String code = request.getParameter("code");
        String codeStr = restTemplate.getForEntity(Util.loadUserIdUrl(access_token, code), String.class).getBody();
        codeStr = codeStr.replace("UserId", "user_id").replace("DeviceId", "device_id");
        ObjectMapper mapper = new ObjectMapper();
        try {
            UserResponse userResponse = mapper.readValue(codeStr, UserResponse.class);
            user_id = userResponse.getUser_id();
            System.out.println("---");
        } catch (IOException e) {
            e.printStackTrace();
        }


        model.addAttribute("access_token", access_token);
        model.addAttribute("user_id", user_id);

//        restTemplate.getForEntity(loadSecondAuthUrl(access_token, user_id), String.class);
        return "/wx/page_submit";
    }


}