package lockc.websocket.example;

import javax.servlet.annotation.WebServlet;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

@SuppressWarnings("serial")
@WebServlet(name = "My WebSocket Servlet", urlPatterns = { "/hello/jetty" })
public class JettyWebSocketServlet extends WebSocketServlet {

	@Override
	public void configure(WebSocketServletFactory factory) {
		
		factory.getPolicy().setIdleTimeout(10000);
		factory.setCreator(new JettyWebSocketCreator());
        factory.register(JettyWebSocketListener.class);
        
	}

}
