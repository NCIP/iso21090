package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.iso._21090.DSetAd;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 * @author mshestopalov
 *
 */
public class DSETADTransformerTest
    extends AbstractTransformerTestBase<DSETADTransformer, DSetAd, DSet<Ad>> {

    public final String CODE1 = "code1";
    public final String CODE2= "code2";
    public final String VALUE1 = "value1";
    public final String VALUE2= "value2";

    @Override
    public DSet<Ad> makeDtoSimple() {
        DSet<Ad> result = new DSet<Ad>();
        result.setItem(new HashSet<Ad>());
        Adxp adxp1 = new Adxp();
        adxp1.setCode(CODE1);
        adxp1.setValue(VALUE1);

        Ad item1 = new Ad();
        List<Adxp> li1 = new ArrayList<Adxp>();
        li1.add(adxp1);
        item1.setPart(li1);


        result.getItem().add(item1);

        return result;
    }

    @Override
    public DSetAd makeXmlSimple() {
    	DSetAd result = new DSetAd();
        org.iso._21090.ADXP part1 = new org.iso._21090.ADXP();
        part1.setCode(CODE1);
        part1.setValue(VALUE1);

        org.iso._21090.Ad item1 = new org.iso._21090.Ad();
        item1.getParts().add(part1);

        result.getItems().add(item1);

        return result;
    }

    @Override
    public void verifyDtoSimple(DSet<Ad> x) {
        assertNotNull(x);
        assertEquals(1, x.getItem().size());
        for (Ad t : x.getItem()) {
            assertNotNull(t);
            assertNull(t.getNullFlavor());
            assertEquals(1, t.getPart().size());
            assertEquals(t.getPart().get(0).getCode(), CODE1);
            assertEquals(t.getPart().get(0).getValue(), VALUE1);
        }
    }

    @Override
    public void verifyXmlSimple(DSetAd x) {
        assertNotNull(x);
        assertNull(x.getNullFlavor());
        assertEquals(1, x.getItems().size());
        for (org.iso._21090.Ad t : x.getItems()) {
            assertNotNull(t);
            assertNull(t.getNullFlavor());
            assertEquals(1, t.getParts().size());
            assertEquals(t.getParts().get(0).getCode(), CODE1);
            assertEquals(t.getParts().get(0).getValue(), VALUE1);
        }
    }

    @Override
    public void verifyXmlNull(DSetAd x) {
        assertNotNull(x);
        assertEquals(NullFlavor.NI, x.getNullFlavor());
        assertTrue(x.getItems().isEmpty());
    }

    @Test
    public void testNull() throws Exception {
    	DSetAd xml = new DSetAd();
        xml.setNullFlavor(NullFlavor.ASKU);
        DSet<Ad> dto = DSETADTransformer.INSTANCE.toDto(xml);
        assertNull(dto); // potentially, this could be non-null with an empty set (either would be fine),
        // but our converter converts to null so we check that here.

        xml = DSETADTransformer.INSTANCE.toXml(null);
        assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

        dto = new DSet<Ad>();
	    assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

        dto.setItem(new HashSet<Ad>());
        dto.getItem().add(null);

        Ad ad = new Ad();
        ad.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        dto.getItem().add(ad);

        xml = DSETADTransformer.INSTANCE.toXml(dto);
        assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());
    }
}
