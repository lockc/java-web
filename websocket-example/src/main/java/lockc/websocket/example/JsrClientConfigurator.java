package lockc.websocket.example;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.websocket.ClientEndpointConfig.Configurator;

public class JsrClientConfigurator extends Configurator {

	@Override
	public void beforeRequest(Map<String, List<String>> headers) {
		super.beforeRequest(headers);
		headers.put("X-Application", Arrays.asList("whoo hoow"));
		headers.put("X-Authentication", Arrays.asList("<<session token>>="));
		headers.put("X-IP", Arrays.asList("10.2.50.120"));        
    }
	
}