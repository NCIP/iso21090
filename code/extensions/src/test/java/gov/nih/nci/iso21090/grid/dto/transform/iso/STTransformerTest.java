//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.NullFlavor;
import org.iso._21090.ST;
import org.junit.Test;

/**
 *
 * @author gax
 */
public class STTransformerTest extends AbstractTransformerTestBase<STTransformer, ST, St>{

    @Override
    public ST makeXmlSimple() {
        ST x = new ST();
        x.setValue("v");
        return x;
    }

    @Override
    public St makeDtoSimple() {
        St x = new St();
        x.setValue("v");
        return x;
    }

    @Override
    public void verifyXmlSimple(ST x) {
        assertEquals("v", x.getValue());
    }

    @Override
    public void verifyDtoSimple(St x) {
        assertEquals("v", x.getValue());
    }

    public ST makeXmlNullFlavored() {
        ST x = new ST();
        x.setNullFlavor(NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(St dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testStNull() throws Exception {
        St st = new St();
        st.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        ST result = STTransformer.INSTANCE.toXml(st);
        assertNotNull(result);
        assertEquals(NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.getValue());
    }
}