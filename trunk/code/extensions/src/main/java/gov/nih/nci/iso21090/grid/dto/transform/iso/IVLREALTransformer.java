package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.apache.log4j.Logger;
import org.iso._21090.IVLREAL;
import org.iso._21090.TS;

/**
 * Transforms timestamp intervals.
 */
public final class IVLREALTransformer extends AbstractTransformer<IVLREAL, Ivl<Real>>
    implements Transformer<IVLREAL, Ivl<Real>> {

    /**
     * Public singleton.
     */
    public static final IVLREALTransformer INSTANCE = new IVLREALTransformer();
    private static final Logger LOG = Logger.getLogger(IVLREALTransformer.class);
    private IVLREALTransformer() {
        // intentionally blank
    }

    /**
     * {@inheritDoc}
     */
    public Ivl<Real> toDto(IVLREAL input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ivl<Real> result = new Ivl<Real>();

        result.setHigh(REALTransformer.INSTANCE.toDto(input.getHigh()));
        result.setHighClosed(input.isHighClosed());
        result.setLow(REALTransformer.INSTANCE.toDto(input.getLow()));
        result.setLowClosed(input.isLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        // Cast from QTY -> REAL is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(REALTransformer.INSTANCE.toDto((org.iso._21090.Real) input.getWidth()));

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
    public IVLREAL toXml(Ivl<Real> input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        IVLREAL result = new IVLREAL();

        result.setHigh(REALTransformer.INSTANCE.toXml(input.getHigh()));
        result.setHighClosed(input.getHighClosed());
        result.setLow(REALTransformer.INSTANCE.toXml(input.getLow()));
        result.setLowClosed(input.getLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        // Cast from QTY -> REAL is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(REALTransformer.INSTANCE.toXml((Real) input.getWidth()));

        // PO-1054 - any not in xsd, but is in our localization
        if (input.getAny() != null) {
            if (result.getHigh() == null && result.getLow() == null) {
                result.setHigh(REALTransformer.INSTANCE.toXml(input.getAny()));
                result.setLow(REALTransformer.INSTANCE.toXml(input.getAny()));
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
    public IVLREAL[] createXmlArray(int size) throws DtoTransformException {
        return new IVLREAL[size];
    }

}
