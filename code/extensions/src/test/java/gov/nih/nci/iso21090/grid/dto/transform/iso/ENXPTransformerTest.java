package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;

import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

public class ENXPTransformerTest extends AbstractTransformerTestBase<ENXPTransformer, org.iso._21090.ENXP, Enxp>{

    @Override
    public Enxp makeDtoSimple() {
        Enxp dto = new Enxp(EntityNamePartType.DEL);
        dto.setCode("code");
        dto.setCodeSystem("code string");
        dto.setCodeSystemVersion("codeSystemVersion");
        dto.setValue("value");
        dto.setQualifier(new HashSet<EntityNamePartQualifier>());
        dto.getQualifier().add(EntityNamePartQualifier.AC);
        dto.getQualifier().add(EntityNamePartQualifier.AD);
        return dto;
    }

    @Override
    public org.iso._21090.ENXP makeXmlSimple() {
    	org.iso._21090.ENXP xml = new org.iso._21090.ENXP();
        xml.setType(org.iso._21090.EntityNamePartType.DEL);
        xml.setCode("code");
        xml.setCodeSystem("code string");
        xml.setCodeSystemVersion("codeSystemVersion");
        xml.setValue("value");
        xml.getQualifiers().add(org.iso._21090.EntityNamePartQualifier.AC);
        xml.getQualifiers().add(org.iso._21090.EntityNamePartQualifier.AD);
        return xml;
    }

    @Override
    public void verifyDtoSimple(Enxp x) {
        assertEquals(x.getCode(), "code");
        assertEquals(x.getCodeSystem(), "code string");
        assertEquals(x.getCodeSystemVersion(), "codeSystemVersion");
        assertEquals(x.getValue(), "value");
        assertEquals(x.getQualifier().size(), 2);
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.ENXP x) {
        assertEquals(x.getCode(), "code");
        assertEquals(x.getCodeSystem(), "code string");
        assertEquals(x.getCodeSystemVersion(), "codeSystemVersion");
        assertEquals(x.getValue(), "value");
        assertEquals(x.getQualifiers().size(), 2);
    }

}
