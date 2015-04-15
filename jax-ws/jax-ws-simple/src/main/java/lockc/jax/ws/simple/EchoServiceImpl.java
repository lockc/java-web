package lockc.jax.ws.simple;

import javax.jws.WebService;

@WebService(endpointInterface="lockc.jax.ws.simple.EchoService")
public class EchoServiceImpl implements EchoService {

	@Override
	public String echo(String message) {
		return message;
	}

	
}
