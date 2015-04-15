package sandbox.lockc.jaxb;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
public class Event {

    private Map<String, String> metadata;
    
    public Event() {
        metadata = new HashMap<String, String>();
    }
    
    @XmlJavaTypeAdapter(MetadataAdapter.class)
    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
    
    
}
