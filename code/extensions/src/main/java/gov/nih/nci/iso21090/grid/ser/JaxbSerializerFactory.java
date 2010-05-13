package gov.nih.nci.iso21090.grid.ser;

import javax.xml.namespace.QName;

import org.apache.axis.encoding.ser.BaseSerializerFactory;

/**
 * Serialization factory.
 * @author gax
 */
public class JaxbSerializerFactory extends BaseSerializerFactory {
    private static final long serialVersionUID = -3492705747528576886L;

    /**
     * @param javaType java type
     * @param xmlType xml type
     */
    @SuppressWarnings("unchecked")
    public JaxbSerializerFactory(Class javaType, QName xmlType) {
        super(JaxbSerializer.class, xmlType, javaType);
    }
}
