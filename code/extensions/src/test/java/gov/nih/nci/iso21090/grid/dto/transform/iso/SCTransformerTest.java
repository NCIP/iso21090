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
import gov.nih.nci.iso21090.Sc;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.SC;
import org.iso._21090.ST;
import org.junit.Test;


/**
 *
 * @author mshestoalov
 */
public class SCTransformerTest  
    extends AbstractTransformerTestBase<SCTransformer, SC, Sc>{


    private STTransformerTest stTest = new STTransformerTest();
    
    @Override
    public SC makeXmlSimple() {
        ST st = stTest.makeXmlSimple();
        if (st != null) {
            SC returnVal = new SC();
            returnVal.setNullFlavor(st.getNullFlavor());
            returnVal.setValue(st.getValue());
            returnVal.setCode(new CDTransformerTest().makeXmlSimple());
            return returnVal;
        } else {
            return null;
        }
    }

    @Override
    public Sc makeDtoSimple() {
        St st = stTest.makeDtoSimple();
        if (st != null) {
            Sc returnVal = new Sc();
            returnVal.setNullFlavor(st.getNullFlavor());
            returnVal.setValue(st.getValue());
            returnVal.setCode(new CDTransformerTest().makeDtoSimple());
            return returnVal;
        } else {
            return null;
        }
        
    }

    @Override
    public void verifyXmlSimple(SC x) {
        assertEquals("v", x.getValue());
        new CDTransformerTest().verifyXmlSimple(x.getCode());
    }

    @Override
    public void verifyDtoSimple(Sc x) {
        assertEquals("v", x.getValue());
        new CDTransformerTest().verifyDtoSimple(x.getCode());
    }

    public SC makeXmlNullFlavored() {
        SC x = new SC();
        x.setNullFlavor(org.iso._21090.NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(Sc dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test(expected=IllegalArgumentException.class)
    public void testScNull() throws Exception {
        Sc sc = new Sc();
        sc.setCode(new CDTransformerTest().makeDtoSimple());
        SC result = SCTransformer.INSTANCE.toXml(sc);
        assertNotNull(result);
        assertNull(result.getValue());
    }
    
}