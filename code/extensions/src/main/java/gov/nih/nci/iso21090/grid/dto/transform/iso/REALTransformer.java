package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

/**
 * Transforms real numbers.
 * @author mshestopalov, Dan Dumitru
 */
public final class REALTransformer extends QTYTransformer<org.iso._21090.Real, Real>
    implements Transformer<org.iso._21090.Real, Real> {

    /**
     * Public singleton.
     */
    public static final REALTransformer INSTANCE = new REALTransformer();

    private REALTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected org.iso._21090.Real newXml() {
        return new org.iso._21090.Real();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Real newDto() {
        return new Real();
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Real toXml(Real input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        org.iso._21090.Real x = transformBaseXml(input);
        Double v = input.getValue();
        if (v != null) {
            x.setValue(v.doubleValue());
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        x.setPrecision(input.getPrecision());
        
        x.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));

        Real inputUncertainty = (Real)(input.getUncertainty());
        if (inputUncertainty != null) {
        	org.iso._21090.Real xUncertainty = newXml();
            xUncertainty.setValue(inputUncertainty.getValue());
            xUncertainty.setPrecision(inputUncertainty.getPrecision());             
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
    public Real toDto(org.iso._21090.Real input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Real d = transformBaseDto(input);
        Double v = input.getValue();
        if (v != null) {
            d.setValue(v);
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }
        d.setPrecision(input.getPrecision());        

        d.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        
        org.iso._21090.Real inputUncertainty = (org.iso._21090.Real)(input.getUncertainty());
        if (inputUncertainty != null) {
            Real xUncertainty = newDto();
            xUncertainty.setValue(inputUncertainty.getValue());
            xUncertainty.setPrecision(inputUncertainty.getPrecision());             
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
    public org.iso._21090.Real[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.Real[size];
    }


}
