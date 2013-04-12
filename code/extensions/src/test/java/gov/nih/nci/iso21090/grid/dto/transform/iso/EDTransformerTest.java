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

import gov.nih.nci.iso21090.Compression;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.Arrays;

import org.iso._21090.ED;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 *
 * @author max
 */
public class EDTransformerTest extends AbstractTransformerTestBase<EDTransformer, ED, Ed>{

    public static final String CONT_ACT_EXT = "cont_act_ext";
    public static final byte[] DATA_BYTES = {1,0,1,0,1,0,1,0};
    public static final String VALUE = "value";

    @Override
    public ED makeXmlSimple() {
        ED x = new ED();
        x.setCompression(org.iso._21090.Compression.DF);
        x.setData(DATA_BYTES);
        x.setValue(VALUE);
        return x;
    }

    @Override
    public Ed makeDtoSimple() {
        Ed x = new Ed();
        x.setCompression(Compression.DF);
        x.setData(DATA_BYTES);
        x.setValue(VALUE);
        return x;
    }

    @Override
    public void verifyXmlSimple(ED x) {
        assertEquals(org.iso._21090.Compression.DF, x.getCompression());
        assertTrue(Arrays.equals(DATA_BYTES, x.getData()));
        assertEquals(VALUE, x.getValue());
    }

    @Override
    public void verifyDtoSimple(Ed x) {
        assertEquals(org.iso._21090.Compression.DF.value(), x.getCompression().name());
        assertTrue(Arrays.equals(DATA_BYTES, x.getData()));
        assertEquals(VALUE, x.getValue());
    }

    public ED makeXmlNullFlavored() {
        ED x = new ED();
        x.setNullFlavor(NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(Ed dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testEdNull() throws Exception {
        Ed ed = new Ed();
        ed.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        ED result = EDTransformer.INSTANCE.toXml(ed);
        assertNotNull(result);
        assertEquals(NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.getValue());
    }
}