package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENONTransformer;

import org.iso._21090.ENON;
import org.iso._21090.ENXP;
import org.junit.Test;

public class ENONTransformerTest extends AbstractTransformerTestBase<ENONTransformer, ENON, EnOn>{

    @Override
    public EnOn makeDtoSimple() {
        EnOn dto = new EnOn();
        Enxp o = new Enxp(EntityNamePartType.DEL);
        dto.getPart().add(o);
        return dto;
    }

    @Override
    public ENON makeXmlSimple() {
        ENON xml = new ENON();
        ENXP o = new ENXP();
        o.setType(org.iso._21090.EntityNamePartType.DEL);
        xml.getPart().add(o);
        return xml;
    }

    @Override
    public void verifyDtoSimple(EnOn x) {
        assertEquals(x.getPart().size(),1);
        assertEquals(x.getPart().get(0).getType(),EntityNamePartType.DEL);
    }

    @Override
    public void verifyXmlSimple(ENON x) {
        assertEquals(x.getPart().size(),1);
        assertEquals(x.getPart().get(0).getType(),org.iso._21090.EntityNamePartType.DEL);

    }

    @Test
    public void testNullPartToXml() throws DtoTransformException {
        EnOn dto = new EnOn();
        ENON result = ENONTransformer.INSTANCE.toXml(dto);
        assertTrue(result.getPart().isEmpty());
    }

    @Test
    public void testNullPartToDto() throws DtoTransformException {
        ENON xml = new ENON();
        EnOn result = ENONTransformer.INSTANCE.toDto(xml);
        assertTrue(result.getPart().isEmpty());
    }

}
