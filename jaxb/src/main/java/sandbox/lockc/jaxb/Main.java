package sandbox.lockc.jaxb;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class Main {

	
	/**
	 * @param args
	 * @throws JAXBException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws JAXBException, IOException {
		
		JAXBContext ctx = JAXBContext.newInstance(Event.class.getPackage().getName());
		
		
		Event e = new Event();
		Map<String, String> metadata = new HashMap<String, String>();
		metadata.put("chris", "lock");
		metadata.put("tom", "bart");
		e.setMetadata(metadata);
		
		StringWriter writer = new StringWriter();
		
		ctx.createMarshaller().marshal(e, writer);
		
		writer.flush();
		String text = writer.toString();
		writer.close();
		System.out.println(text);
		
//		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><event><metadata><tom>bart</tom><chris>lock</chris></metadata></event>";
		String xml = text;
		Event e2 = (Event) ctx.createUnmarshaller().unmarshal(new StringReader(xml));
		ctx.createMarshaller().marshal(e2, System.out);
		
	}

}
