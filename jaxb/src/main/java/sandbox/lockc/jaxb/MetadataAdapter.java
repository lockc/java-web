package sandbox.lockc.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.namespace.QName;

import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;

public class MetadataAdapter extends XmlAdapter<Metadata, Map<String, String>> {

    @Override
    public Map<String, String> unmarshal(Metadata metadataMap) throws Exception {
        Map<String, String> metadata = new HashMap<String, String>();
        
        for(Object o : metadataMap.metadata) {
            ElementNSImpl element = (ElementNSImpl) o;
            String key = element.getNodeName();
            String value = element.getFirstChild().getTextContent();
            metadata.put(key, value);
        }
        
        return metadata;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Metadata marshal(Map<String, String> v) throws Exception {
        Metadata metadataMap = new Metadata();
        
        for(Map.Entry<String, String> entry : v.entrySet()) {
            metadataMap.metadata.add(new JAXBElement(new QName(entry.getKey()), String.class, entry.getValue()));
        }
        
        return metadataMap;
    }

}
