package lockc.websocket.example;

import javax.websocket.ContainerProvider;

import org.eclipse.jetty.websocket.servlet.ServletUpgradeRequest;
import org.eclipse.jetty.websocket.servlet.ServletUpgradeResponse;
import org.eclipse.jetty.websocket.servlet.WebSocketCreator;

public class JettyWebSocketCreator implements WebSocketCreator {

	@Override
	public Object createWebSocket(ServletUpgradeRequest req, ServletUpgradeResponse resp) {
		
		
		
		System.out.println("createWebSocket has been run, do some cool shit here!!!");
		return new JettyWebSocketListener();
	}

}
