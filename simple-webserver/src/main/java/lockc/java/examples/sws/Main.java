package lockc.java.examples.sws;

import lockc.java.examples.sws.ssl.SimpleHttpsServer;

public class Main {

	public static void main(String[] args) throws Exception {
//		SimpleHttpServer http = new SimpleHttpServer(8080, 10, 5);
//		http.start();
		
		SimpleHttpsServer https = new SimpleHttpsServer(8443, 10, 5);
		https.start();
		
		
//		Thread.sleep(15000);
//		
//		http.stop();
	}

}
