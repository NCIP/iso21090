package gov.nih.nci.iso21090;

import gov.nih.nci.iso21090.TelEmail;
import gov.nih.nci.iso21090.TelPerson;

import java.net.URI;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author lpower
 */
public class TelEmailTest {

    private TelPerson t;
    private String TEL = "tel:";
    private String MAILTO = "mailto:";
    private String MAILTO_MIXED = "MAilto:";
    private String phrase = "+15556755745";

    @Before
    public void init() {
        t = new TelEmail();
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testValueAny() {
        t.setValue(URI.create(phrase));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueTel() {
        t.setValue(URI.create(TEL + phrase));
        assertEquals(t.getValue().toString(), (TEL + phrase));
    }

    @Test
    public void testValueMailto() {
        String s = MAILTO + "not+with+a+bang,+but+a+whimper";
        t.setValue(URI.create(s));
        assertEquals(t.getValue().toString(), s);
        
        s = MAILTO_MIXED + "not+with+a+bang,+but+a+whimper";
        t.setValue(URI.create(s));
        assertEquals(t.getValue().toString(), s);
    }

}