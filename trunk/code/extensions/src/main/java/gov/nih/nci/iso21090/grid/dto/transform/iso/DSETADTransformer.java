package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//import org.iso._21090.Ad;
import org.iso._21090.DSetAd;
import org.iso._21090.NullFlavor;

/**
 * Transforms sets of addresses.
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETADTransformer extends AbstractTransformer<DSetAd, DSet<gov.nih.nci.iso21090.Ad>>
    implements Transformer<DSetAd, DSet<gov.nih.nci.iso21090.Ad>> {

    /**
     * Public transformer instance.
     */
    public static final DSETADTransformer INSTANCE = new DSETADTransformer();

    private DSETADTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSetAd toXml(DSet<Ad> input) {
    	DSetAd x = new DSetAd();
        if (input != null && input.getItem() != null) {
            Set<Ad> sItem = input.getItem();
            List<org.iso._21090.Ad> tItem = x.getItems();
            for (Ad ad : sItem) {
            	org.iso._21090.Ad cur = ADTransformer.INSTANCE.toXml(ad);
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
    public DSet<Ad> toDto(DSetAd input) throws DtoTransformException{
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Ad> x = new DSet<Ad>();
        x.setItem(new HashSet<Ad>());
        List<org.iso._21090.Ad> sItem = input.getItems();
        Set<Ad> tItem = x.getItem();
        for (org.iso._21090.Ad ad : sItem) {
            tItem.add(ADTransformer.INSTANCE.toDto(ad));
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSetAd[] createXmlArray(int size) throws DtoTransformException {
        return new DSetAd[size];
    }

}
