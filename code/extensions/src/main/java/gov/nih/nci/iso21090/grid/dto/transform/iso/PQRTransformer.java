package gov.nih.nci.iso21090.grid.dto.transform.iso;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.Pqr;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.CDTransformer;

import org.iso._21090.CD;
import org.iso._21090.PQR;
import org.iso._21090.XReference;

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
//        res.setValueSet(input.getValueSet());
//        res.setValueSetVersion(input.getValueSetVersion());
        
//        List<org.iso._21090.CodingRationale> inputCodingRationales = input.getCodingRationales();
//        Set<gov.nih.nci.iso21090.CodingRationale> targetCodingRationales = new HashSet<gov.nih.nci.iso21090.CodingRationale>(inputCodingRationales.size());
//        for (org.iso._21090.CodingRationale codingRationale : inputCodingRationales) {
//        	res.getCodingRationale().add(gov.nih.nci.iso21090.CodingRationale.valueOf(codingRationale.name()));
//        }
       //TODO verify correctness of CDTransformer/XRef of CD
//        if(((CD)input).getSource()!=null){
//       	 res.setSource(CDTransformer.INSTANCE.toDto(   (CD)(     ((CD)input).getSource().getXref()) ));
//        }
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public PQR[] createXmlArray(int size) throws DtoTransformException {
        return new PQR[size];
    }
}
