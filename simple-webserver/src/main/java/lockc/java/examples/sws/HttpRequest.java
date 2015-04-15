package lockc.java.examples.sws;

import java.util.HashMap;
import java.util.Map;

public class HttpRequest {

	private String method;
	private String uri;
	private String httpVersion;
	private Map<String, String> headers;
	private String messageBody;
	
	private HttpRequest(String method, String uri, String httpVersion,
			Map<String, String> headers, String messageBody) {
		this.method = method;
		this.uri = uri;
		this.httpVersion = httpVersion;
		this.headers = headers;
		this.messageBody = messageBody;
	}

	public String getMethod() {
		return method;
	}

	public String getUri() {
		return uri;
	}

	public String getHttpVersion() {
		return httpVersion;
	}

	public String getMessageBody() {
		return messageBody;
	}

	public String getHeader(String header) {
		return headers.get(header);
	}

	public static HttpRequest parse(String requestData) {
		
		
		
		String[] requestLines = requestData.split("\r\n");
		String requestLine = requestLines[0];  
		String[] requestLineSections = requestLine.split(" ");
		String method = requestLineSections[0];
		String uri = requestLineSections[1];
		String httpVersion = requestLineSections[2];
		
		String messageBody = null;
		Map<String, String> headers = new HashMap<String, String>();
		for(int x = 1; x < requestLines.length; x++) {
			String line = requestLines[x];
			if(!line.isEmpty()) {
				// a header
				String[] headerParts = line.split(":");
				headers.put(headerParts[0], headerParts[1]);
				continue;
			}
			break;
			
		}
		messageBody = requestLines[requestLines.length - 1];
		
		return new HttpRequest(method, uri, httpVersion, headers, messageBody);
	}
}
