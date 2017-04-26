package com.aifeng.mgr.upload;


import com.aifeng.constants.Constants;
import com.aifeng.constants.ImgPath;
import com.aifeng.core.ctl.BaseCtl;
import com.aifeng.util.FileUtil;
import com.aifeng.util.PropUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequestMapping("/file")
public class FileCtl extends BaseCtl {
	
	
    @RequestMapping(value="uploadPic", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPic( ModelMap mm, HttpServletRequest request,  @RequestParam(required = false) MultipartFile Filedata){
        String fileName = "";
        JSONObject json = new JSONObject();
        String fileType = request.getParameter("fileType").toString();
        String fileUrl = Constants.HTTP_IMG_PREFIX + ImgPath.newsDescPath;
        if(Filedata != null){
            try {
            	if(fileType.equals("100")){//商品信息图片上传
           		 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.goods"), Filedata)[0] ; 
            	}else if(fileType.equals("125")){//web端上传设计师作品主图位置
            		 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.atta"), Filedata)[0] ; 
            	}else if(fileType.equals("130")){//广告图片内容
           		 	 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.sysadv"), Filedata)[0] ; 
            	}else if(fileType.equals("140")){//案例管理--案例图片
          		 	 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.case"), Filedata)[0] ;
            	}else if(fileType.equals("150")){//商品分类管理--分类图标
         		 	 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.goodsclass"), Filedata)[0] ;
            	}else if(fileType.equals("160")){//用户管理
        		 	 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.avatar"), Filedata)[0] ; 
            	}else{
            		 fileName = FileUtil.uploadImageAndScale(PropUtil.getString("file.path.goods"), Filedata)[0] ; 
            	}
                json.put("fileName", fileName);
                json.put("name", fileName);
                json.put("path", fileName);
                json.put("thumb", fileName);
                json.put("rootPath", fileUrl);
                json.put("status", 1);
            } catch (IOException e) {
                json.put("fileName",  fileName);
                json.put("name",  fileName);
                json.put("path",  fileName);
                json.put("thumb", fileName);
                json.put("rootPath", fileUrl);
                json.put("status", 0);
                e.printStackTrace();
            } 
        } 
        return JSONObject.toJSONString(json, features);
    }
    
    /**
     * 上传文件
     * @Title: uploadFile 
     * @Description: TODO
     * @param mm
     * @param request
     * @param type
     * @param Filedata
     * @return
     * @return: String
     */
    @RequestMapping(value="uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile( ModelMap mm, HttpServletRequest request, @RequestParam(required = false) MultipartFile Filedata){
//    	public String uploadFile( ModelMap mm, HttpServletRequest request, int type,  @RequestParam(required = false) MultipartFile Filedata){
    	String fileName = "";
    	String fileType = request.getParameter("fileType").toString();
    	JSONObject json = new JSONObject();
    	String fileUrl = PropUtil.getString("url.file");
    	if(Filedata != null){
    		try {
    			fileName = FileUtil.uploadFile(FileUtil.getFilePath(Integer.valueOf(fileType)), Filedata) ; 
//    			fileName = FileUtil.uploadImageAndScale(PropUtil.getString("pic.path"), Filedata)[0] ; 
    			json.put("fileName", fileName);
    			json.put("name", fileName);
    			json.put("path", fileName);
    			json.put("thumb", fileName);
    			json.put("rootPath", fileUrl);
    			json.put("status", 1);
    		} catch (IOException e) {
    			json.put("fileName",  fileName);
    			json.put("name",  fileName);
    			json.put("path",  fileName);
    			json.put("thumb", fileName);
    			json.put("rootPath", fileUrl);
    			json.put("status", 0);
    			e.printStackTrace();
    		} 
    	} 
    	return JSONObject.toJSONString(json, features);
    }
	
	
	
}
