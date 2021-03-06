package com.aifeng.ws.user.ctl;

import com.aifeng.mgr.admin.constants.ImgPath;
import com.aifeng.mgr.admin.model.Agent;
import com.aifeng.mgr.admin.service.impl.*;
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
import java.util.List;
import java.util.Map;

/**
 * Created by pro on 17-4-27.
 */
@RequestMapping("/wx")
@Controller
public class WxCtl {

    private final AuxiliaryInformationService auxiliaryInformationService;
    private final AgentService agentService;
    private final RestTemplate restTemplate;
    private final MemberService memberService;
    private final AgentMessageService agentMessageService;
    private final VisitRecordService visitRecordService;

    @Autowired
    public WxCtl(AgentService agentService, AuxiliaryInformationService auxiliaryInformationService, RestTemplate restTemplate, MemberService memberService, AgentMessageService agentMessageService, VisitRecordService visitRecordService) {
        this.agentService = agentService;
        this.auxiliaryInformationService = auxiliaryInformationService;
        this.restTemplate = restTemplate;
        this.memberService = memberService;
        this.agentMessageService = agentMessageService;
        this.visitRecordService = visitRecordService;
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
        Map<String, String> map = getCode(request, model, "toSubmit");
        String user_id = map.get("user_id");

        Map<String, Object> temp = agentService.getAgentSubmitMsgFromWx(user_id);
        if (temp == null) {
            model.addAllAttributes(map);
            return "/wx/page_submit";
        } else {
            model.addAttribute("temp", temp);
            return "/wx/page_submitted";
        }
    }

    public Map<String, String> getCode(HttpServletRequest request, Model model, String method) {
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
            System.out.println("method: " + method + " getCode userid:" + user_id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String> map = new HashMap<>();
        map.put("access_token", access_token);
        map.put("user_id", user_id);
        return map;
    }

    @RequestMapping("detail")
    public String detail(HttpServletRequest request, Model model) {
        long id = Long.parseLong(request.getParameter("id"));
        String user_id = request.getParameter("userid");
        String path = request.getParameter("path");
        Map<String, Object> map = memberService.getDetailFromWx(id);
        model.addAllAttributes(map);
        model.addAttribute("path", path);
        model.addAttribute("user_id", user_id);

        if (path != null) {
            List<Map<String, Object>> visitRecordList = visitRecordService.getVisitRecord(id);
            model.addAttribute("visitRecords", visitRecordList);
        }
        return "/wx/page_details";
    }

    @RequestMapping("visit")
    @ResponseBody
    public String visit(HttpServletRequest request, Model model) {
        String result = "success";
        try {
            long member_id = Long.parseLong(request.getParameter("id"));
            agentMessageService.visitAgentMessage(member_id);
        } catch (Exception e) {
            e.printStackTrace();
            result = "failure";
        }
        return result;
    }

    @RequestMapping("to_add_visit_record")
    public String toAddVisitRecord(long id, String userid, String path, Model model) {
        model.addAttribute("id", id)
                .addAttribute("user_id", userid)
                .addAttribute("path", path);
        return "/wx/visit_record";
    }

    @RequestMapping("add_visit_record")
    public String addVisitRecord(long id, String userid, String path,
                                 String situation, String remark, String dateStr) {
        try {
//            System.out.println("----");
            visitRecordService.save(id, situation, remark, Util.str2Date(dateStr, "yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/wx/detail.cs?id=" + id + "&path=" + path + "&userid=" + userid;
    }

    @RequestMapping("add_visit_record1")
    public String addVisitRecord1() {
        System.out.println("---");
        long id = 65;
        String path = "total";
        String userid = "lhg0";
        return "redirect:/wx/detail.cs?id=" + id + "&path=" + path + "&userid=" + userid;
    }

    @RequestMapping(value = "manage", produces = "text/plain;charset=utf-8;")
    public String manage(HttpServletRequest request, Model model) {
        Map<String, String> map = getCode(request, model, "manage");
        model.addAllAttributes(map);
        String user_id = request.getParameter("userid");
        if (user_id != null && !user_id.isEmpty()) model.addAttribute("user_id", user_id);
        return "/wx/index";
    }


    @RequestMapping(value = "total", produces = "text/plain;charset=utf-8;")
    public String total(HttpServletRequest request, Model model) {
        String user_id = request.getParameter("userid");
        int count = memberService.getTotalCountFromWx(user_id);
        List<Map<String, Object>> mapList = memberService.getTotalFromWx(user_id);
        model.addAttribute("list", mapList);
        model.addAttribute("count", count);
        model.addAttribute("user_id", user_id);
        return "/wx/total";
    }

    @RequestMapping(value = "query", produces = "text/plain;charset=utf-8;")
    public String query(HttpServletRequest request, Model model) {
        String user_id = request.getParameter("userid");
        String dateStr = request.getParameter("dateStr");
        int count = memberService.queryCountFromWx(user_id, dateStr);
        List<Map<String, Object>> mapList = memberService.queryFromWx(user_id, dateStr);
        model.addAttribute("list", mapList);
        model.addAttribute("count", count);
        model.addAttribute("user_id", user_id);
        return "/wx/query";
    }

    @RequestMapping(value = "today", produces = "text/plain;charset=utf-8;")
    public String today(HttpServletRequest request, Model model) {
        String user_id = request.getParameter("userid");
        int count = memberService.getTodayCountFromWx(user_id);
        List<Map<String, Object>> mapList = memberService.getTodayFromWx(user_id);
        model.addAttribute("list", mapList);
        model.addAttribute("count", count);
        model.addAttribute("user_id", user_id);
        return "/wx/today";
    }

    @RequestMapping(value = "today_visit", produces = "text/plain;charset=utf-8;")
    public String todayVisit(HttpServletRequest request, Model model) {
        String user_id = request.getParameter("userid");
        int count = memberService.getTodayCountFromWx(user_id, true);
        List<Map<String, Object>> mapList = memberService.getTodayFromWx(user_id, true);
        model.addAttribute("list", mapList);
        model.addAttribute("count", count);
        model.addAttribute("user_id", user_id);
        return "/wx/today_visit";
    }

    @RequestMapping(value = "today_not_visit", produces = "text/plain;charset=utf-8;")
    public String todayNotVisit(HttpServletRequest request, Model model) {
        String user_id = request.getParameter("userid");
        int count = memberService.getTodayCountFromWx(user_id, false);
        List<Map<String, Object>> mapList = memberService.getTodayFromWx(user_id, false);
        model.addAttribute("list", mapList);
        model.addAttribute("count", count);
        model.addAttribute("user_id", user_id);
        return "/wx/today_not_visit";
    }

    @RequestMapping(value = "get_balance", produces = "text/plain;charset=utf-8;")
    public String getBalance(HttpServletRequest request, Model model) {
        try {
            Map<String, String> map = getCode(request, model,"getBalance");
            Agent agent = agentService.getAgent(map.get("user_id"));
            model.addAttribute("money", agent == null ? 0 : agent.getMoney());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "/wx/balance";
    }

    @RequestMapping(value = "to_re_add", produces = "text/plain;charset=utf-8;")
    public String toReAdd(HttpServletRequest request, Model model) {
        Map<String, String> map = getCode(request, model,"toReAdd");
        String user_id = map.get("user_id");
        if (user_id != null) {
            Agent agent = agentService.getAgent(user_id);
            model.addAttribute("agent", agent);
        } else {
            model.addAttribute("agent", null);
        }
        model.addAllAttributes(map);
        return "/wx/proxy_address_add";
    }

    @RequestMapping(value = "agent_add", produces = "text/plain;charset=utf-8;")
    @ResponseBody
    public String agentAdd(HttpServletRequest request, Model model) {
        String result = "代理已成功添加，请等待运营认证";
        try {
            String addr = request.getParameter("addr");
            String user_id = request.getParameter("user_id");
            result = agentService.reAddAgent(user_id, addr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

//https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx6b9738b957e2c065&redirect_uri=http://192.168.50.120:8080/wx/to_submit.cs&response_type=code&scope=snsapi_base&agentid=0&state=123346#wechat_redirect