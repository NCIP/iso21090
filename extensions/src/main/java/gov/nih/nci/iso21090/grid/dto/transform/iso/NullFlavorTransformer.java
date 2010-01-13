package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;


/**
 * Transforms null flavors.
 */
public final class NullFlavorTransformer extends AbstractTransformer<org.iso._21090.NullFlavor, NullFlavor>
    implements Transformer<org.iso._21090.NullFlavor, NullFlavor> {

    /**
     * Public singleton.
     */
    public static final NullFlavorTransformer INSTANCE = new NullFlavorTransformer();

    private NullFlavorTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.NullFlavor toXml(NullFlavor input) {
        if (input == null) {
            return null;
        }
        return org.iso._21090.NullFlavor.valueOf(input.name());
    }

    /**
     * {@inheritDoc}
     */
    public NullFlavor toDto(org.iso._21090.NullFlavor input) {
        if (input == null) {
            return null;
        }
        return NullFlavor.valueOf(input.value());
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.NullFlavor[] createXmlArray(int size)
            throws DtoTransformException {
        return new org.iso._21090.NullFlavor[size];
    }
}
