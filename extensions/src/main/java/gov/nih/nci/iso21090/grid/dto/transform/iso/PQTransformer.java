package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.math.BigDecimal;

import org.iso._21090.PQ;

/**
 * Transforms physical quantities.
 * @author mshestopalov
 */
public final class PQTransformer extends QTYTransformer<PQ, Pq>
    implements Transformer<PQ, Pq> {

    /**
     * Public singleton.
     */
    public static final PQTransformer INSTANCE = new PQTransformer();

    private PQTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PQ newXml() {
        return new PQ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pq newDto() {
        return new Pq();
    }

    /**
     * {@inheritDoc}
     */
    public PQ toXml(Pq input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        PQ x = transformBaseXml(input);
        BigDecimal v = input.getValue();
        if (v != null) {
            x.setValue(v.doubleValue());
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        x.setPrecision(input.getPrecision());
        x.setUnit(input.getUnit());
        if (input.getValue() != null) {
            x.setValue(input.getValue().doubleValue());
        }

        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Pq toDto(PQ input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Pq d = transformBaseDto(input);
        Double v = input.getValue();
        if (v != null) {
            d.setValue(BigDecimal.valueOf(v));
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }
        d.setPrecision(input.getPrecision());
        d.setUnit(input.getUnit());
        if (input.getValue() != null) {
            BigDecimal bd = new BigDecimal(input.getValue());
            d.setValue(bd);
        }

        return d;
    }

    /**
     * {@inheritDoc}
     */
    public PQ[] createXmlArray(int size) throws DtoTransformException {
        return new PQ[size];
    }
}
