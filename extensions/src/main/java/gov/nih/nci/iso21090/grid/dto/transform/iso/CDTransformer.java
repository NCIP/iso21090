package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.CD;

/**
 * Transforms coded data elements.
 */
public final class CDTransformer extends AbstractTransformer<CD, Cd>
    implements Transformer<CD, Cd> {

    /**
     * Singleton for client consumption.
     */
    public static final CDTransformer INSTANCE = new CDTransformer();

    private CDTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public Cd toDto(CD input) {
        if (input == null) {
            return null;
        }
        Cd res = new Cd();
        String v = input.getCode();
        if (v != null) {
            res.setCode(v);
        } else {
            res.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public CD toXml(Cd input) {
        if (input == null) {
            return null;
        }
        CD res = new CD();
        String v = input.getCode();
        if (v != null) {
            res.setCode(v);
        } else {
            res.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public CD[] createXmlArray(int size) throws DtoTransformException {
        return new CD[size];
    }
}
