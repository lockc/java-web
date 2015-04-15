package sandbox.lockc.jaxb;

import java.util.LinkedList;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;

public class Metadata {

    @SuppressWarnings("rawtypes")
    @XmlAnyElement
    public LinkedList<JAXBElement> metadata = new LinkedList<JAXBElement>();

}
