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

import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

/**
 * Transforms real numbers.
 * @author mshestopalov
 */
public final class REALTransformer extends QTYTransformer<org.iso._21090.Real, Real>
    implements Transformer<org.iso._21090.Real, Real> {

    /**
     * Public singleton.
     */
    public static final REALTransformer INSTANCE = new REALTransformer();

    private REALTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected org.iso._21090.Real newXml() {
        return new org.iso._21090.Real();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Real newDto() {
        return new Real();
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Real toXml(Real input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        org.iso._21090.Real x = transformBaseXml(input);
        Double v = input.getValue();
        if (v != null) {
            x.setValue(v.doubleValue());
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }

        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Real toDto(org.iso._21090.Real input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Real d = transformBaseDto(input);
        Double v = input.getValue();
        if (v != null) {
            d.setValue(v);
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Real[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.Real[size];
    }


}
