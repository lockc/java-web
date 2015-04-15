package lockc.websocket.example;

import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.eclipse.jetty.websocket.jsr356.ClientContainer;

public class ClientApp {
	public static void main(String[] args) throws Exception {
		
		WebSocketContainer container = ContainerProvider.getWebSocketContainer(); 
		
		final String uri = "ws://localhost:8080/hello/jsr";
		
		Session session = container.connectToServer(JsrClientEndpoint.class, URI.create(uri));
		
		for(int x = 0; x < 100; x++) {
			session.getBasicRemote().sendText("err what!? " + x);
			Thread.sleep(1000);
		}
		
		
		( ( ClientContainer )container ).stop();
		
	}
}
