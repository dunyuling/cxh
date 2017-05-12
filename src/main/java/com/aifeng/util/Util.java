package com.aifeng.util;

import com.aifeng.constants.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pro on 17-3-14.
 */
public class Util {

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    public static String getSign(String timestamp, String platform, String v, String data) {
        String key = "cxh";
        StringBuilder builder = new StringBuilder();
        builder.append(key);
        builder.append("timestamp").append(timestamp);
        builder.append("plat").append(platform);
        builder.append("v").append(v);
        builder.append("data").append(data);
        //	builder.append("tk").append(tk);
        builder.append(key);
        System.out.println("builder--" + builder);
        //	return Md5.getMd5(builder.toString());
        return AES.getMd5(builder.toString());
    }

    /*public void doReqWithParams() {
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        pairs.add(new NameValuePair("plat", this.plat));
        pairs.add(new NameValuePair("v", this.v));
        pairs.add(new NameValuePair("data", this.data));
        pairs.add(new NameValuePair("timestamp", this.timestamp));
        this.tk = this.tk == null ? "" : this.tk;
        pairs.add(new NameValuePair("tk", tk));
        String sign = getSign(this.timestamp, this.plat, this.v, this.data);
        pairs.add(new NameValuePair("sign", sign));
        System.out.println("5555--" + this.url);
        this.result = doClientPost(this.url, pairs.toArray(new NameValuePair[0]));
        //	System.out.println(result);
    }*/
    public static String uploadImg(HttpServletRequest request, String realPath, String fileName) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile(fileName);
        String newName = getRandomName(multipartFile.getOriginalFilename());

        String fullPath = imgRealPathDir + File.separator + newName;
        System.out.println("logImageName：" + newName);
        File file = new File(fullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return realPath + File.separator + newName;
    }

    public static String uploadImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile("img");
        String newName = getRandomName(multipartFile.getOriginalFilename());

        String fullPath = imgRealPathDir + File.separator + newName;
        System.out.println("logImageName：" + newName);
        File file = new File(fullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return realPath + File.separator + newName;
    }

    public static String editImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        Util.mkDir(imgRealPathDir);

        String imgRelativePath = null;
        MultipartFile multipartFile = multipartRequest.getFile("img");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String newName = getRandomName(multipartFile.getOriginalFilename());

            String fullPath = imgRealPathDir + File.separator + newName;
            File file = new File(fullPath);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            imgRelativePath = realPath + File.separator + newName;
        }
        return imgRelativePath;
    }

    public static String getRandomName(String originalName) {
        String suffix = "";
        if (originalName.contains(".")) {
            suffix = originalName.substring(originalName.lastIndexOf("."), originalName.length());
        } else {
            suffix = ".png";
        }
        String generatedString = RandomStringUtils.random(6, true, true).toLowerCase();
        return generatedString + System.currentTimeMillis() + suffix;


    }


    public static String loadAccessTokenUrl(String corpid, String corpsecret) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?";
        return pre + "corpid=" + corpid + "&corpsecret=" + corpsecret;
    }

    public static String loadCodeUrl(String appid) {
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

    public static String loadUserIdUrl(String access_token, String code) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?";
        return pre + "access_token=" + access_token + "&code=" + code;
    }

    public static String loadSecondAuthUrl(String access_token, String userid) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/user/authsucc?";
        return pre + "access_token=" + access_token + "&userid=" + userid;
    }

    public static String loadSendMsgUrl(String access_token) {
        String pre = "https://qyapi.weixin.qq.com/cgi-bin/message/send?";
        return pre + "access_token=" + access_token;
    }

    public static boolean expire(long time1, long time2) {
        return time1 - time2 > 6900 * 1000; //为保证方便，有效期缩小五分钟
    }

    public static String date2String(Date date, String style) {
        SimpleDateFormat sdf = new SimpleDateFormat(style);
        //TimeZone gmt = TimeZone.getTimeZone("GMT+08:00");
        //sdf.setTimeZone(gmt);
        if (date != null) {
            return sdf.format(date);
        }
        return null;
    }

    public static Date str2Date(String str, String style) {
        DateFormat dateFormat = new SimpleDateFormat(style);
        Date date = null;
        try {
            date = dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}