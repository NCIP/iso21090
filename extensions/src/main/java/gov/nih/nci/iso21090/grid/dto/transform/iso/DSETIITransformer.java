package gov.nih.nci.iso21090.grid.dto.transform.iso;


import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iso._21090.DSETII;
import org.iso._21090.II;
import org.iso._21090.NullFlavor;

/**
 * Transforms sets of II.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETIITransformer extends AbstractTransformer<DSETII, DSet<Ii>>
    implements Transformer<DSETII, DSet<Ii>> {

    /**
     * Public transformer instance.
     */
    public static final DSETIITransformer INSTANCE = new DSETIITransformer();

    private DSETIITransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSETII toXml(DSet<Ii> input) {
        DSETII x = new DSETII();
        if (input != null && input.getItem() != null) {
            Set<Ii> sItem = input.getItem();
            List<II> tItem = x.getItem();
            for (Ii ad : sItem) {
                II cur = IITransformer.INSTANCE.toXml(ad);
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
    public DSet<Ii> toDto(DSETII input) {
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Ii> x = new DSet<Ii>();
        x.setItem(new HashSet<Ii>());
        List<II> sItem = input.getItem();
        Set<Ii> tItem = x.getItem();
        for (II ad : sItem) {
            tItem.add(IITransformer.INSTANCE.toDto(ad));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSETII[] createXmlArray(int size) throws DtoTransformException {
        return new DSETII[size];
    }

}
