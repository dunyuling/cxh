package com.aifeng.notify.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.aifeng.notify.EndPointServer;
import org.apache.log4j.Logger;
import com.aifeng.notify.service.INotifyService;
import com.aifeng.util.StringUtil;
import org.springframework.stereotype.Service;

@Service("notifyService")
public class NotifyService implements INotifyService {

	Logger log  = Logger.getLogger(NotifyService.class);
	
	@Override
	public List<String> getAllOnline() {
		// TODO Auto-generated method stub
		List<String> ids = new ArrayList<String>();
		for (EndPointServer endPointServer : EndPointServer.sessionList) {
			ids.add(endPointServer.session.getId());
		}
		return ids;
	}

	@Override
	public boolean sendMsg(String sid, String msg) {
		// TODO Auto-generated method stub

		if (StringUtil.isNotBlank(sid, msg)) {
			for (EndPointServer endPointServer : EndPointServer.sessionList) {
				if (sid.equals(endPointServer.session.getId())) {
					try {
						endPointServer.session.getBasicRemote().sendText(msg);
						return true;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						return true;
					}
				}

			}
		}
		return false;
	}

	@Override
	public boolean notifyAll(String msg) {
		// TODO Auto-generated method stub

		if (StringUtil.isNotBlank(msg)) {
			for (EndPointServer endPointServer : EndPointServer.sessionList) {
				try {
					endPointServer.session.getBasicRemote().sendText(msg);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			}
			return true;

		}
		return false;

	}

}
