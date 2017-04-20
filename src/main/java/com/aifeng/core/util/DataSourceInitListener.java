package com.aifeng.core.util;
/**   
* @Title: DataSourceInitListener.java 
* @Package org.cosco.wechat.listener 
* @Description: TODO(用一句话描述该文件做什么) 
* @author cosco gt.fan@msn.cn   
* @date 2016年3月8日 下午6:18:21 
* @version   
*/ 

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/** 
 * 当spring容器初始化完成后就会执行该方法
* @ClassName: DataSourceInitListener 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author cosco gt.fan@msn.cn 
* @date 2016年3月8日 下午6:18:21 
*  
*/
public class DataSourceInitListener implements ApplicationListener<ContextRefreshedEvent> {

	/** (非 Javadoc) 
	* <p>Title: onApplicationEvent</p> 
	* <p>Description: </p> 
	* @param arg0 
	* @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent) 
	*/ 
	@Override
	public void onApplicationEvent(ContextRefreshedEvent ev) {
		// TODO Auto-generated method stub
		
		//
		if(ev.getApplicationContext().getParent() == null){
			// execute my method
//			WxUtil.initAccessToken();  // 获取access_token
			System.out.println("DataSourceInitListener..................!");
		}
		
	}
	
	
	

}
