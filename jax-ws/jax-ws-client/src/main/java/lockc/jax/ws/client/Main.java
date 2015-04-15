package lockc.jax.ws.client;

import lockc.jax.ws.simple.EchoService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("echo-service-context.xml");
		EchoService service = (EchoService) context.getBean("echoService");
		
		String response = service.echo("testing 1 2 3");
		System.out.println(response);
		
		
	}

}
