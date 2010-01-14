package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.apache.log4j.Logger;
import org.iso._21090.INT;
import org.iso._21090.IVLINT;

/**
 * Transforms integer intervals.
 */
public final class IVLINTTransformer extends AbstractTransformer<IVLINT, Ivl<Int>> implements
        Transformer<IVLINT, Ivl<Int>> {

    /**
     * Public singleton.
     */
    public static final IVLINTTransformer INSTANCE = new IVLINTTransformer();
    private static final Logger LOG = Logger.getLogger(IVLINTTransformer.class);

    private IVLINTTransformer() {
        // intentionally blank
    }

    /**
     * {@inheritDoc}
     */
    public Ivl<Int> toDto(IVLINT input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ivl<Int> result = new Ivl<Int>();

        result.setHigh(INTTransformer.INSTANCE.toDto(input.getHigh()));
        result.setHighClosed(input.isHighClosed());
        result.setLow(INTTransformer.INSTANCE.toDto(input.getLow()));
        result.setLowClosed(input.isLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        result.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        // Cast from QTY -> INT is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(INTTransformer.INSTANCE.toDto((INT) input.getWidth()));

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
    public IVLINT toXml(Ivl<Int> input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        IVLINT result = new IVLINT();

        result.setHigh(INTTransformer.INSTANCE.toXml(input.getHigh()));
        result.setHighClosed(input.getHighClosed());
        result.setLow(INTTransformer.INSTANCE.toXml(input.getLow()));
        result.setLowClosed(input.getLowClosed());
        result.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        result.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));
        // Cast from QTY -> INT is ok by invariant in 21090 Sec. 7.11.8.3.5
        result.setWidth(INTTransformer.INSTANCE.toXml((Int) input.getWidth()));

        // PO-1054 - any not in xsd, but is in our localization
        if (input.getAny() != null) {
            if (result.getHigh() == null && result.getLow() == null) {
                result.setHigh(INTTransformer.INSTANCE.toXml(input.getAny()));
                result.setLow(INTTransformer.INSTANCE.toXml(input.getAny()));
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
    public IVLINT[] createXmlArray(int size) throws DtoTransformException {
        return new IVLINT[size];
    }

}
