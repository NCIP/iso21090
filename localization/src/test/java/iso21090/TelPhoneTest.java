package gov.nih.nci.iso21090;

import gov.nih.nci.iso21090.TelPhone;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author lpower
 */
public class TelPhoneTest {

    private TelPhone t;
    private String TEL = "tel:";
    private String XTEL = "x-text-tel:";
    private String XFAX = "x-text-fax:";
    private String MAILTO = "mailto:";
    private String phrase = "this+is+the+way+the+world+ends";

    @Before
    public void init() {
        t = new TelPhone();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testValueAny() {
        t.setValue(URI.create(phrase));
    }


    
    @Test
    public void test() {
        TelUrlTest.testAllowed(t, XFAX, XTEL, TEL, "TEL:", "X-Text-Fax:");
        TelUrlTest.testDisallowed(t, "", MAILTO, "foo:");
    }

}