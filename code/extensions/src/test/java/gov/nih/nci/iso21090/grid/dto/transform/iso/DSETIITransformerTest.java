//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

import org.iso._21090.DSetII;
import org.iso._21090.NullFlavor;
import org.junit.Test;

public class DSETIITransformerTest extends AbstractTransformerTestBase<DSETIITransformer, DSetII, DSet<Ii>> {

	@Override
	public DSet<Ii> makeDtoSimple() {
		 DSet<Ii> result = new DSet<Ii>();
	     result.setItem(new HashSet<Ii>());
	     result.getItem().add(new IITransformerTest().makeDtoSimple());
	     return result;
	}

	@Override
	public DSetII makeXmlSimple() {
		DSetII result = new DSetII();
		 result.getItems().add(new IITransformerTest().makeXmlSimple());
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
	public void verifyXmlSimple(DSetII x) {
		 assertNotNull(x);
	     assertNull(x.getNullFlavor());
	     assertEquals(1, x.getItems().size());
	     for (org.iso._21090.Ii ii : x.getItems()) {
	        	new IITransformerTest().verifyXmlSimple(ii);
	        }
	}
	@Override
	public void verifyXmlNull(DSetII x) {
	        assertNotNull(x);
	        assertEquals(NullFlavor.NI, x.getNullFlavor());
	        assertTrue(x.getItems().isEmpty());
	}

	@Test
	public void testNull() throws Exception {
		DSetII xml = new DSetII();
	    xml.setNullFlavor(NullFlavor.ASKU);
	    DSet<Ii> dto = DSETIITransformer.INSTANCE.toDto(xml);
	    assertNull(dto); // potentially, this could be non-null with an empty set (either would be fine),
	                     // but our converter converts to null so we check that here.

	    xml = DSETIITransformer.INSTANCE.toXml(null);
	    assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto = new DSet<Ii>();
	    assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());

	    dto.setItem(new HashSet<Ii>());
	    dto.getItem().add(null);

	    Ii ii = new Ii();
	    ii.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
	    dto.getItem().add(ii);
	    xml = DSETIITransformer.INSTANCE.toXml(dto);
	    assertNotNull(xml);
	    assertTrue(xml.getItems().isEmpty());
	    assertEquals(NullFlavor.NI, xml.getNullFlavor());
	}

}
