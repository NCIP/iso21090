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

import gov.nih.nci.iso21090.Ii;
//import gov.nih.nci.iso21090.extensions.Id;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

/**
 * Transforms II[] (XML type) and Ii[] (DTO type) instances.
 * @author smatyas
 */
public final class IdArrayTransformer extends AbstractTransformer<org.iso._21090.Ii[], Ii[]> implements Transformer<org.iso._21090.Ii[], Ii[]> {
    /**
     * Public singleton.
     */
    public static final IdArrayTransformer INSTANCE = new IdArrayTransformer();

    private IdArrayTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public Ii[] toDto(org.iso._21090.Ii[] input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ii[] results = new Ii[input.length];
        int i = 0;
        for (org.iso._21090.Ii id : input) {
            results[i] = IdTransformer.INSTANCE.toDto(id);
            i++;
        }
        return results;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ii[] toXml(Ii[] input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        org.iso._21090.Ii[] results = new org.iso._21090.Ii[input.length];
        int i = 0;
        for (Ii id : input) {
            results[i] = IdTransformer.INSTANCE.toXml(id);
            i++;
        }
        return results;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ii[][] createXmlArray(int arg0) throws DtoTransformException {
        return new org.iso._21090.Ii[arg0][];
    }

}
