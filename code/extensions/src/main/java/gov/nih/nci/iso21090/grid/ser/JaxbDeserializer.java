//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.ser;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;

import org.apache.axis.encoding.DeserializationContext;
import org.apache.axis.encoding.Deserializer;
import org.apache.axis.encoding.DeserializerImpl;
import org.apache.axis.message.MessageElement;
import org.xml.sax.SAXException;

/**
 * JAXB based deserializer.
 *
 * @author gax
 */
public class JaxbDeserializer extends DeserializerImpl implements Deserializer {
    private static final long serialVersionUID = 6701906739176588187L;

    private final Class<?> javaType;

    /**
     * @param javaType java type this deserializes
     * @param xmlType not used
     */
    @SuppressWarnings("PMD.UnusedFormalParameter")
    public JaxbDeserializer(final Class<?> javaType, final QName xmlType) {
        super();
        this.javaType = javaType;
    }

    /**
     * {@inheritDoc} Return something even if no characters were found.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void onEndElement(String namespace, String localName, DeserializationContext context) throws SAXException {
        try {
            MessageElement msgElem = context.getCurElement();
            if (msgElem != null) {
                JAXBContext jc = JaxbContextManager.getContextForPackage(javaType.getPackage().getName());
                Unmarshaller unmarshaller = jc.createUnmarshaller();
                // Unmarshall the nested XML element into a jaxb object of type 'javaType'
                value = unmarshaller.unmarshal(msgElem.getAsDOM());
                if (value instanceof JAXBElement) {
                	JAXBElement jaxbElement = (JAXBElement)value;
                	value = jaxbElement.getValue();
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
            throw new SAXException(e);
        }
    }
}
