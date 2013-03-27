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

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.ArrayList;
import java.util.List;


public class ADTransformerTest extends AbstractTransformerTestBase<ADTransformer, org.iso._21090.Ad, Ad> {
    public final String CODE = "code";
    public final String VAL = "val";

    @Override
    public Ad makeDtoSimple() {
        Ad dto = new Ad();
        Adxp adxp1 = new Adxp();
        adxp1.setCode(CODE);
        adxp1.setValue(VAL);
        List<Adxp> li1 = new ArrayList<Adxp>();
        li1.add(adxp1);
        dto.setPart(li1);
        
        return dto;
    }

    @Override
    public org.iso._21090.Ad makeXmlSimple() {
    	org.iso._21090.Ad xml = new org.iso._21090.Ad();
    	org.iso._21090.ADXP part1 = new org.iso._21090.ADXP();
        part1.setCode(CODE);
        
        part1.setValue(VAL);
        xml.getParts().add(part1);
        return xml;
    }

    @Override
    public void verifyDtoSimple(Ad x) {
        assertEquals(x.getPart().get(0).getCode(), CODE);
        assertEquals(x.getPart().get(0).getValue(), VAL);
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.Ad x) {
        assertEquals(x.getParts().get(0).getCode(), CODE);
        assertEquals(x.getParts().get(0).getValue(), VAL);
    }

    @Override
    public void verifyXmlNull(org.iso._21090.Ad x) {
        assertNull(x);
    }
}
