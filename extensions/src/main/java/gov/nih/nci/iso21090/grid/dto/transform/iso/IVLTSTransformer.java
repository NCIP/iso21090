package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.apache.log4j.Logger;
import org.iso._21090.IVLTS;
import org.iso._21090.TS;

/**
 * Transforms timestamp intervals.
 */
public final class IVLTSTransformer extends AbstractTransformer<IVLTS, Ivl<Ts>>
    implements Transformer<IVLTS, Ivl<Ts>> {

    /**
     * Public singleton.
     */
    public static final IVLTSTransformer INSTANCE = new IVLTSTransformer();
    private static final Logger LOG = Logger.getLogger(IVLTSTransformer.class);
    private IVLTSTransformer() {
        // intentionally blank
    }

    /**
     * {@inheritDoc}
     */
    public Ivl<Ts> toDto(IVLTS input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ivl<Ts> result = new Ivl<Ts>();

        result.setHigh(TSTransformer.INSTANCE.toDto(input.getHigh()));
        result.setHighClosed(input.isHighClosed());
        result.setLow(TSTransformer.INSTANCE.toDto(input.getLow()));
        result.setLowClosed(input.isLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        result.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        // Cast from QTY -> TS is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(TSTransformer.INSTANCE.toDto((TS) input.getWidth()));

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
    public IVLTS toXml(Ivl<Ts> input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        IVLTS result = new IVLTS();

        result.setHigh(TSTransformer.INSTANCE.toXml(input.getHigh()));
        result.setHighClosed(input.getHighClosed());
        result.setLow(TSTransformer.INSTANCE.toXml(input.getLow()));
        result.setLowClosed(input.getLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        result.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));
        // Cast from QTY -> TS is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(TSTransformer.INSTANCE.toXml((Ts) input.getWidth()));

        // PO-1054 - any not in xsd, but is in our localization
        if (input.getAny() != null) {
            if (result.getHigh() == null && result.getLow() == null) {
                result.setHigh(TSTransformer.INSTANCE.toXml(input.getAny()));
                result.setLow(TSTransformer.INSTANCE.toXml(input.getAny()));
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
    public IVLTS[] createXmlArray(int size) throws DtoTransformException {
        return new IVLTS[size];
    }

}
