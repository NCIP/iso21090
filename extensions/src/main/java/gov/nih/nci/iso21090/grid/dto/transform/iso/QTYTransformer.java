package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.Pqv;
import gov.nih.nci.iso21090.Qty;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.UncertaintyType;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.INT;
import org.iso._21090.PQ;
import org.iso._21090.QTY;
import org.iso._21090.TS;

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

        QTYX x = newXml();


        if (input.getOriginalText() != null) {
            x.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));
        }

        if (input.getUncertainty() != null) {
            if (input.getUncertainty() instanceof Int) {
                x.setUncertainty(INTTransformer.INSTANCE.toXml((Int) input.getUncertainty()));
            } else if (input.getUncertainty() instanceof Pqv) {
                x.setUncertainty(PQTransformer.INSTANCE.toXml((Pq) input.getUncertainty()));
            } else if (input.getUncertainty() instanceof Ts) {
                x.setUncertainty(TSTransformer.INSTANCE.toXml((Ts) input.getUncertainty()));
            } else {
                throw new DtoTransformException("Input is not a child of QTY");
            }
        }

        if (input.getUncertaintyType() != null) {
            x.setUncertaintyType(org.iso._21090
                    .UncertaintyType.valueOf(input.getUncertaintyType().name()));
        }

        return x;
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
        Qtyx x = newDto();

        //QTY fields.
        if (input.getOriginalText() != null) {
            x.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        }

        if (input.getUncertainty() != null) {
            if (input.getUncertainty() instanceof INT) {
                x.setUncertainty(INTTransformer.INSTANCE.toDto((INT) input.getUncertainty()));
            } else if (input.getUncertainty() instanceof PQ) {
                x.setUncertainty(PQTransformer.INSTANCE.toDto((PQ) input.getUncertainty()));
            } else if (input.getUncertainty() instanceof TS) {
                x.setUncertainty(TSTransformer.INSTANCE.toDto((TS) input.getUncertainty()));
            } else {
                throw new DtoTransformException("Input is not a child of QTY");
            }
        }

        if (input.getUncertaintyType() != null) {
            x.setUncertaintyType(UncertaintyType.valueOf(input.getUncertaintyType().name()));
        }

        return x;
    }
}
