package com.aifeng.notify.ctl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;

import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.notify.EndPointServer;
import com.aifeng.util.DateUtil;
import com.aifeng.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/notify")
public class NotifyCtl extends BaseCtl {

	
    /**
     * 广播发送消息
     * @param modelMap	
     * @param session
     * @param msg
     * @return
     */
    @RequestMapping(value="/toBroadCastMsg",method = RequestMethod.POST)
    @ResponseBody
    public String toBroadCastMsg(String data, ModelMap modelMap){
    	Map<String,String> map = getMapFromJson(data);
    	String msg = map.get("msg");
        if (StringUtil.isNotBlank(msg)) {
            for (EndPointServer endPointServer : EndPointServer.sessionList) {
                try {
                	   	JSONObject jo = new JSONObject();
                	   	jo.put("type", 1);
                	   	jo.put("data", msg);
                	   	jo.put("pushTime", DateUtil.formatTime(Calendar.getInstance().getTime()));
                       endPointServer.session.getBasicRemote().sendText(jo.toJSONString());
                       modelMap.put("msg", 1);//发送成功
                   } catch (IOException e) {
                       // TODO Auto-generated catch block
                       modelMap.put("msg", 0);//发送失败
                       e.printStackTrace();
                   }           
           }
        }else {
            modelMap.put("msg", -1);//参数错误
       }

        return AJAX_SUCCESS;
    }
	
}
