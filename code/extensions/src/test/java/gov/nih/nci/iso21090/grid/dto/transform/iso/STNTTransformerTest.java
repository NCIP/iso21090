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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.StNt;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.ST;
import org.junit.Test;


/**
 *
 * @author mshestoalov
 */
public class STNTTransformerTest  
    extends AbstractTransformerTestBase<STNTTransformer, org.iso._21090.StNt, StNt>{


    private STTransformerTest stTest = new STTransformerTest();
    
    @Override
    public org.iso._21090.StNt makeXmlSimple() {
        ST st = stTest.makeXmlSimple();
        if (st != null) {
        	org.iso._21090.StNt returnVal = new org.iso._21090.StNt();
            returnVal.setNullFlavor(st.getNullFlavor());
            returnVal.setValue(st.getValue());
            return returnVal;
        } else {
            return null;
        }
    }

    @Override
    public StNt makeDtoSimple() {
        St st = stTest.makeDtoSimple();
        if (st != null) {
            StNt returnVal = new StNt();
            returnVal.setNullFlavor(st.getNullFlavor());
            returnVal.setValue(st.getValue());
            return returnVal;
        } else {
            return null;
        }
        
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.StNt x) {
        assertEquals("v", x.getValue());
    }

    @Override
    public void verifyDtoSimple(StNt x) {
        assertEquals("v", x.getValue());
    }

    public org.iso._21090.StNt makeXmlNullFlavored() {
    	org.iso._21090.StNt x = new org.iso._21090.StNt();
        x.setNullFlavor(org.iso._21090.NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(StNt dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testStNtNull() throws Exception {
        StNt stNt = new StNt();
        stNt.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        org.iso._21090.StNt result = STNTTransformer.INSTANCE.toXml(stNt);
        assertNotNull(result);
        assertEquals(org.iso._21090.NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.getValue());
    }
    
}