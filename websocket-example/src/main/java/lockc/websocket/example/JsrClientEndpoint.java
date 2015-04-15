package lockc.websocket.example;

import javax.websocket.ClientEndpoint;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

@ClientEndpoint(configurator=JsrClientConfigurator.class)
public class JsrClientEndpoint {

	@OnOpen
	public void onOpen(Session session )  {
		System.out.println("client onOpen executed.");
	}

	@OnMessage
	public void onMessage(String message, Session session) {
		System.out.println("client onMessage executed: " + message);
	}
	
	@OnError
	public void onError(Throwable error, Session session) {
		System.out.println("client onError executed.");
	}
	
	@OnClose
	public void onClose(Session session) {
		System.out.println("client onClose executed.");
	}
	
}
