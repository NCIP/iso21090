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

package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.Compression;
import gov.nih.nci.iso21090.EdText;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lpower
 */
public class EdTextTest {

    private EdText t;
    private final String phrase = "this+is+the=way+the+world+ends";

    @Before
    public void init() {
        t = new EdText();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCompression() {
        t.setCompression(Compression.BZ);
    }

    @Test
    public void testNullCompression() {
        t.setCompression(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testData() {
        byte[] a = phrase.getBytes();
        t.setData(a);
    }

    @Test
    public void testNullData() {
        t.setData(null);
        assertNull(t.getData());
    }

    @Test
    public void testValue() {
        t.setValue(phrase);
        assertEquals(t.getValue(), phrase);
    }
}