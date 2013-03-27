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
import gov.nih.nci.iso21090.Bl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.BL;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 *
 * @author max
 */
public class BLTransformerTest extends AbstractTransformerTestBase<BLTransformer, BL, Bl>{

    @Override
    public BL makeXmlSimple() {
        BL x = new BL();
        x.setValue(true);
        return x;
    }

    @Override
    public Bl makeDtoSimple() {
        Bl x = new Bl();
        x.setValue(true);
        return x;
    }

    @Override
    public void verifyXmlSimple(BL x) {
        assertEquals(true, x.isValue());
    }

    @Override
    public void verifyDtoSimple(Bl x) {
        assertEquals(true, x.getValue());
    }

    public BL makeXmlNullFlavored() {
        BL x = new BL();
        x.setNullFlavor(NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(Bl dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testBlNull() throws Exception {
        Bl bl = new Bl();
        bl.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        BL result = BLTransformer.INSTANCE.toXml(bl);
        assertNotNull(result);
        assertEquals(NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.isValue());
    }
}