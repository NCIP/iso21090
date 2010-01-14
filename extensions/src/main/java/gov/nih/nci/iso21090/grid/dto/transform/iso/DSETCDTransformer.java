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
import org.iso._21090.DSETCD;
import org.iso._21090.NullFlavor;

/**
 * Transforms sets of CD.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETCDTransformer extends AbstractTransformer<DSETCD, DSet<Cd>>
    implements Transformer<DSETCD, DSet<Cd>> {
    /**
     * Public transformer instance.
     */
    public static final DSETCDTransformer INSTANCE = new DSETCDTransformer();

    private DSETCDTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSETCD toXml(DSet<Cd> input) {
        DSETCD x = new DSETCD();
        if (input != null && input.getItem() != null) {
            Set<Cd> sItem = input.getItem();
            List<CD> tItem = x.getItem();
            for (Cd cd : sItem) {
                CD cur = CDTransformer.INSTANCE.toXml(cd);
                // XSD rule: all elements of set must be non-null
                if (!(cur == null || cur.getNullFlavor() != null)) {
                    tItem.add(cur);
                }
            }
        }
        if (x.getItem().isEmpty()) {
            x.setNullFlavor(NullFlavor.NI);
        }
        return x;

    }

    /**
     * {@inheritDoc}
     */
    public DSet<Cd> toDto(DSETCD input) {
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Cd> x = new DSet<Cd>();
        x.setItem(new HashSet<Cd>());
        List<CD> sItem = input.getItem();
        Set<Cd> tItem = x.getItem();
        for (CD cd : sItem) {
            tItem.add(CDTransformer.INSTANCE.toDto(cd));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSETCD[] createXmlArray(int size) throws DtoTransformException {
        return new DSETCD[size];
    }

}
