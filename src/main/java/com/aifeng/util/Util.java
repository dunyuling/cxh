package com.aifeng.util;

import com.aifeng.constants.Constants;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by pro on 17-3-14.
 */
public class Util {

    public static void mkDir(String path) {
        File file = new File(path);
        if (!file.exists())
            file.mkdirs();
    }

    static String key = "zhzj";

    public static String getSign(String timestamp, String platform, String v, String data) {
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
        String imgName = multipartFile.getOriginalFilename();
        String fullPath = imgRealPathDir + File.separator + imgName;

        System.out.println("logImageName：" + imgName);
        File file = new File(fullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return realPath + File.separator + imgName;

    }

    public static String uploadImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);

        MultipartFile multipartFile = multipartRequest.getFile("img");
        String imgName = multipartFile.getOriginalFilename();
        String fullPath = imgRealPathDir + File.separator + imgName;

        System.out.println("logImageName：" + imgName);
        File file = new File(fullPath);
        try {
            multipartFile.transferTo(file);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
        }
        return realPath + File.separator + imgName;
    }

    public static String[] uploadImgs(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();

        String[] paths = null;
        for (String key : multiValueMap.keySet()) {
            if (key.equals("product_slide")) {
                List<MultipartFile> multipartFiles = multiValueMap.get(key);
                int i = 0;
                paths = new String[multipartFiles.size()];
                for (MultipartFile multipartFile : multipartFiles) {
                    String imgName = multipartFile.getOriginalFilename();
                    String fullPath = imgRealPathDir + File.separator + imgName;

                    System.out.println("logImageName：" + imgName);
                    File file = new File(fullPath);
                    try {
                        multipartFile.transferTo(file);
                    } catch (IllegalStateException | IOException e) {
                        e.printStackTrace();
                    }
                    paths[i++] = realPath + File.separator + imgName;
                }
            }
        }
        return paths;
    }

    public static String[] editImgs(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        mkDir(imgRealPathDir);
        MultiValueMap<String, MultipartFile> multiValueMap = multipartRequest.getMultiFileMap();

        String[] paths = null;
        for (String key : multiValueMap.keySet()) {
            if (key.equals("product_slide")) {
                List<MultipartFile> multipartFiles = multiValueMap.get(key);
                int i = 0;
                paths = new String[multipartFiles.size()];
                for (MultipartFile multipartFile : multipartFiles) {
                    if (!multipartFile.isEmpty()) {
                        String imgName = multipartFile.getOriginalFilename();
                        String fullPath = imgRealPathDir + File.separator + imgName;

                        System.out.println("logImageName：" + imgName);
                        File file = new File(fullPath);
                        try {
                            multipartFile.transferTo(file);
                        } catch (IllegalStateException | IOException e) {
                            e.printStackTrace();
                        }
                        paths[i++] = realPath + File.separator + imgName;
                    } else {
                        paths[i++] = null;
                    }
                }
            }
        }
        return paths;
    }


    public static String editImg(HttpServletRequest request, String realPath) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        Util.mkDir(imgRealPathDir);

        String imgRelativePath = null;
        MultipartFile multipartFile = multipartRequest.getFile("img");
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fullPath = imgRealPathDir + File.separator + multipartFile.getOriginalFilename();

            File file = new File(fullPath);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            imgRelativePath = realPath + File.separator + multipartFile.getOriginalFilename();
        }
        return imgRelativePath;
    }

    public static String editImg(HttpServletRequest request, String realPath, String fileName) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        String imgRealPathDir = request.getSession().getServletContext().getRealPath(realPath);
        Util.mkDir(imgRealPathDir);

        String imgRelativePath = null;
        MultipartFile multipartFile = multipartRequest.getFile(fileName);
        if (!multipartFile.isEmpty()) {
            String fullPath = imgRealPathDir + File.separator + multipartFile.getOriginalFilename();

            File file = new File(fullPath);
            try {
                multipartFile.transferTo(file);
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
            imgRelativePath = realPath + File.separator + multipartFile.getOriginalFilename();
        }
        return imgRelativePath;
    }

    //TODO 删除这次没被选中的
    public Collection<Long> getToDelete(List<Long> old, List<Long> current) {
        Collection<Long> max, min;

        Collection<Long> toDel = new LinkedList<>();
        Collection<Long> toAdd = new LinkedList<>();

        if (old.size() > current.size()) {
            max = old;
            min = current;
        } else {
            max = current;
            min = old;
        }

        Map<Object, Long> map = new HashMap<>(max.size());
        for (Object obj : max) {

        }


        return null;
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
}