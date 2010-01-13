package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

import org.iso._21090.CD;
import org.iso._21090.DSETCD;
import org.iso._21090.NullFlavor;
import org.junit.Test;

public class DSETCDTransformerTest extends
	AbstractTransformerTestBase<DSETCDTransformer,DSETCD,DSet<Cd>> {

	@Override
	public DSet<Cd> makeDtoSimple() {
		 DSet<Cd> result = new DSet<Cd>();
	     result.setItem(new HashSet<Cd>());
	     result.getItem().add(new CDTransformerTest().makeDtoSimple());
	     return result;
	}

	@Override
	public DSETCD makeXmlSimple() {
		 DSETCD result = new DSETCD();
		 result.getItem().add(new CDTransformerTest().makeXmlSimple());
		 return result;
	}

	@Override
	public void verifyDtoSimple(DSet<Cd> x) {
		assertNotNull(x);
        assertEquals(1, x.getItem().size());
        for (Cd cd : x.getItem()) {
        	new CDTransformerTest().verifyDtoSimple(cd);
        }
		
	}

	@Override
	public void verifyXmlSimple(DSETCD x) {
		 assertNotNull(x);
	     assertNull(x.getNullFlavor());
	     assertEquals(1, x.getItem().size());
	     for (CD cd : x.getItem()) {
	        	new CDTransformerTest().verifyXmlSimple(cd);
	        }
	}
	@Override
	public void verifyXmlNull(DSETCD x) {
	        assertNotNull(x);
	        assertEquals(NullFlavor.NI, x.getNullFlavor());
	        assertTrue(x.getItem().isEmpty());
	}

	@Test
	public void testNull() throws Exception {
		DSETCD xml = new DSETCD();
	    xml.setNullFlavor(NullFlavor.ASKU);
	    DSet<Cd> dto = DSETCDTransformer.INSTANCE.toDto(xml);
	    assertNull(dto); // potentially, this could be non-null with an empty set (either would be fine),
	                     // but our converter converts to null so we check that here.

	    xml = DSETCDTransformer.INSTANCE.toXml(null);
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto = new DSet<Cd>();
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto.setItem(new HashSet<Cd>());
	    dto.getItem().add(null);
	        
	    Cd cd = new Cd();
	    cd.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
	    dto.getItem().add(cd);
	    xml = DSETCDTransformer.INSTANCE.toXml(dto);
	    assertNotNull(xml);
	    assertTrue(xml.getItem().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());
	}

}
