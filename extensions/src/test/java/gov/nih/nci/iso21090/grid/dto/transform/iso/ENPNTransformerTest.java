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

import org.iso._21090.ENPN;
import org.iso._21090.ENXP;
import org.junit.Test;

public class ENPNTransformerTest extends AbstractTransformerTestBase<ENPNTransformer, ENPN, EnPn> {

    @Override
    public EnPn makeDtoSimple() {
        EnPn dto = new EnPn();
        Enxp o = new Enxp(EntityNamePartType.FAM);
        dto.getPart().add(o);
        return dto;
    }

    @Override
    public ENPN makeXmlSimple() {
        ENPN xml = new ENPN();
        ENXP o = new ENXP();
        o.setCode("value");
        o.setType(org.iso._21090.EntityNamePartType.FAM);
        xml.getPart().add(o);
        return xml;
    }

    @Override
    public void verifyDtoSimple(EnPn x) {
        assertEquals(x.getPart().size(), 1);
        assertEquals(x.getPart().get(0).getType(), EntityNamePartType.FAM);

    }

    @Override
    public void verifyXmlSimple(ENPN x) {
        assertEquals(x.getPart().size(), 1);
        assertEquals(x.getPart().get(0).getType(), org.iso._21090.EntityNamePartType.FAM);
    }

    @Test
    public void testAllNullPartToDto() throws DtoTransformException {
        ENPN xml = new ENPN();
        ENXP o1 = new ENXP();
        o1.setCode("value1");
        xml.getPart().add(o1);
        ENXP o2 = new ENXP();
        o2.setCode("value2");
        xml.getPart().add(o2);
        ENXP o3 = new ENXP();
        o3.setCode("value3");
        xml.getPart().add(o3);
        ENXP o4 = new ENXP();
        o4.setCode("value4");
        xml.getPart().add(o4);
        ENXP o5 = new ENXP();
        o5.setCode("value5");
        xml.getPart().add(o5);
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
        ENPN xml = new ENPN();
        ENXP o1 = new ENXP();
        o1.setCode("value1");
        o1.setType(org.iso._21090.EntityNamePartType.PFX);
        xml.getPart().add(o1);
        ENXP o2 = new ENXP();
        o2.setCode("value2");
        o2.setType(org.iso._21090.EntityNamePartType.GIV);
        xml.getPart().add(o2);
        ENXP o3 = new ENXP();
        o3.setCode("value3");
        xml.getPart().add(o3);
        ENXP o4 = new ENXP();
        o4.setCode("value4");
        xml.getPart().add(o4);
        ENXP o5 = new ENXP();
        o5.setCode("value5");
        xml.getPart().add(o5);
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
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);

        Iterator<ENXP> it = result.getPart().iterator();
        while(it.hasNext()) {
            ENXP item = it.next();
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
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);

        assertEquals(org.iso._21090.EntityNamePartType.PFX, result.getPart().get(0).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(1).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(2).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(3).getType());
        assertEquals(org.iso._21090.EntityNamePartType.FAM, result.getPart().get(4).getType());
    }

    @Test
    public void testNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);
        assertTrue(result.getPart().isEmpty());
    }

    @Test
    public void testNullPartToDto() throws DtoTransformException {
        ENPN xml = new ENPN();
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);
        assertTrue(result.getPart().isEmpty());
    }

}
