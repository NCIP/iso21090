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

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.INT;

/**
 * Transforms strings.
 * @author mshestopalov
 */
public final class INTTransformer extends QTYTransformer<INT, Int>
    implements Transformer<INT, Int> {

    /**
     * Public singleton.
     */
    public static final INTTransformer INSTANCE = new INTTransformer();

    private INTTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected INT newXml() {
        return new INT();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Int newDto() {
        return new Int();
    }

    /**
     * {@inheritDoc}
     */
    public INT toXml(Int input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        INT x = transformBaseXml(input);
        Integer v = input.getValue();
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
    public Int toDto(INT input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Int d = transformBaseDto(input);
        Integer v = input.getValue();
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
    public INT[] createXmlArray(int size) throws DtoTransformException {
        return new INT[size];
    }


}
