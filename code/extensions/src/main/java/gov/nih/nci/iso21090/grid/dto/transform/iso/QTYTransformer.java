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

import gov.nih.nci.iso21090.Qty;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.QTY;

/**
 * Transforms base QTY fields.
 * @author mshestopalov
 *
 * @param <QTYX> extends QTY.
 * @param <Qtyx> extends Qtyx.
 */
@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.AbstractNaming" })
public abstract class QTYTransformer<QTYX extends QTY, Qtyx extends Qty> extends AbstractTransformer<QTYX, Qtyx>
    implements Transformer<QTYX, Qtyx> {

    /**
     * @return newly constructed xml object.
     */
    protected abstract QTYX newXml();

    /**
     * @return newly constructed dto object.
     */
    protected abstract Qtyx newDto();

    /**
     * Moves Qty fields into a QTY object.
     * @param input Qty
     * @return QTY
     * @throws DtoTransformException same as toXml
     */
    protected QTYX transformBaseXml(Qtyx input) throws DtoTransformException {
        if (input == null) {
            return null;
        }

        return newXml();
    }

    /**
     * Moves QTY fields into a Qty object.
     * @param input QTY
     * @return Qty
     * @throws DtoTransformException same as toDto
     */
    public Qtyx transformBaseDto(QTYX input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        return newDto();
    }
}
