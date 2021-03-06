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

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.apache.log4j.Logger;
import org.iso._21090.IVLPQ;
import org.iso._21090.PQ;

/**
 * Transforms physical quantity intervals.
 */
public final class IVLPQTransformer extends AbstractTransformer<IVLPQ, Ivl<Pq>> implements
        Transformer<IVLPQ, Ivl<Pq>> {

    /**
     * Public singleton.
     */
    public static final IVLPQTransformer INSTANCE = new IVLPQTransformer();
    private static final Logger LOG = Logger.getLogger(IVLPQTransformer.class);

    private IVLPQTransformer() {
        // intentionally blank
    }

    /**
     * {@inheritDoc}
     */
    public Ivl<Pq> toDto(IVLPQ input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ivl<Pq> result = new Ivl<Pq>();

        result.setHigh(PQTransformer.INSTANCE.toDto(input.getHigh()));
        result.setHighClosed(input.isHighClosed());
        result.setLow(PQTransformer.INSTANCE.toDto(input.getLow()));
        result.setLowClosed(input.isLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        // Cast from QTY -> PQ is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(PQTransformer.INSTANCE.toDto((PQ) input.getWidth()));

        // PO-1054 - any not in xsd, but is in our localization

        if (result.isLowMissing() || result.isHighEqualLow()) {
            result.setAny(result.getHigh());
        } else if (result.isHighMissing()) {
            result.setAny(result.getLow());
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public IVLPQ toXml(Ivl<Pq> input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        IVLPQ result = new IVLPQ();

        result.setHigh(PQTransformer.INSTANCE.toXml(input.getHigh()));
        result.setHighClosed(input.getHighClosed());
        result.setLow(PQTransformer.INSTANCE.toXml(input.getLow()));
        result.setLowClosed(input.getLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        // Cast from QTY -> PQ is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(PQTransformer.INSTANCE.toXml((Pq) input.getWidth()));

        // PO-1054 - any not in xsd, but is in our localization
        if (input.getAny() != null) {
            if (result.getHigh() == null && result.getLow() == null) {
                result.setHigh(PQTransformer.INSTANCE.toXml(input.getAny()));
                result.setLow(PQTransformer.INSTANCE.toXml(input.getAny()));
                result.setHighClosed(true);
                result.setLowClosed(true);
            } else {
                LOG.warn("Lost IVL.any information because high and low were non-null.");
            }
        }

        return result;
    }

    /**
     * {@inheritDoc}
     */
    public IVLPQ[] createXmlArray(int size) throws DtoTransformException {
        return new IVLPQ[size];
    }

}
