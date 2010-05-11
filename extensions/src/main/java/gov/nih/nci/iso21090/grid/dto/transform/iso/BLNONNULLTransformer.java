package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Bl;
import gov.nih.nci.iso21090.BlNonNull;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import gov.nih.nci.iso21090.BlNonNull;

/**
 * Transforms strings.
 */
public final class BLNONNULLTransformer extends AbstractTransformer<org.iso._21090.BlNonNull, BlNonNull> 
    implements Transformer<org.iso._21090.BlNonNull, BlNonNull> {

    /**
     * Public singleton.
     */
    public static final BLNONNULLTransformer INSTANCE = new BLNONNULLTransformer();
    
    private BLNONNULLTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.BlNonNull toXml(BlNonNull input) {
        org.iso._21090.BL bl = BLTransformer.INSTANCE.toXml(input);
        if (bl == null) {
            return null;
        }
        org.iso._21090.BlNonNull blnonnull = new org.iso._21090.BlNonNull();
        blnonnull.setValue(bl.isValue());
        return blnonnull;
    }

    /**
     * {@inheritDoc}
     */
    public BlNonNull toDto(org.iso._21090.BlNonNull input) {
        Bl bl = BLTransformer.INSTANCE.toDto(input);
        if (bl == null) {
            return null;
        }
        BlNonNull blnonnull = new BlNonNull();
        blnonnull.setValue(bl.getValue());
        return blnonnull;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.BlNonNull[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.BlNonNull[size];
    }
}
