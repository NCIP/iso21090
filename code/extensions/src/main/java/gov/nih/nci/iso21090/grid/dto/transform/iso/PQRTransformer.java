//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

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
        
        return res;
    }

    /**
     * {@inheritDoc}
     */
    public PQR[] createXmlArray(int size) throws DtoTransformException {
        return new PQR[size];
    }
}
