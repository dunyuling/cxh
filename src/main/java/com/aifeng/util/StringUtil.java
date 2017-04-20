package com.aifeng.util;

import java.util.regex.Pattern;

/**
 * 字符串处理工具类
 * @ClassName: StringUtil 
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年8月19日 下午11:20:29
 */
public class StringUtil {

	/**
	 * 
	 * @Title: isBlank 
	 * @Description: 	验证字符串是否为空or null
	 * @param str		需要校验的字符串
	 * @return
	 * @return: boolean
	 */
    public static boolean isBlank(String str){
        return str == null || "null".equals(str) || "".equals(str) ? true:false;
    }
    
    /**
     * 
     * @Title: isNotBlank 
     * @Description: 验证字符串是否不为空 and null
     * @param str
     * @return
     * @return: boolean
     */
    public static boolean isNotBlank(String str){
    	return !isBlank(str);
    }
    
    /**
     * 
     * @Title: isBlank 
     * @Description: TODO
     * @param strs			要校验的字符串
     * @return
     * @return: boolean
     */
    public static boolean isBlank(String...strs){
    	for(String str : strs){
    		if(isBlank(str)){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 
     * @Title: isNotBlank 
     * @Description: TODO
     * @param strs
     * @return
     * @return: boolean
     */
    public static boolean isNotBlank(String...strs){
    	return !isBlank(strs);
    }
    
    /**
     * 
     * @Title: toInt 
     * @Description: 字符串转int 为空or null 返回0
     * @param str
     * @return
     * @return: int
     */
    public static int toInt(String str){
    	return isBlank(str)?0:Integer.valueOf(str);
    }
    
    /**
     * 
     * @Title: toLong 
     * @Description: TODO
     * @param str
     * @return
     * @return: long
     */
    public static long toLong(String str){
    	return isBlank(str)?0:Long.valueOf(str);
    }
    
    /**
     * 
     * @Title: toDouble 
     * @Description: TODO
     * @param str
     * @return
     * @return: double
     */
    public static double toDouble(String str){
    	return isBlank(str)?0:Double.valueOf(str);
    }
    
    
    /**
     * 
     * @Title: toDouble 
     * @Description: TODO
     * @param str
     * @return
     * @return: double
     */
    public static double toFloat(String str){
    	return isBlank(str)?0:Float.valueOf(str);
    }
    
	/**
	 * 验证email
	 * @param data
	 * @return
	 */
	public static boolean isEMail(String data){
        String regexp="^[-\\.\\w]+\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
        return isMatch(regexp, data);
	}
	
	/**
     * 判断是否符合电话号码，Telephone,由数字和－组成

     * @param str
     * @return
	 */
	public static boolean isPphone(String str){
        String regexp="^[0-9\\-]*$";
        return isMatch(regexp, str);
	}
	
	/**
     * 判断是否是邮编
     * @param str      例:333-3421 或 3333421
     * @param bsize    横杠前位数
     * @param asize    横杠后位数
     * @return
     */
    public static boolean isZipcode(String str, int bsize, int asize){
        try{
            String regEx = "^[0-9]{"+ (bsize + asize) +"}$";
            String regEx1 = "^[0-9]{"+ bsize +"}\\-[0-9]{"+ asize +"}$";
            if (Pattern.compile(regEx).matcher(str).find()) {
                return true;
            }else if(Pattern.compile(regEx1).matcher(str).find()){
                return true;
            }else{
                return false;
            }
        }catch(Exception e){
            return false;
        }
    }
    
    
    /**
     * 判断是否没有中文,,当然在其他语言的系统中，如果是日文、韩文也适用
     * @Title: isNoChinese 
     * @Description: TODO
     * @param str
     * @return
     * @return: boolean
     */
	public static boolean isNoChinese(String str){
		char[] c = str.toCharArray(); 
		for(int i=0;i<c.length;i++){
	    		if ((int)c[i] > 256){
	        		return false;
	    		}
	  	}
	  	return true;
	}
	
	/**
	 * 验证数字字符串的值在某一范围
	 * @param cValue  需验证的字符串
	 * @param startInt 
	 * @param endInt
	 * @return
	 */
	public static boolean isNumBetween(String cValue,int startInt,int endInt){
		try{ 
			 int i=Integer.parseInt(cValue);
			 if(i<=endInt && i>=startInt){
				 return true; 
			 }else{
				 return false;
			 } 
		}catch(Exception e){
			return false;
		}
		
	}
	
    /**
     * 检查数据是否符合正则表达式
     * @param regexp    正则表达式

     * @param data      数据
     * @return
     */
    public static boolean isMatch(String regexp, String data){
        boolean isOk = false;
        try{
            if(Pattern.compile(regexp).matcher(data).find()){
                isOk = true;
            }
        }catch(Exception e){
            isOk = false;
        }
        return isOk;
    }
    
    /**
     * Conver null.
     * 
     * @param obSource Object对象
     * 
     * @return 非NULL的字符串
     */
    public static String converNull(Object strSource){
        if(strSource == null)
            return "";
        else
            return strSource.toString();
    }
    
    
    /**
     * 取得字符串长度.
     * 
     * @param strSource 源字符串
     * 
     * @return 源字符串长度, 半角=1, 全角=2.
     */
    public static int getLenB(String strSource){
        int iLen = converNull(strSource).length();
        int iSize = 0;
        
        for(int i=0; i<iLen; i++){
            String strOneChar = strSource.substring(i, i+1);

            if(strOneChar.getBytes().length == 1){
                iSize += 1;
            }else{
                iSize += 2;
            }
        }
        
        return iSize;
    }
    
	/***
	 * 判断输入值是否是全角文字
	 * @param data
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean isFullChar(String data){
		boolean isFull = true;
        if(isNotBlank(data)){
    		int iLen = getLenB(data);
            if(iLen != data.length()*2){
                isFull = false;
            }
        }
		return isFull;
	}
    
    /**
     * 检查是否是半角文字
     * @param str
     * @return
     */
    public static boolean isHalfChar(String data){
        boolean isHalf = true;
        if(isNotBlank(data)){
            int iLen = getLenB(data);
            if(iLen != data.length()){
                isHalf = false;
            }
        }
        return isHalf;
    }
    
    /**
     * 替换特殊字符(" , ')
     * @param strOld
     * @return
     */
    public static String replaceSpecialChar(Object strOld){
        if(strOld != null){
            String strNew = converNull(strOld);
            strNew = strNew.replace(",", "\\,");
            strNew = strNew.replace("\"", "\\\"");
            strNew = strNew.replace("'", "\\'");
            strNew = strNew.replace(":", "\\:");
            strNew = strNew.replace("[", "\\[");
            strNew = strNew.replace("]", "\\]");
            strNew = strNew.replace("{", "\\{");
            strNew = strNew.replace("}", "\\}");
            strNew = strNew.replace("\r", "\\r");
            strNew = strNew.replace("\n", "\\n");
            return strNew;
        }else{
            return "";
        }
    }
    
    /**
     * 将字符串中的特殊字符转换成HTML编码
     * @param strOld
     * @return
     */
    public static String replaceSpCharToHTMLChar(Object strOld){
        if(strOld != null){
            String strNew = converNull(strOld);
            strNew = strNew.replace("'", "&#39;");
            strNew = strNew.replace("\"", "&#34;");
            return strNew;
        }else{
            return "";
        }
    }
    
    

	
}
