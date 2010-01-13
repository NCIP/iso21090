package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.ArrayList;
import java.util.List;

import org.iso._21090.AD;
import org.iso._21090.ADXP;

public class ADTransformerTest extends AbstractTransformerTestBase<ADTransformer, AD, Ad> {
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
    public AD makeXmlSimple() {
        AD xml = new AD();
        ADXP part1 = new ADXP();
        part1.setCode(CODE);
        part1.setValue(VAL);
        xml.getPart().add(part1);
        return xml;
    }

    @Override
    public void verifyDtoSimple(Ad x) {
        assertEquals(x.getPart().get(0).getCode(), CODE);
        assertEquals(x.getPart().get(0).getValue(), VAL);
    }

    @Override
    public void verifyXmlSimple(AD x) {
        assertEquals(x.getPart().get(0).getCode(), CODE);
        assertEquals(x.getPart().get(0).getValue(), VAL);
    }

    @Override
    public void verifyXmlNull(AD x) {
        assertNull(x);
    }
}
