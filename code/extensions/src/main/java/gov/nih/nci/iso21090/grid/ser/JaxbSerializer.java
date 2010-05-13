package gov.nih.nci.iso21090.grid.ser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.axis.Constants;
import org.apache.axis.encoding.SerializationContext;
import org.apache.axis.encoding.Serializer;
import org.apache.axis.wsdl.fromJava.Types;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;

/**
 * JAXB based serializer.
 *
 * @author gax
 */
public class JaxbSerializer implements Serializer {

    private static final long serialVersionUID = -3204978709399586948L;
    private static final Map<String, JAXBContext> MAP = new HashMap<String, JAXBContext>();

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "PMD.PreserveStackTrace", "PMD.AvoidPrintStackTrace" })
    public void serialize(QName name, Attributes attributes, Object value, SerializationContext context)
            throws IOException {
        try {
            JAXBContext jaxbContext = MAP.get(value.getClass().getPackage().getName());
            if (jaxbContext == null) {           
                jaxbContext = JAXBContext.newInstance(value.getClass().getPackage().getName());
                MAP.put(value.getClass().getPackage().getName(), jaxbContext);
            }
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(value, new Filter(context));

        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    public String getMechanismType() {
        return Constants.AXIS_SAX;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "unchecked", "PMD.SignatureDeclareThrowsException" })
    public Element writeSchema(final Class javaType, final Types types) throws Exception {
        return null;
    }
}
