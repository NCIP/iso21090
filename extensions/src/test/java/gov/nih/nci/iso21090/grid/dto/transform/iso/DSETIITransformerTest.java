package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

import org.iso._21090.DSETII;
import org.iso._21090.II;
import org.iso._21090.NullFlavor;
import org.junit.Test;

public class DSETIITransformerTest extends AbstractTransformerTestBase<DSETIITransformer, DSETII, DSet<Ii>> {

	@Override
	public DSet<Ii> makeDtoSimple() {
		 DSet<Ii> result = new DSet<Ii>();
	     result.setItem(new HashSet<Ii>());
	     result.getItem().add(new IITransformerTest().makeDtoSimple());
	     return result;
	}

	@Override
	public DSETII makeXmlSimple() {
		 DSETII result = new DSETII();
		 result.getItem().add(new IITransformerTest().makeXmlSimple());
		 return result;
	}

	@Override
	public void verifyDtoSimple(DSet<Ii> x) {
		assertNotNull(x);
        assertEquals(1, x.getItem().size());
        for (Ii ii : x.getItem()) {
        	new IITransformerTest().verifyDtoSimple(ii);
        }

	}

	@Override
	public void verifyXmlSimple(DSETII x) {
		 assertNotNull(x);
	     assertNull(x.getNullFlavor());
	     assertEquals(1, x.getItem().size());
	     for (II ii : x.getItem()) {
	        	new IITransformerTest().verifyXmlSimple(ii);
	        }
	}
	@Override
	public void verifyXmlNull(DSETII x) {
	        assertNotNull(x);
	        assertEquals(NullFlavor.NI, x.getNullFlavor());
	        assertTrue(x.getItem().isEmpty());
	}

	@Test
	public void testNull() throws Exception {
		DSETII xml = new DSETII();
	    xml.setNullFlavor(NullFlavor.ASKU);
	    DSet<Ii> dto = DSETIITransformer.INSTANCE.toDto(xml);
	    assertNull(dto); // potentially, this could be non-null with an empty set (either would be fine),
	                     // but our converter converts to null so we check that here.

	    xml = DSETIITransformer.INSTANCE.toXml(null);
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto = new DSet<Ii>();
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto.setItem(new HashSet<Ii>());
	    dto.getItem().add(null);

	    Ii ii = new Ii();
	    ii.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
	    dto.getItem().add(ii);
	    xml = DSETIITransformer.INSTANCE.toXml(dto);
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());
	}

}
