package lockc.websocket.example;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.websocket.Session;

public class MessageSender {

	public static List<Session> SESSIONS = new CopyOnWriteArrayList<Session>(); 
	
	
	public void start() {
		
		System.out.println("Starting message sender.");
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(;;) {
					for(Session session : SESSIONS) {
						try {
							session.getBasicRemote().sendText("some text I am sending you.");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
					}
				}
				
				
			}
		});
		
		t.start();
	}
}
