package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.CD;
import org.junit.Test;

/**
 *
 * @author gax
 */
public class CDTransformerTest extends AbstractTransformerTestBase<CDTransformer, CD, Cd> {

    @Override
    public Cd makeDtoSimple() {
        Cd i = new Cd();
        i.setCode("code");
        i.setCodeSystem("ignored");
        i.setCodeSystemName("ignored");
        i.setCodeSystemVersion("ignored");
        i.setDisplayName(new STTransformerTest().makeDtoSimple());
        i.setOriginalText(null);
        return i;
    }

    @Override
    public CD makeXmlSimple() {
        CD x = new CD();
        x.setCode("code");
        x.setCodeSystem("sys");
        x.setCodeSystemName("name");
        x.setCodeSystemVersion("v");
        x.setControlActExtension("cae");
        x.setControlActRoot("r");
        x.setDisplayName(new STTransformerTest().makeXmlSimple());

        return x;
    }

    @Override
    public void verifyXmlSimple(CD x) {
        assertEquals("code", x.getCode());
        assertNull(x.getCodeSystem());
        assertNull(x.getCodeSystemName());
        assertNull(x.getCodeSystemVersion());
        assertNull(x.getDisplayName());
        assertNull(x.getControlActRoot());
        assertNull(x.getDisplayName());
    }


    @Override
    public void verifyDtoSimple(Cd i) {
        assertEquals("code", i.getCode());
        assertNull(i.getCodeSystem());
        assertNull(i.getCodeSystemName());
        assertNull( i.getCodeSystemVersion());
        assertNull(i.getDisplayName());
    }

    @Test
    public void testCdCodeNull() throws Exception {
        Cd cd = new Cd();
        cd.setNullFlavor(NullFlavor.ASKU);
        CD result = CDTransformer.INSTANCE.toXml(cd);
        assertNotNull(result);
        assertNull(result.getCode());
        assertEquals(org.iso._21090.NullFlavor.ASKU, result.getNullFlavor());

        cd = CDTransformer.INSTANCE.toDto(result);
        assertNotNull(cd);
        assertNull(cd.getCode());
        assertEquals(NullFlavor.ASKU, cd.getNullFlavor());
    }
}