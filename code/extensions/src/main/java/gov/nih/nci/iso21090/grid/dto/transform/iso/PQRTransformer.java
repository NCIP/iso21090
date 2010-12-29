package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Pqr;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.PQR;

/**
 * Transforms strings.
 */
public final class PQRTransformer extends AbstractTransformer<PQR, Pqr>
    implements Transformer<PQR, Pqr> {

    /**
     * Public singleton.
     */
    public static final PQRTransformer INSTANCE = new PQRTransformer();

    private PQRTransformer() {
    }

    /**
     * {@inheritDoc}
     * @throws DtoTransformException 
     */
    public PQR toXml(Pqr input) throws DtoTransformException {
    	 if (input == null) {
	            return null;
	        }
	        PQR res = new PQR();
	        
	        res.setCode(input.getCode());
	        res.setCodeSystem(input.getCodeSystem());
	        res.setCodeSystemName(input.getCodeSystemName());
	        res.setCodeSystemVersion(input.getCodeSystemVersion());
	        res.setDisplayName(STTransformer.INSTANCE.toXml(input.getDisplayName()));
	        
	        res.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
	        
	        return res;
    }

    /**
     * {@inheritDoc}
     * @throws DtoTransformException 
     */
    public Pqr toDto(PQR input) throws DtoTransformException {
    	if (input == null) {
            return null;
        }
        Pqr res = new Pqr();
        
        res.setCode(input.getCode());
        res.setCodeSystem(input.getCodeSystem());
        res.setCodeSystemName(input.getCodeSystemName());
        res.setCodeSystemVersion(input.getCodeSystemVersion());
        res.setDisplayName(STTransformer.INSTANCE.toDto(input.getDisplayName()));
        
        res.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public PQR[] createXmlArray(int size) throws DtoTransformException {
        return new PQR[size];
    }
}
