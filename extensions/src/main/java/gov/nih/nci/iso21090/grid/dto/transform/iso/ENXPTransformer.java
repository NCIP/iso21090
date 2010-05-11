package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;

/**
 * Transforms en parts.
 */
public final class ENXPTransformer extends AbstractTransformer<org.iso._21090.ENXP, Enxp> implements Transformer<org.iso._21090.ENXP, Enxp> {

    /**
     * Public singleton.
     */
    public static final ENXPTransformer INSTANCE = new ENXPTransformer();

    private ENXPTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.ENXP toXml(Enxp input) {
        if (input == null) {
            return null;
        }
        org.iso._21090.ENXP d = new org.iso._21090.ENXP();
        if (input.getType() != null) {
            d.setType(org.iso._21090.EntityNamePartType.valueOf(input.getType().name()));
        }
        d.setValue(input.getValue());
        d.setCode(input.getCode());
        d.setCodeSystem(input.getCodeSystem());
        d.setCodeSystemVersion(input.getCodeSystemVersion());
        if (input.getQualifier() != null) {
            for (EntityNamePartQualifier qual : input.getQualifier()) {
                d.getQualifiers().add(org.iso._21090.EntityNamePartQualifier.valueOf(qual.name()));
            }
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public Enxp toDto(org.iso._21090.ENXP input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Enxp d = new Enxp();
        if (input.getType() != null) {
            d.setType(EntityNamePartType.valueOf(input.getType().name()));
        }

        d.setValue(input.getValue());
        d.setCode(input.getCode());
        d.setCodeSystem(input.getCodeSystem());
        d.setCodeSystemVersion(input.getCodeSystemVersion());
        if(input.getQualifiers() != null && input.getQualifiers().size() > 0)
        {
        	HashSet<EntityNamePartQualifier> qualifiers = new HashSet<EntityNamePartQualifier>();
	        for (org.iso._21090.EntityNamePartQualifier qual : input.getQualifiers()) {
	            qualifiers.add(EntityNamePartQualifier.valueOf(qual.name()));
	        }
	        d.setQualifier(qualifiers);
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.ENXP[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.ENXP[size];
    }
}
