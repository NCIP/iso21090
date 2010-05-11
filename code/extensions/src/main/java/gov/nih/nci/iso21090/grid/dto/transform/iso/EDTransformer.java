package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Compression;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.ED;

/**
 * Transforms strings.
 * @author mshestopalov
 */
public final class EDTransformer extends AbstractTransformer<ED, Ed>
    implements Transformer<ED, Ed> {

    /**
     * Public singleton.
     */
    public static final EDTransformer INSTANCE = new EDTransformer();

    private EDTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public ED toXml(Ed input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        ED x = new ED();
        String v = input.getValue();
        if (v != null) {
            x.setValue(v);
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        if (input.getCompression() != null) {
            x.setCompression(org.iso._21090.Compression.valueOf(input.getCompression().name()));
        }
        x.setData(input.getData());
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Ed toDto(ED input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ed d = new Ed();
        String v = input.getValue();
        if (v != null) {
            d.setValue(v);
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }

        if (input.getCompression() != null) {
            d.setCompression(Compression.valueOf(input.getCompression().name()));
        }
        d.setData(input.getData());

        return d;
    }

    /**
     * {@inheritDoc}
     */
    public ED[] createXmlArray(int size) throws DtoTransformException {
        return new ED[size];
    }
}
