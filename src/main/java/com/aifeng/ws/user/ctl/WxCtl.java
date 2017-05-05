package com.aifeng.ws.user.ctl;

import com.aifeng.mgr.admin.constants.ImgPath;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.AgentService;
import com.aifeng.mgr.admin.service.impl.AuxiliaryInformationService;
import com.aifeng.mgr.admin.service.impl.MemberService;
import com.aifeng.util.Util;
import com.aifeng.ws.wx.UserResponse;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
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
    private final MemberService memberService;

    @Autowired
    public WxCtl(AgentService agentService, AuxiliaryInformationService auxiliaryInformationService, RestTemplate restTemplate, MemberService memberService) {
        this.agentService = agentService;
        this.auxiliaryInformationService = auxiliaryInformationService;
        this.restTemplate = restTemplate;
        this.memberService = memberService;
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

    @RequestMapping("to_submit")
    public String toSubmit(HttpServletRequest request, Model model) {
        model.addAttribute(getCode(request, model));
        return "/wx/page_submit";
    }

    public Map<String, String> getCode(HttpServletRequest request, Model model) {
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

        Map<String, String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("user_id", user_id);
        return map;
    }

    @RequestMapping(value = "manage", produces = "text/plain;charset=utf-8;")
    public String manage(HttpServletRequest request, Model model) {
        model.addAttribute(getCode(request, model));
        return "/wx/index";
    }

    @RequestMapping(value = "get_balance", produces = "text/plain;charset=utf-8;")
    @ResponseBody
    public String getBalance(HttpServletRequest request, Model model) {
        Map<String, String> map = getCode(request, model);
        model.addAttribute(map);
        Agent agent = agentService.getAgentByUserid(map.get("userid"));
        return agent.getMoney() + "";
    }

    @RequestMapping(value = "total", produces = "text/plain;charset=utf-8;")
    public String total(HttpServletRequest request, Model model) {
        String userid = request.getParameter("userid");

        return "/wx/total";
    }

    @RequestMapping(value = "today", produces = "text/plain;charset=utf-8;")
    public String today() {

        return "/wx/today";
    }

    @RequestMapping(value = "today_visit", produces = "text/plain;charset=utf-8;")
    public String todayVisit() {

        return "/wx/today_visit";
    }

    @RequestMapping(value = "today_not_visit", produces = "text/plain;charset=utf-8;")
    public String todayNotVisit() {

        return "/wx/today_visit";
    }

    @RequestMapping(value = "to_re_add", produces = "text/plain;charset=utf-8;")
    public String toReAdd(HttpServletRequest request, Model model) {
        model.addAttribute(getCode(request, model));
        return "/wx/proxy_address_add";
    }

    @RequestMapping(value = "agent_add", produces = "text/plain;charset=utf-8;")
    @ResponseBody
    public String agentAdd(HttpServletRequest request, Model model) {
        String result = "代理已成功添加，请等待运营认证";
        try {
            String addr = request.getParameter("addr");
            String user_id = request.getParameter("user_id");
            result = agentService.reAdd(user_id, addr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}