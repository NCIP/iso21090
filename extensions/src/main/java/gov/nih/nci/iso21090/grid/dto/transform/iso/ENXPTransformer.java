package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;

import org.iso._21090.ENXP;

/**
 * Transforms en parts.
 */
public final class ENXPTransformer extends AbstractTransformer<ENXP, Enxp> implements Transformer<ENXP, Enxp> {

    /**
     * Public singleton.
     */
    public static final ENXPTransformer INSTANCE = new ENXPTransformer();

    private ENXPTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public ENXP toXml(Enxp input) {
        if (input == null) {
            return null;
        }
        ENXP d = new ENXP();
        if (input.getType() != null) {
            d.setType(org.iso._21090.EntityNamePartType.valueOf(input.getType().name()));
        }
        d.setValue(input.getValue());

        // all of these are currently ignored by PO.
        d.setCode(input.getCode());
        d.setCodeSystem(input.getCodeSystem());
        d.setCodeSystemVersion(input.getCodeSystemVersion());
        if (input.getQualifier() != null) {
            for (EntityNamePartQualifier qual : input.getQualifier()) {
                d.getQualifier().add(org.iso._21090.EntityNamePartQualifier.valueOf(qual.name()));
            }
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public Enxp toDto(ENXP input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Enxp d = new Enxp();
        if (input.getType() != null) {
            d.setType(EntityNamePartType.valueOf(input.getType().name()));
        }

        d.setValue(input.getValue());

        // all of these are currently ignored by PO.
        d.setCode(input.getCode());
        d.setCodeSystem(input.getCodeSystem());
        d.setCodeSystemVersion(input.getCodeSystemVersion());
        d.setQualifier(new HashSet<EntityNamePartQualifier>());
        for (org.iso._21090.EntityNamePartQualifier qual : input.getQualifier()) {
            d.getQualifier().add(EntityNamePartQualifier.valueOf(qual.name()));
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public ENXP[] createXmlArray(int size) throws DtoTransformException {
        return new ENXP[size];
    }
}
