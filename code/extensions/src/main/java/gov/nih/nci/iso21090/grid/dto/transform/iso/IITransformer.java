package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;


/**
 * Transforms identifiers.
 */
public final class IITransformer extends AbstractTransformer<org.iso._21090.Ii, Ii>
    implements Transformer<org.iso._21090.Ii, Ii> {

    /**
     * Public singleton.
     */
    public static final IITransformer INSTANCE = new IITransformer();

    private IITransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ii toXml(Ii input) {
        if (input == null) {
            return null;
        }
        org.iso._21090.Ii d = new org.iso._21090.Ii();
        copyToXml(input, d);
        return d;
    }

    private static void copyToXml(Ii source, org.iso._21090.Ii target) {
        target.setDisplayable(source.getDisplayable());
        target.setExtension(source.getExtension());
        target.setIdentifierName(source.getIdentifierName());
        target.setRoot(source.getRoot());
        if (source.getReliability() != null) {
            String reliabilityName = source.getReliability().name();
            if (IdentifierReliability.UNV.name().equals(reliabilityName)) {
                reliabilityName = org.iso._21090.IdentifierReliability.USE.name();
            }
            target.setReliability(org.iso._21090.IdentifierReliability.valueOf(reliabilityName));
        }
        if (source.getScope() != null) {
            target.setScope(org.iso._21090.IdentifierScope.valueOf(source.getScope().name()));
        }
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(source.getNullFlavor()));
    }

    /**
     * {@inheritDoc}
     */
    public Ii toDto(org.iso._21090.Ii input) {
        if (input == null) {
            return null;
        }
        Ii d = new Ii();
        copyToDto(input, d);
        return d;
    }

    private static void copyToDto(org.iso._21090.Ii source, Ii target) {
        target.setDisplayable(source.isDisplayable());
        target.setExtension(source.getExtension());
        target.setIdentifierName(source.getIdentifierName());
        target.setRoot(source.getRoot());
        if (source.getReliability() != null) {
            String reliabilityName = source.getReliability().name();
            if (org.iso._21090.IdentifierReliability.USE.name().equals(reliabilityName)) {
                reliabilityName = IdentifierReliability.UNV.name();
            }
            target.setReliability(IdentifierReliability.valueOf(reliabilityName));
        }
        if (source.getScope() != null) {
            target.setScope(IdentifierScope.valueOf(source.getScope().name()));
        }
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(source.getNullFlavor()));
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.Ii[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.Ii[size];
    }
}
