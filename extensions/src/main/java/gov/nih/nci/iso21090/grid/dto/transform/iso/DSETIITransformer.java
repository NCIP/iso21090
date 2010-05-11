package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iso._21090.DSetII;
import org.iso._21090.NullFlavor;

/**
 * Transforms sets of II.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETIITransformer extends AbstractTransformer<DSetII, DSet<gov.nih.nci.iso21090.Ii>>
    implements Transformer<DSetII, DSet<gov.nih.nci.iso21090.Ii>> {

    /**
     * Public transformer instance.
     */
    public static final DSETIITransformer INSTANCE = new DSETIITransformer();

    private DSETIITransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSetII toXml(DSet<Ii> input) {
    	DSetII x = new DSetII();
        if (input != null && input.getItem() != null) {
            Set<Ii> sItem = input.getItem();
            List<org.iso._21090.Ii> tItem = x.getItems();
            for (Ii ad : sItem) {
            	org.iso._21090.Ii cur = IITransformer.INSTANCE.toXml(ad);
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
    public DSet<Ii> toDto(DSetII input) {
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Ii> x = new DSet<Ii>();
        x.setItem(new HashSet<Ii>());
        List<org.iso._21090.Ii> sItem = input.getItems();
        Set<Ii> tItem = x.getItem();
        for (org.iso._21090.Ii ad : sItem) {
            tItem.add(IITransformer.INSTANCE.toDto(ad));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSetII[] createXmlArray(int size) throws DtoTransformException {
        return new DSetII[size];
    }

}
