//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Transforms Addresses.
 * 
 * TODO QSet<TS> transformation
 */
public final class ADTransformer extends AbstractTransformer<org.iso._21090.Ad, gov.nih.nci.iso21090.Ad> implements Transformer<org.iso._21090.Ad, gov.nih.nci.iso21090.Ad> {

    /**
     * Public singleton.
     */
    public static final ADTransformer INSTANCE = new ADTransformer();

    private ADTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ad toXml(Ad input) {
        if (input == null) {
            return null;
        }
        org.iso._21090.Ad x = new org.iso._21090.Ad();
        copyToXml(input, x);
        return x;
    }

    private static void copyToXml(gov.nih.nci.iso21090.Ad source, org.iso._21090.Ad target) {
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(source.getNullFlavor()));
        List<gov.nih.nci.iso21090.Adxp> sourcePart = source.getPart();
        if (sourcePart != null) {
            List<org.iso._21090.ADXP> targetPart = target.getParts(); // never return null
            for (gov.nih.nci.iso21090.Adxp p : sourcePart) {
                if (p != null) {
                    targetPart.add(ADXPTransformer.INSTANCE.toXml(p));
                }
            }
        }
        
     
    }

    /**
     * {@inheritDoc}
     */
    public Ad toDto(org.iso._21090.Ad input) throws DtoTransformException{
        if (input == null) {
            return null;
        }
        Ad d = new Ad();
        copyToDto(input, d);
        return d;
    }

    private static void copyToDto(org.iso._21090.Ad source, Ad target) throws DtoTransformException{
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(source.getNullFlavor()));
        List<org.iso._21090.ADXP> sourcePart = source.getParts(); // never returns null
        List<gov.nih.nci.iso21090.Adxp> targetPart = new ArrayList<gov.nih.nci.iso21090.Adxp>(sourcePart.size());
        target.setPart(targetPart);
        for (org.iso._21090.ADXP p : sourcePart) {
            if (p != null) {
                targetPart.add(ADXPTransformer.INSTANCE.toDto(p));
            }
        }
        
        
        
        
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ad[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.Ad[size];
    }
}
