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
 * @author gax, Dan Dumitru
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
        i.setValueSet("valueSet");
        i.setValueSetVersion("valueSetVersion");
        
        Cd cd2 = new Cd();
        cd2.setCode("code2");
        cd2.setCodeSystem("ignored2");
        cd2.setCodeSystemName("ignored2");
        cd2.setCodeSystemVersion("ignored2");
        cd2.setDisplayName(new STTransformerTest().makeDtoSimple());
        cd2.setOriginalText(null);
        
        Set<Cd> translations = new HashSet<Cd>();
        translations.add(cd2);

        i.setTranslations(translations);
       
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
        x.setValueSet("valueSet");
        x.setValueSetVersion("valueSetVersion");   

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

        assertEquals(new STTransformerTest().makeXmlSimple().getValue(), x.getDisplayName().getValue());
        
        assertEquals(null,x.getOriginalText());
        assertEquals("valueSet",x.getValueSet());
        assertEquals("valueSetVersion",x.getValueSetVersion());

        if (x.getTranslations() != null) {
            CD cD = x.getTranslations().get(0);
            assertEquals("code2", cD.getCode());
            assertEquals("ignored2", cD.getCodeSystem());
            assertEquals("ignored2", cD.getCodeSystemName());
            assertEquals("ignored2", cD.getCodeSystemVersion());
            assertEquals(new STTransformerTest().makeXmlSimple().getValue(), cD.getDisplayName().getValue());
            assertEquals(null, cD.getOriginalText());
        }
        
    }


    @Override
    public void verifyDtoSimple(Cd i) {
        assertEquals("code", i.getCode());
        assertEquals("sys", i.getCodeSystem());
        assertEquals("name", i.getCodeSystemName());
        assertEquals("v", i.getCodeSystemVersion());

        assertEquals(new STTransformerTest().makeDtoSimple().getValue(),i.getDisplayName().getValue());
        assertEquals(null,i.getOriginalText());
        assertEquals("valueSet",i.getValueSet());
        assertEquals("valueSetVersion",i.getValueSetVersion());

        if (i.getTranslations() != null && i.getTranslations().iterator().hasNext()) {        
            Cd cd = i.getTranslations().iterator().next();
            assertEquals("code2", cd.getCode());
            assertEquals("ignored2", cd.getCodeSystem());
            assertEquals("ignored2", cd.getCodeSystemName());
            assertEquals("ignored2", cd.getCodeSystemVersion());
            assertEquals(new STTransformerTest().makeDtoSimple().getValue(), cd.getDisplayName().getValue());
            assertEquals(null, cd.getOriginalText());
        }
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