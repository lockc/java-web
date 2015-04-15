package sandbox.lockc.jaxb;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public Event createEvent() {
        return new Event();
    }
}
