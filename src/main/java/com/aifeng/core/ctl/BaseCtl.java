package com.aifeng.core.ctl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.aifeng.constants.Constants;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import com.aifeng.util.StringUtil;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 
 * @ClassName: BaseCtl
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月19日 下午11:04:42
 */
public class BaseCtl {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    private HttpSession session;
	
    protected final String MENU_CODE = "menuCode";
    
    public String AJAX_RETURN = "common/ajax_return";
    
    public String AJAX_SUCCESS = "{\"code\":200,\"data\":\"\"}";
    
//    public JSONObject AJAX_SUCCESS = JSON.parseObject("{code:200,data:\"\"}");
    
	protected static final SerializerFeature[] features = { 
			SerializerFeature.WriteMapNullValue, // 输出空置字段 
	        SerializerFeature.WriteNullListAsEmpty, // list字段如果为null，输出为[]，而不是null 
	        SerializerFeature.WriteNullNumberAsZero, // 数值字段如果为null，输出为0，而不是null 
	        SerializerFeature.WriteNullBooleanAsFalse, // Boolean字段如果为null，输出为false，而不是null 
	        SerializerFeature.WriteNullStringAsEmpty, // 字符类型字段如果为null，输出为""，而不是null 
	       // SerializerFeature.WriteDateUseDateFormat
	}; 
	
	
	@InitBinder    
	public void initBinder(WebDataBinder binder) {    
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	        dateFormat.setLenient(false);    
	        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));    
	} 
	
	/**
	 * 
	 * @Title: init 
	 * @Description: TODO
	 * @return: void
	 */
    private void init(){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)requestAttributes;
        request = sra.getRequest();
        try{
            Object user = session.getAttribute(Constants.SESSION_USER);
            if(user == null || session == null) session = request.getSession(true);
            else session = request.getSession(false);
        }catch (Exception e) {
            session = request.getSession(true);
        }
    }
	
	/**
	 * 
	 * @Title: getBeanFromJson
	 * @Description: TODO
	 * @param jsonStr
	 * @param clazz
	 * @param datePattern
	 * @return
	 * @return: T
	 */
	protected <T> T getBeanFromJson(String jsonStr, Class<T> clazz, String datePattern) {
		if (StringUtil.isNotBlank(jsonStr)) {
			try {
				ObjectMapper objectMapper = new ObjectMapper();
				if (StringUtils.isNotEmpty(datePattern)) {
					DeserializationConfig cfg = objectMapper.getDeserializationConfig()
							.withDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
					objectMapper.setDeserializationConfig(cfg);
				}
				return objectMapper.readValue(jsonStr, clazz);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 从json 串获取对象
	 * 
	 * @Title: getBeanFromJson
	 * @Description: TODO
	 * @param jsonStr
	 * @param typeReference
	 * @return
	 * @return: T
	 */
	protected <T> T getBeanFromJson(String jsonStr, TypeReference<T> typeReference) {

		if (StringUtil.isNotBlank(jsonStr)) {
			try {
				return new ObjectMapper().readValue(jsonStr, typeReference);
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * @param jsonStr
	 * @param clazz
	 * @return
	 */
	protected <T> T getBeanFromJson(String jsonStr, Class<T> clazz) {
		return getBeanFromJson(jsonStr, clazz, null);
	}

	/**
	 * 
	 * @Title: getMapFromJson
	 * @Description: TODO
	 * @param jsonStr
	 * @return
	 * @return: Map<String,String>
	 */
	protected Map<String, String> getMapFromJson(String jsonStr) {
		return getBeanFromJson(jsonStr, new TypeReference<Hashtable<String, String>>() {
		});
	}

	/**
	 * 
	 * @Title: getJson
	 * @Description: TODO
	 * @param ret
	 * @param result
	 * @param msg
	 * @return
	 * @return: JSONObject
	 */
	public JSONObject getJson(int ret, Object result, String msg) {
		JSONObject json = new JSONObject();
		json.put("ret", ret);
		json.put("data", result);
		json.put("msg", msg);
		return json;
	}
	
    /**
     * 将内容放入session
     * @param key
     * @param value
     */
    public void set(String key,Object value){
        init();
        session.setAttribute(key,value);
    }

    public Object get(String key){
        init();
        return session.getAttribute(key);
    }
    
    @ExceptionHandler
    public String exception(Exception ex,ModelMap mm) {
		mm.put("error",ex);
        return "error";
    }
    
    

}
