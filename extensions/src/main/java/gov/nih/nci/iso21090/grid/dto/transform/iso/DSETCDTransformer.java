package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iso._21090.CD;
import org.iso._21090.DSetCd;
import org.iso._21090.NullFlavor;

/**
 * Transforms sets of CD.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETCDTransformer extends AbstractTransformer<DSetCd, DSet<gov.nih.nci.iso21090.Cd>>
    implements Transformer<DSetCd, DSet<gov.nih.nci.iso21090.Cd>> {
    /**
     * Public transformer instance.
     */
    public static final DSETCDTransformer INSTANCE = new DSETCDTransformer();

    private DSETCDTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSetCd toXml(DSet<Cd> input) throws DtoTransformException{
    	DSetCd x = new DSetCd();
        if (input != null && input.getItem() != null) {
            Set<Cd> sItem = input.getItem();
            List<org.iso._21090.CD> tItem = x.getItems();
            for (Cd cd : sItem) {
            	org.iso._21090.CD cur = CDTransformer.INSTANCE.toXml(cd);
                // XSD rule: all elements of set must be non-null
                if (!(cur == null || cur.getNullFlavor() != null)) {
                    tItem.add(cur);
                }
            }
        }
        if (x.getItems().isEmpty()) {
            x.setNullFlavor(NullFlavor.NI);
        }
        return x;

    }

    /**
     * {@inheritDoc}
     */
    public DSet<Cd> toDto(DSetCd input) throws DtoTransformException{
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Cd> x = new DSet<Cd>();
        x.setItem(new HashSet<Cd>());
        List<org.iso._21090.CD> sItem = input.getItems();
        Set<Cd> tItem = x.getItem();
        for (org.iso._21090.CD cd : sItem) {
            tItem.add(CDTransformer.INSTANCE.toDto(cd));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSetCd[] createXmlArray(int size) throws DtoTransformException {
        return new DSetCd[size];
    }

}
