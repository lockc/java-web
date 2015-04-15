package lockc.java.examples.sws;

import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleHttpServer {

	private final ServerSocketListener serverSocketListener;
	private final ExecutorService workerPool;
	private ExecutorService internalPool = Executors.newFixedThreadPool(2);
	private volatile boolean shutdownHook = false;
	
	public SimpleHttpServer(int port, int maxQueueSize, int poolsize) throws IOException {
		serverSocketListener = new ServerSocketListener(port, maxQueueSize);
		workerPool = Executors.newFixedThreadPool(poolsize);
	}
	
	public void start() throws Exception {
		/*
		 * Start listening for connections to this server
		 */
		internalPool.execute(serverSocketListener);
		/*
		 * Start processing connections as they become available
		 */
		while(true) {
			
			if(shutdownHook) {
				serverSocketListener.stop();
				break;
			}
			
			try {
				Socket socket = serverSocketListener.getNextRequest();
				System.out.println("Sooket received: " + socket.getLocalPort() + " " + socket.getPort());
				dispatch(socket);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
	
	public void stop() throws IOException {
		shutdownHook = true;
		serverSocketListener.stop();
		internalPool.shutdownNow();
		workerPool.shutdownNow();
	}
	
	private void dispatch(final Socket socket) {
		workerPool.execute(new Runnable() {
			public void run() {
				byte[] buffer = new byte[4096];
				
				try {
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					
					int totalRead = 0;
					while(true) {
						try {
							int len = 1024;
							int off = totalRead ==  0 ? 0 : totalRead - 1;
							
							// extend the buffer is required
							if(buffer.length - totalRead <= len) {
								byte[] tmp = new byte[buffer.length + len];
								buffer = tmp;
								tmp = null;
							}
							
							int b = dis.read(buffer, off, len);
							totalRead = totalRead + b;
							if(b == -1) {
								break;
							}
						} catch(SocketTimeoutException ste) {
							break;
						}
						
					}
					
					if(totalRead > 0) {
						String requestData = new String(buffer);
						System.out.println("Sooket: " + socket.getLocalPort() + " " + socket.getPort() + "\r\nTotal Read: " + totalRead + "\r\nRequest Data: \r\n" + requestData);
						HttpRequest request = HttpRequest.parse(requestData);
						socket.getOutputStream().write("<html><body>Hello World!</body></html>".getBytes());
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if(!socket.isClosed()) {
						try {
							socket.close();
						} catch (IOException e) {
							// Swallow the exception but log it 
							System.err.println(e.getMessage());
						}
					}
				}
				
			}
		});
	}

}
