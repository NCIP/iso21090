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

import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.ED;

/**
 * Transforms strings.
 * @author mshestopalov
 */
public final class EDTextTransformer extends AbstractTransformer<org.iso._21090.EdText, EdText>
    implements Transformer<org.iso._21090.EdText, EdText> {

    /**
     * Public singleton.
     */
    public static final EDTextTransformer INSTANCE = new EDTextTransformer();

    private EDTextTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.EdText toXml(EdText input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        org.iso._21090.EdText x = new org.iso._21090.EdText();
        ED ed = EDTransformer.INSTANCE.toXml(input);
        x.setCharset(ed.getCharset());
        x.setData(ed.getData());
        x.setMediaType(ed.getMediaType());
        x.setReference(ed.getReference());
        x.setXml(ed.getXml());
        x.setValue(ed.getValue());
        x.setNullFlavor(ed.getNullFlavor());
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public EdText toDto(org.iso._21090.EdText input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        EdText x = new EdText();
        Ed ed = EDTransformer.INSTANCE.toDto(input);
        x.setData(ed.getData());
        x.setValue(ed.getValue());
        x.setNullFlavor(ed.getNullFlavor());
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.EdText[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.EdText[size];
    }
}
