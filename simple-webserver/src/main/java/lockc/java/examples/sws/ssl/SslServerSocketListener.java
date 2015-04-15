package lockc.java.examples.sws.ssl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class SslServerSocketListener implements Runnable {

	private final ServerSocket serverSocket;
	private final LinkedBlockingQueue<Socket> requestQueue;
	private volatile boolean shutdownHook = false;
	
	public SslServerSocketListener(int port, int maxQueueSize) throws IOException, SslException {
		SSLContext sslContext = initialiseSslContext();
		serverSocket = sslContext.getServerSocketFactory().createServerSocket(port);
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
	
	private SSLContext initialiseSslContext() throws SslException {
		char[] passphrase = "pa55word".toCharArray();
		
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		SSLContext context = null;
		try {
			KeyStore keyStoreKeys = KeyStore.getInstance("JKS");
			keyStoreKeys.load(loader.getResourceAsStream("keystore.jks"), passphrase);
			
			KeyStore keyStoreTrust = KeyStore.getInstance("JKS");
			keyStoreTrust.load(loader.getResourceAsStream("keystore.jks"), passphrase);
		
			KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
			kmf.init(keyStoreKeys, passphrase);
			
			TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
			tmf.init(keyStoreTrust);
			
			context = SSLContext.getInstance("SSL");
			context.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
		} catch (Exception e) {
			throw new SslException(e);
		}
		
		return context;
	}

}
