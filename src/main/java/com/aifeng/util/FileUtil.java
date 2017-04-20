package com.aifeng.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @ClassName: FileUtil
 * @Description: TODO
 * @author: cosco gt.fan@msn.cn
 * @date: 2016年9月13日 下午9:57:22
 */
public class FileUtil {
	static Logger log = Logger.getLogger(FileUtil.class);

	private final static int BUFFER = 8192;
	
	/**
	 * 获取 webroot 路径
	 * @Title: getWebRootPath 
	 * @Description: TODO
	 * @return
	 * @return: String
	 */
	public static String getWebRootPath() {
		String resource = Thread.currentThread().getContextClassLoader().getResource("").getFile();
		File directory = new File(resource);
		String parent = directory.getParentFile().getParent();
		return parent;
	}
	
	/**
	 * 获取文件的扩展名 
	 * 例如： 1.png  返回 png 
	 * @Description: TODO
	 * @param file
	 * @return
	 * @return: String
	 */
	public static String getSuffix(MultipartFile file){
		if(file  == null) return null;
		return file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf(".") + 1).toLowerCase();
	}
	
	/**
	 * 
	 * @Title: inputStreamToFile 
	 * @Description: TODO
	 * @param ins
	 * @param file				生成的文件路径
	 * @throws IOException
	 * @return: void
	 */
	public static void inputStreamToFile(InputStream ins,File file) throws IOException{
		OutputStream os = new FileOutputStream(file);
    	int bytesRead = 0;
    	byte[] buffer = new byte[8192];
    	try {
			while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
				os.write(buffer, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    	os.close();
    	ins.close();
	}
	
	
	
	
	/**
	 * 
	 * @Title: downloadFile
	 * @Description: TODO
	 * @param theURL
	 * @param filePath
	 * @param fileName
	 * @return
	 * @return: boolean
	 */
	public static boolean download(URL theURL, String filePath, String fileName) {
		File dirFile = new File(filePath);
		if (!dirFile.exists()) {// 文件路径不存在时，自动创建目录
			dirFile.mkdir();
		}
		// 从服务器上获取图片并保存
		URLConnection connection;
		try {
			connection = theURL.openConnection();

			InputStream in = connection.getInputStream();
			FileOutputStream os = new FileOutputStream(filePath + fileName);
			byte[] buffer = new byte[4 * 1024];
			int read;
			while ((read = in.read(buffer)) > 0) {
				os.write(buffer, 0, read);
			}
			os.close();
			in.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
			return false;
		}

	}

	/**
	 * 
	 * @Title: copyTo
	 * @Description: TODO
	 * @param strSourceFileName
	 * @param strDestDir
	 * @return
	 * @return: boolean
	 */
	public static boolean copyTo(String strSourceFileName, String strDestDir) {
		File fileSource = new File(strSourceFileName);
		File fileDest = new File(strDestDir);

		// 如果源文件不存或源文件是文件夹
		if (!fileSource.exists() || !fileSource.isFile()) {
			log.debug("源文件[" + strSourceFileName + "],不存在或是文件夹!");
			return false;
		}

		// 如果目标文件夹不存在
		if (!fileDest.isDirectory() || !fileDest.exists()) {
			if (!fileDest.mkdirs()) {
				log.debug("目录文件夹不存，在创建目标文件夹时失败!");
				return false;
			}
		}

		try {
			String strAbsFilename = strDestDir + File.separator + fileSource.getName();

			FileInputStream fileInput = new FileInputStream(strSourceFileName);
			FileOutputStream fileOutput = new FileOutputStream(strAbsFilename);

			log.debug("开始拷贝文件");

			int count = -1;

			long nWriteSize = 0;
			long nFileSize = fileSource.length();

			byte[] data = new byte[BUFFER];

			while (-1 != (count = fileInput.read(data, 0, BUFFER))) {

				fileOutput.write(data, 0, count);

				nWriteSize += count;

				long size = (nWriteSize * 100) / nFileSize;
				long t = nWriteSize;

				String msg = null;

				if (size <= 100 && size >= 0) {
					msg = "\r拷贝文件进度:   " + size + "%   \t" + "\t   已拷贝:   " + t;
					log.debug(msg);
				} else if (size > 100) {
					msg = "\r拷贝文件进度:   " + 100 + "%   \t" + "\t   已拷贝:   " + t;
					log.debug(msg);
				}

			}

			fileInput.close();
			fileOutput.close();

			log.debug("拷贝文件成功!");
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 功 能: 移动文件(只能移动文件) 参 数: strSourceFileName: 是指定的文件全路径名 strDestDir:
	 * 移动到指定的文件夹中 返回值: 如果成功true; 否则false
	 * 
	 * @Title: moveFile
	 * @Description: TODO
	 * @param strSourceFileName
	 * @param strDestDir
	 * @return
	 * @return: boolean
	 */
	public static boolean moveFile(String strSourceFileName, String strDestDir) {
		if (copyTo(strSourceFileName, strDestDir))
			return delete(strSourceFileName);
		else
			return false;
	}

	/**
	 * 功 能: 删除指定的文件 参 数: 指定绝对路径的文件名 strFileName 返回值: 如果删除成功true否则false;
	 * 
	 * @param strFileName
	 * @return
	 */
	public static boolean delete(String strFileName) {
		File fileDelete = new File(strFileName);

		if (!fileDelete.exists() || !fileDelete.isFile()) {
			log.debug(strFileName + "不存在!");
			return false;
		}

		return fileDelete.delete();
	}

	/**
	 * 功 能: 创建文件夹 参 数: strDir 要创建的文件夹名称 返回值: 如果成功true;否则false
	 * 
	 * @param strDir
	 * @return
	 */
	public static boolean makeDir(String strDir) {
		File fileNew = new File(strDir);

		if (!fileNew.exists()) {
			return fileNew.mkdirs();
		} else {
			return true;
		}
	}

	/**
	 * 功 能: 删除文件夹 参 数: strDir 要删除的文件夹名称 返回值: 如果成功true;否则false
	 * 
	 * @param strDir
	 * @return
	 */
	public static boolean removeDir(String strDir) {
		File rmDir = new File(strDir);
		if (rmDir.isDirectory() && rmDir.exists()) {
			String[] fileList = rmDir.list();

			for (int i = 0; i < fileList.length; i++) {
				String subFile = strDir + File.separator + fileList[i];
				File tmp = new File(subFile);
				if (tmp.isFile())
					tmp.delete();
				else if (tmp.isDirectory())
					removeDir(subFile);
			}
			rmDir.delete();
		} else {
			return false;
		}
		return true;
	}
	
	/**
	 * 将Date类型的日期转换为系统参数定义的格式的字符串
	 * 
	 * @Title: format
	 * @Description: TODO
	 * @param aTs_Datetime
	 * @param as_Pattern
	 * @return
	 * @return: String
	 */
	public static String format(Date aTs_Datetime, DateStyle as_Pattern) {
		if (aTs_Datetime == null || as_Pattern == null)
			return null;

		SimpleDateFormat dateFromat = new SimpleDateFormat();
		dateFromat.applyPattern(as_Pattern.getValue());

		return dateFromat.format(aTs_Datetime);
	}
	
	public static String formatYeayMonth(Date aTs_Datetime) {
		return format(aTs_Datetime, DateStyle.YYYY_MM);
	}
	
	
	

}
