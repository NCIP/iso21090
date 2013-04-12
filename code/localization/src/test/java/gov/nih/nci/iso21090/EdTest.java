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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.zip.GZIPOutputStream;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author mshestopalov
 */
public class EdTest {

    private Ed t;
    private final String phrase = "this+is+the=way+the+world+ends";

    @Before
    public void init() {
        t = new Ed();
    }

    @Test
    public void testEquality() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());
        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = new Ed();
        t2.setCompression(Compression.BZ);
        t2.setData(phrase.getBytes());
        t2.setNullFlavor(NullFlavor.NA);
        t2.setValue(phrase);

        assertTrue(t.equals(t2));
    }

    @Test
    public void testHashCode() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());
        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = new Ed();
        t2.setCompression(Compression.BZ);
        t2.setData(phrase.getBytes());
        t2.setNullFlavor(NullFlavor.NA);
        t2.setValue(phrase);
        assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void testCloneable() {
        t.setCompression(Compression.BZ);
        t.setData(phrase.getBytes());
        t.setNullFlavor(NullFlavor.NA);
        TelUrl a = new TelUrl();
        a.setValue(URI.create("http:"+phrase));
        t.setValue(phrase);
        assertTrue(t.equals(t));
        assertFalse(t.equals(null));

        Ed t2 = t.clone();

        assertTrue(t != t2);
        assertTrue(t.equals(t2));
        assertEquals(t.hashCode(), t2.hashCode());
    }

    @Test
    public void testNullUncompression() throws Exception {
        t.setData(null);
        InputStream is = t.getDataUncompressed();
        assertEquals(0, is.available());
    }

    @Test(expected = IOException.class)
    public void testNullUncompressionWithGZ() throws Exception {
        t.setData(null);
        t.setCompression(Compression.GZ);
        t.getDataUncompressed();  // valid GZIP streams have some preamble
    }

    @Test
    public void testEmptyUncompression() throws Exception {
        t.setData(new byte[] {});
        InputStream is = t.getDataUncompressed();
        assertEquals(0, is.available());
    }

    @Test
    public void testSimpleUncompression() throws Exception {
        byte[] bytes = new byte[] {1, 2, 3, 4, 5};
        t.setData(bytes);
        InputStream is = t.getDataUncompressed();
        for (int i = 1; i < 6; ++i) {
            assertEquals(i, is.read());
        }
        assertEquals(0, is.available());
    }

    @Test
    public void testGZEmptyUncompression() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(baos);
        gzos.close();
        t.setData(baos.toByteArray());
        t.setCompression(Compression.GZ);

        InputStream is = t.getDataUncompressed();
        baos = new ByteArrayOutputStream();
        int cur;
        while ((cur = is.read()) != -1) {
            baos.write(cur); // blindingly fast
        }
        is.close();
        assertEquals(0, baos.toByteArray().length);
    }

    @Test
    public void testGZSimpleUncompression() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(baos);
        byte[] value = "something to be gziped".getBytes();
        gzos.write(value);
        gzos.close();
        t.setData(baos.toByteArray());
        t.setCompression(Compression.GZ);

        InputStream is = t.getDataUncompressed();
        baos = new ByteArrayOutputStream();
        int cur;
        while ((cur = is.read()) != -1) {
            baos.write(cur); // blindingly fast
        }
        is.close();
        byte[] receivedBytes = baos.toByteArray();
        assertTrue(Arrays.equals(value, receivedBytes));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonGZUncompression() throws Exception {
        t.setData(new byte[] {1, 2, 3});
        t.setCompression(Compression.ZL);
        t.getDataUncompressed();
    }
}
