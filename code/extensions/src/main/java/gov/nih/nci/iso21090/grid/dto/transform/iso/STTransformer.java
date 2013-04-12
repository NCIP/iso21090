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

import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.ST;

/**
 * Transforms strings.
 */
public final class STTransformer extends AbstractTransformer<ST, St> implements Transformer<ST, St> {

    /**
     * Public singleton.
     */
    public static final STTransformer INSTANCE = new STTransformer();

    private STTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public ST toXml(St input) {
        if (input == null) {
            return null;
        }
        ST x = new ST();
        String v = input.getValue();
        if (v != null) {
            x.setValue(v);
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public St toDto(ST input) {
        if (input == null) {
            return null;
        }
        St d = new St();
        String v = input.getValue();
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
    public ST[] createXmlArray(int size) throws DtoTransformException {
        return new ST[size];
    }
}
