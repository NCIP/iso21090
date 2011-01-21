package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Sc;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.SC;
import org.iso._21090.ST;

/**
 * Transforms coded strings.
 */
public final class SCTransformer extends AbstractTransformer<SC, Sc> implements Transformer<SC, Sc> {

    /**
     * Public singleton.
     */
    public static final SCTransformer INSTANCE = new SCTransformer();
    
    private SCTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public SC toXml(Sc input) throws DtoTransformException{
        ST st = STTransformer.INSTANCE.toXml(input);
        if (st == null) {
            return null;
        }
        SC sc = new SC();
        sc.setValue(st.getValue());
        sc.setNullFlavor(st.getNullFlavor());
        sc.setCode(CDTransformer.INSTANCE.toXml(input.getCode()));
        if (sc.getNullFlavor() == null && sc.getValue() == null && sc.getCode() != null) {
            throw new IllegalArgumentException("Any non-null SC must have text if code is set.");
        }
        return sc;
    }

    /**
     * {@inheritDoc}
     */
    public Sc toDto(SC input) throws DtoTransformException{
        St st = STTransformer.INSTANCE.toDto(input);
        if (st == null) {
            return null;
        }
        Sc sc = new Sc();
        sc.setValue(st.getValue());
        sc.setNullFlavor(st.getNullFlavor());
        sc.setCode(CDTransformer.INSTANCE.toDto(input.getCode()));
        if (sc.getNullFlavor() == null && sc.getValue() == null && sc.getCode() != null) {
            throw new IllegalArgumentException("Any non-null SC must have text if code is set.");
        }
        return sc;
    }

    /**
     * {@inheritDoc}
     */
    public SC[] createXmlArray(int size) throws DtoTransformException {
        return new SC[size];
    }
}
