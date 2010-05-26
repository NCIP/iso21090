package gov.nih.nci.iso21090.grid.ser;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

public class JaxbContextManager {
    
    private static Map<String, JAXBContext> contexts = 
        Collections.synchronizedMap(new HashMap<String, JAXBContext>());

    private JaxbContextManager() {
        // nothing to see here
    }
    
    
    public static JAXBContext getContextForPackage(String packageName) throws JAXBException {
        JAXBContext jaxbContext = contexts.get(packageName);
        if (jaxbContext == null) {           
            jaxbContext = JAXBContext.newInstance(packageName);
            contexts.put(packageName, jaxbContext);
        }
        return jaxbContext;
    }
}
