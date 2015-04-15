package lockc.jax.rs.jersey.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("echo")
public class EchoResource {

	public EchoResource() {
		System.out.println(">>>>>>>>>>>> EchoResource <<<<<<<<<<<<");
	}
	
	@GET
	@Produces(value="text/plain")
	public String echoQuery(@QueryParam(value = "message") String message) {
		System.out.println(message);
		return message;
	}
}
