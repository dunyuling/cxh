package com.aifeng;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.MultipartPostMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import com.aifeng.util.AES;

public class HttpBase {
	
	protected String url;
	
	protected String plat ;
	protected String v ;
	protected String data;
	protected String timestamp;
	protected String tk;
	protected String result;
	
	

	private String getSign(String platform,String v,String data){
		String key = "cs_core";
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append("plat").append(platform);
		builder.append("v").append(v);
		builder.append("data").append(data);
		builder.append(key);
		System.out.println("builder--"+builder);
		return AES.getMd5(builder.toString());
	}
	
	public String getSign(String timestamp, String platform,String v,String data){
		String key = "cs_core";
		StringBuilder builder = new StringBuilder();
		builder.append(key);
		builder.append("timestamp").append(timestamp);
		builder.append("plat").append(platform);
		builder.append("v").append(v);
		builder.append("data").append(data);
//		builder.append("tk").append(tk);
		builder.append(key);
		System.out.println("builder--"+builder.toString());
		return AES.getMd5(builder.toString());
	}
	
	public void doReqWithParams(){
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new NameValuePair("plat", this.plat));
		pairs.add(new NameValuePair("v", this.v));
		pairs.add(new NameValuePair("data", this.data));
		pairs.add(new NameValuePair("timestamp", this.timestamp));
		this.tk = this.tk == null ? "":this.tk;
		pairs.add(new NameValuePair("tk", tk));
		String sign = getSign(this.timestamp, this.plat, this.v, this.data);
		pairs.add(new NameValuePair("sign", sign));
		System.out.println("5555--" + this.url);
		this.result = doClientPost(this.url, pairs.toArray(new NameValuePair[0]));
//		System.out.println(result);
	}
	
	public void doReqWithParams_Mul(){
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
		pairs.add(new NameValuePair("plat", this.plat));
		pairs.add(new NameValuePair("v", this.v));
		pairs.add(new NameValuePair("data", this.data));
		pairs.add(new NameValuePair("timestamp", this.timestamp));
		this.tk = this.tk == null ? "":this.tk;
		pairs.add(new NameValuePair("tk", tk));
		String sign = getSign(this.timestamp, this.plat, this.v, this.data);
		pairs.add(new NameValuePair("sign", sign));
		System.out.println("5555--" + this.url);
		this.result = doClientPost_Mul(this.url, pairs.toArray(new NameValuePair[0]));
//		System.out.println(result);
	}
	
	public String doClientPost(String url,NameValuePair[] pairs){
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实例
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		if (pairs != null) {
			postMethod.addParameters(pairs);  
		}
		// 执行postMethod
		try {
			//fiddler调试时使用
			//httpClient.getHostConfiguration().setProxy("127.0.0.1", 8888);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String body = postMethod.getResponseBodyAsString();
				postMethod.releaseConnection();
				return body;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public String doClientPost_Mul(String url,NameValuePair[] pairs){
		HttpClient httpClient = new HttpClient();
		// 创建POST方法的实例
		MultipartPostMethod postMethod = new MultipartPostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		if (pairs != null) {
//			postMethod.addParameter(parameterName, parameterValue);
			for(NameValuePair nvp : pairs){
				postMethod.addParameter(nvp.getName(), nvp.getValue());
			}
		}
		
		// 执行postMethod
		try {
			//fiddler调试时使用
			//httpClient.getHostConfiguration().setProxy("127.0.0.1", 8888);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String body = postMethod.getResponseBodyAsString();
				postMethod.releaseConnection();
				return body;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

	
	private String doHttpClientPost(){
		HttpClient httpClient = new HttpClient();
		String url = "http://mgr.wcsapi.biz.matocloud.com/fops";
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();
//		String bucket=Urlsafe_Base64_Encode();
		pairs.add(new NameValuePair("",""));
		// 创建POST方法的实例
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8"); 
		if (pairs != null) {
			postMethod.addParameters( pairs.toArray(new NameValuePair[0]));  
		}
		
		// 执行postMethod
		try {
			//fiddler调试时使用
			//httpClient.getHostConfiguration().setProxy("127.0.0.1", 8888);
			int statusCode = httpClient.executeMethod(postMethod);
			if (statusCode == HttpStatus.SC_OK) {
				String body = postMethod.getResponseBodyAsString();
				postMethod.releaseConnection();
				return body;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	

}
