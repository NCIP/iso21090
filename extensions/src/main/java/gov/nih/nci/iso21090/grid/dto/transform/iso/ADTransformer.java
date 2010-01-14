package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.ArrayList;
import java.util.List;
import org.iso._21090.AD;
import org.iso._21090.ADXP;

/**
 * Transforms Addresses.
 */
public final class ADTransformer extends AbstractTransformer<AD, Ad> implements Transformer<AD, Ad> {

    /**
     * Public singleton.
     */
    public static final ADTransformer INSTANCE = new ADTransformer();

    private ADTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public AD toXml(Ad input) {
        if (input == null) {
            return null;
        }
        AD x = new AD();
        copyToXml(input, x);
        return x;
    }

    private static void copyToXml(Ad source, AD target) {
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(source.getNullFlavor()));
        List<Adxp> sourcePart = source.getPart();
        if (sourcePart != null) {
            List<ADXP> targetPart = target.getPart(); // never return null
            for (Adxp p : sourcePart) {
                if (p != null) {
                    targetPart.add(ADXPTransformer.INSTANCE.toXml(p));
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public Ad toDto(AD input) {
        if (input == null) {
            return null;
        }
        Ad d = new Ad();
        copyToDto(input, d);
        return d;
    }

    private static void copyToDto(AD source, Ad target) {
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(source.getNullFlavor()));
        List<ADXP> sourcePart = source.getPart(); // never returns null
        List<Adxp> targetPart = new ArrayList<Adxp>(sourcePart.size());
        target.setPart(targetPart);
        for (ADXP p : sourcePart) {
            if (p != null) {
                targetPart.add(ADXPTransformer.INSTANCE.toDto(p));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public AD[] createXmlArray(int size) throws DtoTransformException {
        return new AD[size];
    }
}
