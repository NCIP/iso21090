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
import gov.nih.nci.iso21090.StNt;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.ST;

/**
 * Transforms strings.
 */
public final class STNTTransformer extends AbstractTransformer<org.iso._21090.StNt, StNt> implements Transformer<org.iso._21090.StNt, StNt> {

    /**
     * Public singleton.
     */
    public static final STNTTransformer INSTANCE = new STNTTransformer();
    
    private STNTTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.StNt toXml(StNt input) {
        ST st = STTransformer.INSTANCE.toXml(input);
        if (st == null) {
            return null;
        }
        org.iso._21090.StNt stnt = new org.iso._21090.StNt();
        stnt.setValue(st.getValue());
        stnt.setNullFlavor(st.getNullFlavor());
        return stnt;
    }

    /**
     * {@inheritDoc}
     */
    public StNt toDto(org.iso._21090.StNt input) {
        St st = STTransformer.INSTANCE.toDto(input);
        if (st == null) {
            return null;
        }
        StNt stnt = new StNt();
        stnt.setValue(st.getValue());
        stnt.setNullFlavor(st.getNullFlavor());
        return stnt;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.StNt[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.StNt[size];
    }
}
