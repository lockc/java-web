package lockc.java.examples.sws;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.annotation.concurrent.ThreadSafe;
import javax.net.ServerSocketFactory;

@ThreadSafe
public class ServerSocketListener implements Runnable {

	private final ServerSocket serverSocket;
	private final LinkedBlockingQueue<Socket> requestQueue;
	private volatile boolean shutdownHook = false;
	
	public ServerSocketListener(int port, int maxQueueSize) throws IOException {
		serverSocket = ServerSocketFactory.getDefault().createServerSocket(port);
		requestQueue = new LinkedBlockingQueue<Socket>(maxQueueSize);
	}
	
	public void run() {
		while(true) {
			if(shutdownHook) {
				doShutdown();
				break;
			}
			try {
				Socket socket = serverSocket.accept();
				socket.setSoTimeout(10);
				InetAddress client = socket.getInetAddress();
		        System.out.println(client.getHostName() + " connected to server. " + socket.getPort() + "\n");
		        
		        if(!requestQueue.offer(socket)) {
		        	throw new RuntimeException("Server Busy!");
		        }
			} catch (IOException e) {
				if(shutdownHook) {
					System.out.println("Server was shutdown, no longer accepting connections.");
				} else {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void stop() throws IOException {
		shutdownHook = true;
	}
	
	public Socket getNextRequest() throws InterruptedException {
		return requestQueue.take();
	}
	
	private void doShutdown() {
		try {
			if(!serverSocket.isClosed()){
				serverSocket.close();
			}
		} catch (IOException e) {
			// swallow the error but log it.
			System.err.println(e.getMessage());
		}
		requestQueue.drainTo(new ArrayList<Socket>());
	}

}
