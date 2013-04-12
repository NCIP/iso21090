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
import static org.junit.Assert.fail;

import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.iso21090.TelUrl;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lpower
 */
public class TelUrlTest {

    private TelUrl t;
    private final String FILE = "file:";
    private final String NFS = "nfs:";
    private final String FTP = "ftp:";
    private final String HTTP = "http:";
    private final String HTTPS = "https:";
    private final String phrase = "this+is+the+way+the+world+ends";

    @Before
    public void init() {
        t = new TelUrl();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueAny() {
        t.setValue(URI.create(phrase));
    }

    public static void testAllowed(Tel t, String... allowed) {
        for (String scheme : allowed) {
            String u = scheme + "not+with+a+bang,+but+a+whimper";
            t.setValue(URI.create(u));
            assertEquals(u, t.getValue().toString());
        }
    }

    public static void testDisallowed(Tel t, String... disallowed) {
        for (String scheme : disallowed) {
            String u = scheme + "this+is+the+way+the+world+ends";
            URI uri = URI.create(u);
            try{
                t.setValue(uri);
                fail(scheme);
            } catch (IllegalArgumentException i) {
            }
        }
    }

    @Test
    public void testSchemes() {
        testAllowed(t, FILE, NFS, FTP, HTTP, HTTPS, "File:", "HttpS:");
        testDisallowed(t, "", "mailto:");
    }


}