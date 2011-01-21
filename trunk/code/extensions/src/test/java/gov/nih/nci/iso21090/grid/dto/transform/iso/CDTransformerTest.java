package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        
        
        Cd cd2 = new Cd();
        cd2.setCode("code2");
        cd2.setCodeSystem("ignored2");
        cd2.setCodeSystemName("ignored2");
        cd2.setCodeSystemVersion("ignored2");
        cd2.setDisplayName(new STTransformerTest().makeDtoSimple());
        cd2.setOriginalText(null);
        
        
        Set<Cd> translations = new HashSet<Cd>();
        translations.add(cd2);
       
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


        CD cd2 = new CD();
        cd2.setCode("code2");
        cd2.setCodeSystem("ignored2");
        cd2.setCodeSystemName("ignored2");
        cd2.setCodeSystemVersion("ignored2");
        cd2.setDisplayName(new STTransformerTest().makeXmlSimple());
        cd2.setOriginalText(null);
 
        
        List<CD> aList = new ArrayList<CD>();
        aList.add(cd2);
        x.getTranslations().addAll(aList);

        return x;
    }

    @Override
    public void verifyXmlSimple(CD x) {
        assertEquals("code", x.getCode());
        assertEquals("ignored", x.getCodeSystem());
        assertEquals("ignored", x.getCodeSystemName());
        assertEquals("ignored", x.getCodeSystemVersion());
        
    }


    @Override
    public void verifyDtoSimple(Cd i) {
        assertEquals("code", i.getCode());
        assertEquals("sys", i.getCodeSystem());
        assertEquals("name", i.getCodeSystemName());
        assertEquals("v", i.getCodeSystemVersion());
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