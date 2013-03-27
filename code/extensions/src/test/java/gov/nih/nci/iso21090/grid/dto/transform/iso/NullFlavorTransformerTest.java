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
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.NullFlavor;

/**
 * @author Todd Parnell
 *
 */
public class NullFlavorTransformerTest extends
        AbstractTransformerTestBase<NullFlavorTransformer, NullFlavor, gov.nih.nci.iso21090.NullFlavor> {

    @Override
    public gov.nih.nci.iso21090.NullFlavor makeDtoSimple() {
        return gov.nih.nci.iso21090.NullFlavor.ASKU;
    }

    @Override
    public NullFlavor makeXmlSimple() {
        return NullFlavor.ASKU;
    }

    @Override
    public void verifyDtoSimple(gov.nih.nci.iso21090.NullFlavor x) {
        assertNotNull(x);
        assertEquals(gov.nih.nci.iso21090.NullFlavor.ASKU, x);
    }

    @Override
    public void verifyXmlSimple(NullFlavor x) {
        assertNotNull(x);
        assertEquals(NullFlavor.ASKU, x);
    }
}
