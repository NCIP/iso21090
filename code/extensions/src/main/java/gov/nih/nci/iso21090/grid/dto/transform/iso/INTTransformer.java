package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.Qty;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.INT;
import org.iso._21090.PQ;

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

        x.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));

        Int inputUncertainty = (Int)(input.getUncertainty());
        if (inputUncertainty != null) {
            INT xUncertainty = newXml();
            xUncertainty.setValue(inputUncertainty.getValue());            
            x.setUncertainty(xUncertainty);
            if (input.getUncertaintyType() != null){
                x.setUncertaintyType(org.iso._21090.UncertaintyType.valueOf(input.getUncertaintyType().name()));
            }          
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

        d.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));  

        INT inputUncertainty = (INT)(input.getUncertainty());
        if (inputUncertainty != null) {
            Int xUncertainty = newDto();
            xUncertainty.setValue(inputUncertainty.getValue());          
            d.setUncertainty(xUncertainty);
            if (input.getUncertaintyType() != null){
                d.setUncertaintyType(gov.nih.nci.iso21090.UncertaintyType.valueOf(input.getUncertaintyType().name()));            
            }            
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
