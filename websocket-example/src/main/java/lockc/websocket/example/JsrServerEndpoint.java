package lockc.websocket.example;

import javax.websocket.CloseReason;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/hello/jsr", configurator=JsrServerConfigurator.class)
public class JsrServerEndpoint {

	@OnOpen
	public void onOpen(Session session, EndpointConfig endpointConfig) {
		System.out.println("onOpen executed.");
		
		
		
		MessageSender.SESSIONS.add(session);
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		System.out.println("onClose executed. " + closeReason);
	}
	
	@OnMessage()
	public void onMessage(Session session, String message) {
		System.out.println("onMessage executed: " + message);
	}
	
	@OnError
	public void onError(Session session, Throwable error) {
		System.out.println("onError executed." + error);
	}
	
}
