package com.aifeng.notify.service;

import java.util.List;

public interface INotifyService {

	
	public List<String> getAllOnline();
	
	/**
	 * 向用户发送信息
	 * @param sid
	 * @param msg
	 * @return
	 */
	public boolean sendMsg(String sid, String msg);
	
	
	public boolean notifyAll(String msg);
	
	
	
}
