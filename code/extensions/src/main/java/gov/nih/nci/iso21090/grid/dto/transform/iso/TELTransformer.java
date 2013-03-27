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

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.iso21090.TelEmail;
import gov.nih.nci.iso21090.TelPerson;
import gov.nih.nci.iso21090.TelPhone;
import gov.nih.nci.iso21090.TelUrl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.net.URI;
import java.net.URISyntaxException;

import org.iso._21090.TEL;

/**
 * Transforms telecoms.
 */
public final class TELTransformer extends AbstractTransformer<org.iso._21090.TEL, Tel> implements Transformer<org.iso._21090.TEL, Tel> {

    /**
     * Public singleton.
     */
    public static final TELTransformer INSTANCE = new TELTransformer();

    private TELTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public TEL toXml(Tel input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        TEL x;
        if (input instanceof TelEmail) {
            x = new org.iso._21090.TelEmail();
        } else if (input instanceof TelPhone) {
            x = new org.iso._21090.TelPhone();
        } else if (input instanceof TelPerson) {
            x = new org.iso._21090.TELPerson();
        } else if (input instanceof TelUrl) {
            x = new org.iso._21090.TelUrl();
        } else {
            x = new TEL();
        }
        copyToXml(input, x);
        return x;
    }

    private static void copyToXml(Tel source, TEL target) {
        URI u = source.getValue();
        if (u != null) {
            target.setValue(u.toString());
        } else {
            target.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(source.getNullFlavor()));
        }
    }

    /**
     * {@inheritDoc}
     */
    public Tel toDto(TEL input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Tel d;
        if (input instanceof org.iso._21090.TelEmail) {
            d = new TelEmail();
        } else if (input instanceof org.iso._21090.TelPhone) {
            d = new TelPhone();
        } else if (input instanceof org.iso._21090.TELPerson) {
            d = new TelPerson();
        } else if (input instanceof org.iso._21090.TelUrl) {
            d = new TelUrl();
        } else {
            d = new Tel();
        }
        copyToDto(input, d);
        return d;
    }

    private static void copyToDto(TEL source, Tel target) throws DtoTransformException {
        String v = source.getValue();
        if (v != null) {
            try {
                target.setValue(new URI(v));
            } catch (URISyntaxException ex) {
                throw new DtoTransformException("error converting " + source.getClass().getSimpleName(), ex);
            }
        } else {
            target.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(source.getNullFlavor()));
        }
    }

    /**
    * {@inheritDoc}
    */
    public TEL[] createXmlArray(int size) throws DtoTransformException {
        return new TEL[size];
    }

}
