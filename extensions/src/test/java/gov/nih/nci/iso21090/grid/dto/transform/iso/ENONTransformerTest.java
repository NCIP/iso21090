package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENONTransformer;

import org.junit.Test;

public class ENONTransformerTest extends AbstractTransformerTestBase<ENONTransformer, org.iso._21090.EnOn, EnOn>{

    @Override
    public EnOn makeDtoSimple() {
        EnOn dto = new EnOn();
        Enxp o = new Enxp(EntityNamePartType.DEL);
        dto.getPart().add(o);
        return dto;
    }

    @Override
    public org.iso._21090.EnOn makeXmlSimple() {
    	org.iso._21090.EnOn xml = new org.iso._21090.EnOn();
    	org.iso._21090.ENXP o = new org.iso._21090.ENXP();
        o.setType(org.iso._21090.EntityNamePartType.DEL);
        xml.getParts().add(o);
        return xml;
    }

    @Override
    public void verifyDtoSimple(EnOn x) {
        assertEquals(x.getPart().size(),1);
        assertEquals(x.getPart().get(0).getType(),EntityNamePartType.DEL);
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.EnOn x) {
        assertEquals(x.getParts().size(),1);
        assertEquals(x.getParts().get(0).getType(),org.iso._21090.EntityNamePartType.DEL);

    }

    @Test
    public void testNullPartToXml() throws DtoTransformException {
        EnOn dto = new EnOn();
        org.iso._21090.EnOn result = ENONTransformer.INSTANCE.toXml(dto);
        assertTrue(result.getParts().isEmpty());
    }

    @Test
    public void testNullPartToDto() throws DtoTransformException {
    	org.iso._21090.EnOn xml = new org.iso._21090.EnOn();
        EnOn result = ENONTransformer.INSTANCE.toDto(xml);
        assertTrue(result.getPart().isEmpty());
    }

}
