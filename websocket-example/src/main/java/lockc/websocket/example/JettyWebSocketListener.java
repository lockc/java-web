package lockc.websocket.example;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

public class JettyWebSocketListener implements WebSocketListener {

	@Override
	public void onWebSocketBinary(byte[] payload, int offset, int len) {
		// TODO Auto-generated method stub
		System.out.println("onWebSocketBinary");
	}

	@Override
	public void onWebSocketClose(int statusCode, String reason) {
		// TODO Auto-generated method stub
		System.out.println("onWebSocketClose");
	}

	@Override
	public void onWebSocketConnect(Session session) {
		// TODO Auto-generated method stub
		System.out.println("onWebSocketConnect");
	}

	@Override
	public void onWebSocketError(Throwable cause) {
		// TODO Auto-generated method stub
		System.out.println("onWebSocketError");
	}

	@Override
	public void onWebSocketText(String message) {
		System.out.println(message);
	}

}
