package lockc.websocket.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.websocket.jsr356.server.ServerContainer;
import org.eclipse.jetty.websocket.jsr356.server.deploy.WebSocketServerContainerInitializer;

public class ServerStarter {
	public static void main(String[] args)
    {
        Server server = new Server(8080);
        
        try
        {

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            
            server.setHandler(context);
            
            // Initialize the JSR-356 layer
            ServerContainer container = WebSocketServerContainerInitializer.configureContext(context);
            // Manually add the endpoint
            container.addEndpoint(JsrServerEndpoint.class);
            
            server.start(); // start server
            server.join(); // wait for server to stop
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

