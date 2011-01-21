package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.iso21090.TelUrl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

import org.iso._21090.DSetTel;
import org.iso._21090.NullFlavor;
import org.iso._21090.TEL;
import org.junit.Test;

/**
 * @author Todd Parnell
 *
 */
public class DSETTelTransformerTest
    extends AbstractTransformerTestBase<DSETTELTransformer, org.iso._21090.DSetTel, DSet<Tel>> {

    @Override
    public DSet<Tel> makeDtoSimple() {
        DSet<Tel> result = new DSet<Tel>();
        result.setItem(new HashSet<Tel>());
        Tel item = new Tel();
        item.setValue(TELTransformerTest.MAILTO);
        result.getItem().add(item);
        item = new TelUrl();
        item.setValue(TELTransformerTest.WEB);
        result.getItem().add(item);

        return result;
    }

    @Override
    public DSetTel makeXmlSimple() {
    	DSetTel result = new DSetTel();
        TEL item = new TEL();
        item.setValue(TELTransformerTest.MAILTO.toASCIIString());
        result.getItems().add(item);
        item = new TEL();
        item.setValue(TELTransformerTest.WEB.toASCIIString());
        result.getItems().add(item);

        return result;
    }

    @Override
    public void verifyDtoSimple(DSet<Tel> x) {
        assertNotNull(x);
        assertEquals(2, x.getItem().size());
        for (Tel t : x.getItem()) {
            assertNotNull(t);
            assertNull(t.getNullFlavor());
            assertNotNull(t.getValue());
        }
    }

    @Override
    public void verifyXmlSimple(DSetTel x) {
        assertNotNull(x);
        assertNull(x.getNullFlavor());
        assertEquals(2, x.getItems().size());
        for (TEL t : x.getItems()) {
            assertNotNull(t);
            assertNull(t.getNullFlavor());
            assertNotNull(t.getValue());
        }
    }

    @Override
    public void verifyXmlNull(DSetTel x) {
        assertNotNull(x);
        assertEquals(NullFlavor.NI, x.getNullFlavor());
        assertTrue(x.getItems().isEmpty());
    }

    @Test
    public void testNull() throws Exception {
    	DSetTel xml = new DSetTel();
        xml.setNullFlavor(NullFlavor.ASKU);
        DSet<Tel> dto = DSETTELTransformer.INSTANCE.toDto(xml);
        assertNull(dto); // potentially, this could be non-null with an empty set (either would be fine),
                         // but our converter converts to null so we check that here.

        xml = DSETTELTransformer.INSTANCE.toXml(null);
        assertNotNull(xml);
        assertTrue(xml.getItems().isEmpty());
        assertEquals(NullFlavor.NI, xml.getNullFlavor());

        dto = new DSet<Tel>();
        assertNotNull(xml);
        assertTrue(xml.getItems().isEmpty());
        assertEquals(NullFlavor.NI, xml.getNullFlavor());

        dto.setItem(new HashSet<Tel>());
        dto.getItem().add(null);
        Tel tel = new Tel();
        tel.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        dto.getItem().add(tel);
        xml = DSETTELTransformer.INSTANCE.toXml(dto);
        assertNotNull(xml);
        assertTrue(xml.getItems().isEmpty());
        assertEquals(NullFlavor.NI, xml.getNullFlavor());
    }
}
