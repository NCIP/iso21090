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

import java.io.IOException;

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

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({ "PMD.PreserveStackTrace", "PMD.AvoidPrintStackTrace" })
    public void serialize(QName name, Attributes attributes, Object value, SerializationContext context)
            throws IOException {
        try {
            JAXBContext jaxbContext = JaxbContextManager.getContextForPackage(value.getClass().getPackage().getName());
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(value, new Filter(context));
        } catch (Exception e) {
            e.printStackTrace();
            IOException ioe = new IOException(e.getMessage());
            ioe.initCause(e);
            throw ioe;
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
