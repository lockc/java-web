package lockc.jax.ws.simple;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface EchoService {

	@WebMethod
	String echo(String message);
}
