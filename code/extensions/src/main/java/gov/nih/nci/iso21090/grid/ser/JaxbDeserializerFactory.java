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

import javax.xml.namespace.QName;

import org.apache.axis.encoding.ser.BaseDeserializerFactory;

/**
 * Creates deserializers via factory method.
 * @author gax
 */
public class JaxbDeserializerFactory extends BaseDeserializerFactory {

    private static final long serialVersionUID = 8321286845635600390L;

    /**
     * @param javaType java type
     * @param xmlType xml type
     */
    @SuppressWarnings("unchecked")
    public JaxbDeserializerFactory(Class javaType, QName xmlType) {
        super(JaxbDeserializer.class, xmlType, javaType);
    }

}
