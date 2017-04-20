package com.aifeng.notify;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;
import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.aifeng.core.util.SpringUtil;
import org.apache.log4j.Logger;
import com.aifeng.notify.service.INotifyService;
import com.aifeng.notify.service.impl.NotifyService;

@ServerEndpoint(value="/notify",configurator=GetHttpSessionConfigurator.class)
public class EndPointServer {

	Logger log = Logger.getLogger(EndPointServer.class);
	
	private static final String GUEST_PREFIX = "Guest_";
	private static final AtomicInteger connectionIds = new AtomicInteger(0);  
	
	
    public static  List<EndPointServer>  sessionList= new CopyOnWriteArrayList<EndPointServer>();
    public Session  session ;
    
    private final String nickname; 
    
	INotifyService notifyService = SpringUtil.getBean(NotifyService.class);
	
	
	public EndPointServer(){
		nickname = GUEST_PREFIX + connectionIds.getAndIncrement();  
	}
	 
     @OnOpen
     public void onOpen(Session session,EndpointConfig config) throws IOException {
         //以下代码省略...
    	log.info("用户 : " + nickname + " id : " + session.getId() + " 链接");
        this.session = session;
        sessionList.add(this);
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        httpSession.setAttribute("scoketSessionId", session.getId()); 
    }

     @OnMessage
     public void onMessage(Session session,String message) {
    	 System.out.println(message);
    	 notifyService.notifyAll(message);
     }

     @OnError
     public void onError(Throwable t) {
         //以下代码省略...
    	 log.error("websocket 连接， 用户 : " + nickname + " id : " + session.getId() + " 出错了");
     }

     @OnClose
     public void onClose(Session session, CloseReason reason) {
         //以下代码省略...
         sessionList.remove(this);
         log.info("用户 : " + nickname + " id : " + session.getId()  + "退出链接");
     } 
     
     

}