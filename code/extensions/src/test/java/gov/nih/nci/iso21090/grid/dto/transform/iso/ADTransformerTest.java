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
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.PostalAddressUse;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iso._21090.IVLTS;


public class ADTransformerTest extends AbstractTransformerTestBase<ADTransformer, org.iso._21090.Ad, Ad> {

    public final String CODE = "code";
    public final String VAL = "val";
    public final Ivl<Ts> USEABLE_PERIOD_DTO = new IVLTSTransformerTest().makeDtoSimple();
    public final IVLTS USEABLE_PERIOD_XML = new IVLTSTransformerTest().makeXmlSimple();

    @Override
    public Ad makeDtoSimple() {
        Ad dto = new Ad();
        
        Set<PostalAddressUse> uses = new HashSet<PostalAddressUse>();
        uses.add(PostalAddressUse.H);
        uses.add(PostalAddressUse.HP);
        dto.setUses(uses);
        
        dto.setUseablePeriod(USEABLE_PERIOD_DTO);
        
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
    	
    	xml.getUses().add(org.iso._21090.PostalAddressUse.valueOf("H"));
    	xml.getUses().add(org.iso._21090.PostalAddressUse.valueOf("HP"));
    	
    	xml.setUseablePeriod(USEABLE_PERIOD_XML);
    	
        part1.setCode(CODE);
        part1.setValue(VAL);
        xml.getParts().add(part1);
        return xml;
    }

    @Override
    public void verifyDtoSimple(Ad x) {
        assertEquals(x.getPart().get(0).getCode(), CODE);
        assertEquals(x.getPart().get(0).getValue(), VAL);
        boolean containsUse = x.getUses().contains(PostalAddressUse.H);
        assertEquals( containsUse,true );
        containsUse = x.getUses().contains(PostalAddressUse.HP);
        assertEquals( containsUse,true );      
        
        boolean areEqual = (x.getUseablePeriod()).equals(USEABLE_PERIOD_DTO);
        assertEquals(areEqual,false);  //Lost IVL.any information during transformation because high and low were non-null
        
        ((Ivl<Ts>)(x.getUseablePeriod())).setAny(USEABLE_PERIOD_DTO.getAny());
        areEqual = (x.getUseablePeriod()).equals(USEABLE_PERIOD_DTO);
        assertEquals(areEqual,true);     
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.Ad x) {
        assertEquals(x.getParts().get(0).getCode(), CODE);
        assertEquals(x.getParts().get(0).getValue(), VAL);
        
        boolean containsUse = x.getUses().contains(org.iso._21090.PostalAddressUse.valueOf("H"));
        assertEquals( containsUse,true );
        containsUse = x.getUses().contains(org.iso._21090.PostalAddressUse.valueOf("HP"));
        assertEquals( containsUse,true );  
        
        IVLTS useablePeriod = (IVLTS)x.getUseablePeriod();
        assertEquals(useablePeriod.getHigh().getValue(),USEABLE_PERIOD_XML.getHigh().getValue());
        assertEquals(useablePeriod.isHighClosed(),USEABLE_PERIOD_XML.isHighClosed());
        assertEquals(useablePeriod.getLow().getValue(),USEABLE_PERIOD_XML.getLow().getValue());
        assertEquals(useablePeriod.isLowClosed(),USEABLE_PERIOD_XML.isLowClosed());

    }

    @Override
    public void verifyXmlNull(org.iso._21090.Ad x) {
        assertNull(x);
    }
}
