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
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENPNTransformer;

import java.util.Iterator;

import org.junit.Test;

public class ENPNTransformerTest extends AbstractTransformerTestBase<ENPNTransformer, org.iso._21090.EnPn, EnPn> {

    @Override
    public EnPn makeDtoSimple() {
        EnPn dto = new EnPn();
        Enxp o = new Enxp(EntityNamePartType.FAM);
        dto.getPart().add(o);
        return dto;
    }

    @Override
    public org.iso._21090.EnPn makeXmlSimple() {
    	org.iso._21090.EnPn xml = new org.iso._21090.EnPn();
    	org.iso._21090.ENXP o = new org.iso._21090.ENXP();
        o.setCode("value");
        o.setType(org.iso._21090.EntityNamePartType.FAM);
        xml.getParts().add(o);
        return xml;
    }

    @Override
    public void verifyDtoSimple(EnPn x) {
        assertEquals(x.getPart().size(), 1);
        assertEquals(x.getPart().get(0).getType(), EntityNamePartType.FAM);

    }

    @Override
    public void verifyXmlSimple(org.iso._21090.EnPn x) {
        assertEquals(x.getParts().size(), 1);
        assertEquals(x.getParts().get(0).getType(), org.iso._21090.EntityNamePartType.FAM);
    }

    @Test
    public void testAllNullPartToDto() throws DtoTransformException {
    	org.iso._21090.EnPn xml = new org.iso._21090.EnPn();
    	org.iso._21090.ENXP o1 = new org.iso._21090.ENXP();
        o1.setCode("value1");
        xml.getParts().add(o1);
        org.iso._21090.ENXP o2 = new org.iso._21090.ENXP();
        o2.setCode("value2");
        xml.getParts().add(o2);
        org.iso._21090.ENXP o3 = new org.iso._21090.ENXP();
        o3.setCode("value3");
        xml.getParts().add(o3);
        org.iso._21090.ENXP o4 = new org.iso._21090.ENXP();
        o4.setCode("value4");
        xml.getParts().add(o4);
        org.iso._21090.ENXP o5 = new org.iso._21090.ENXP();
        o5.setCode("value5");
        xml.getParts().add(o5);
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);

        Iterator<Enxp> it = result.getPart().iterator();
        while(it.hasNext()) {
            Enxp item = it.next();
            if (it.hasNext()) {
                assertEquals(EntityNamePartType.GIV, item.getType());
            } else {
                assertEquals(EntityNamePartType.FAM, item.getType());
            }
        }
    }

    @Test
    public void testSomeNullPartToDto() throws DtoTransformException {
    	org.iso._21090.EnPn xml = new org.iso._21090.EnPn();
    	org.iso._21090.ENXP o1 = new org.iso._21090.ENXP();
        o1.setCode("value1");
        o1.setType(org.iso._21090.EntityNamePartType.PFX);
        xml.getParts().add(o1);
        org.iso._21090.ENXP o2 = new org.iso._21090.ENXP();
        o2.setCode("value2");
        o2.setType(org.iso._21090.EntityNamePartType.GIV);
        xml.getParts().add(o2);
        org.iso._21090.ENXP o3 = new org.iso._21090.ENXP();
        o3.setCode("value3");
        xml.getParts().add(o3);
        org.iso._21090.ENXP o4 = new org.iso._21090.ENXP();
        o4.setCode("value4");
        xml.getParts().add(o4);
        org.iso._21090.ENXP o5 = new org.iso._21090.ENXP();
        o5.setCode("value5");
        xml.getParts().add(o5);
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);

        assertEquals(EntityNamePartType.PFX, result.getPart().get(0).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(1).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(2).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(3).getType());
        assertEquals(EntityNamePartType.FAM, result.getPart().get(4).getType());
    }

    @Test
    public void testAllNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        Enxp o1 = new Enxp();
        o1.setCode("value1");
        dto.getPart().add(o1);
        Enxp o2 = new Enxp();
        o2.setCode("value2");
        dto.getPart().add(o2);
        Enxp o3 = new Enxp();
        o3.setCode("value3");
        dto.getPart().add(o3);
        Enxp o4 = new Enxp();
        o4.setCode("value4");
        dto.getPart().add(o4);
        Enxp o5 = new Enxp();
        o5.setCode("value5");
        dto.getPart().add(o5);
        org.iso._21090.EnPn result = ENPNTransformer.INSTANCE.toXml(dto);

        Iterator<org.iso._21090.ENXP> it = result.getParts().iterator();
        while(it.hasNext()) {
        	org.iso._21090.ENXP item = it.next();
            if (it.hasNext()) {
                assertEquals(org.iso._21090.EntityNamePartType.GIV, item.getType());
            } else {
                assertEquals(org.iso._21090.EntityNamePartType.FAM, item.getType());
            }
        }
    }

    @Test
    public void testSomeNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        Enxp o1 = new Enxp();
        o1.setCode("value1");
        o1.setType(EntityNamePartType.PFX);
        dto.getPart().add(o1);
        Enxp o2 = new Enxp();
        o2.setCode("value2");
        o2.setType(EntityNamePartType.GIV);
        dto.getPart().add(o2);
        Enxp o3 = new Enxp();
        o3.setCode("value3");
        dto.getPart().add(o3);
        Enxp o4 = new Enxp();
        o4.setCode("value4");
        dto.getPart().add(o4);
        Enxp o5 = new Enxp();
        o5.setCode("value5");
        dto.getPart().add(o5);
        org.iso._21090.EnPn result = ENPNTransformer.INSTANCE.toXml(dto);

        assertEquals(org.iso._21090.EntityNamePartType.PFX, result.getParts().get(0).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getParts().get(1).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getParts().get(2).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getParts().get(3).getType());
        assertEquals(org.iso._21090.EntityNamePartType.FAM, result.getParts().get(4).getType());
    }

    @Test
    public void testNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        org.iso._21090.EnPn result = ENPNTransformer.INSTANCE.toXml(dto);
        assertTrue(result.getParts().isEmpty());
    }

    @Test
    public void testNullPartToDto() throws DtoTransformException {
    	org.iso._21090.EnPn xml = new org.iso._21090.EnPn();
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);
        assertTrue(result.getPart().isEmpty());
    }

}
