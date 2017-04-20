package com.aifeng.util;

import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @ClassName: PropUtil 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年9月22日 下午11:30:55
 */
public class PropUtil {

    public static Properties properties = System.getProperties();

    static{
        try {
//            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("classpath:system.properties");
            properties.load(PropUtil.class.getClassLoader().getResourceAsStream("/system.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getString(String key){
        return properties.getProperty(key);
    }

    public static boolean getBoolean(String key){
        String value = properties.getProperty(key,"false");
        return Boolean.parseBoolean(value);
    }

    public static int getInt(String key){
        String value = properties.getProperty(key,"0");
        return Integer.parseInt(value);
    }
    
    public static long getLong(String key){
    	String value = properties.getProperty(key,"0");
    	return Long.valueOf(value);
    }
    
    public static double getDouble(String key){
    	String value = properties.getProperty(key,"0");
    	return Double.valueOf(value);
    }
    
    
    

	
}
