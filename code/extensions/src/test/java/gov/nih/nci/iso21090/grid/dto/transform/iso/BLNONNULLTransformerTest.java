//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.BlNonNull;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import org.iso._21090.BL;
import org.junit.Test;


/**
 *
 * @author mshestoalov
 */
public class BLNONNULLTransformerTest  
    extends AbstractTransformerTestBase<BLNONNULLTransformer, org.iso._21090.BlNonNull, BlNonNull>{


    private BLTransformerTest blTest = new BLTransformerTest();
    
    @Override
    public org.iso._21090.BlNonNull makeXmlSimple() {
        BL bl = blTest.makeXmlSimple();
        if (bl != null) {
        	org.iso._21090.BlNonNull returnVal = new org.iso._21090.BlNonNull();
            returnVal.setValue(bl.isValue());
            return returnVal;
        } else {
            return null;
        }
    }

    @Override
    public BlNonNull makeDtoSimple() {
        gov.nih.nci.iso21090.Bl bl = blTest.makeDtoSimple();
        if (bl != null) {
            BlNonNull returnVal = new BlNonNull();        
            returnVal.setValue(bl.getValue());
            return returnVal;
        } else {
            return null;
        }
        
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.BlNonNull x) {
        assertEquals(true, x.isValue());
    }

    @Override
    public void verifyDtoSimple(BlNonNull x) {
        assertEquals(true, x.getValue());
    }
    
    public org.iso._21090.BlNonNull makeXmlNullFlavored() throws DtoTransformException {
    	org.iso._21090.BlNonNull x = new org.iso._21090.BlNonNull();
        x.setNullFlavor(org.iso._21090.NullFlavor.NI);
        return x;
    }
    
    public void verifyDtoNullFlavored(BlNonNull dto) {
        assertNull(dto.getValue());
        assertNull(dto.getNullFlavor());
    }    
    
    @Test(expected=IllegalArgumentException.class)
    public void testSettingNullFlavorOnDto() {
        BlNonNull blNonNull = new BlNonNull();
        blNonNull.setNullFlavor(NullFlavor.ASKU);
    }    
}