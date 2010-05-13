package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.TelPerson;

import java.net.URI;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lpower
 */
public class TelPersonTest {

    private TelPerson t;
    private final String TEL = "tel:";
    private final String XTEL = "x-text-tel:";
    private final String XFAX = "x-text-fax:";
    private final String MAILTO = "mailto:";
    private final String MAILTO_MIXED = "MailTO:";
    private final String phrase = "this+is+the+way+the+world+ends";

    @Before
    public void init() {
        t = new TelPerson();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValueAny() {
        t.setValue(URI.create(phrase));
    }

    @Test
    public void testValueTel() {
        String u = TEL + phrase;
        t.setValue(URI.create(u));
        assertEquals(u, t.getValue().toString());
    }

    @Test
    public void testValueXTel() {
        String u = XTEL + phrase;
        t.setValue(URI.create(u));
        assertEquals(u, t.getValue().toString());
    }

    @Test
    public void testValueXFax() {
        String u = XFAX + phrase;
        t.setValue(URI.create(u));
        assertEquals(u, t.getValue().toString());
    }

    @Test
    public void testValueMailto() {
        String u = MAILTO + phrase;
        t.setValue(URI.create(u));
        assertEquals(u, t.getValue().toString());
    }
    
    @Test
    public void testMixedCaseMailto() {
        String u = MAILTO_MIXED + phrase;
        t.setValue(URI.create(u));
        assertEquals(u, t.getValue().toString());
    }

    @Test
    public void testEquality() {
        TelPerson first = new TelPerson();
        first.setNullFlavor(NullFlavor.ASKU);
        String u = TEL + phrase;
        first.setValue(URI.create(u));
        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        TelPerson second = new TelPerson();
        second.setNullFlavor(NullFlavor.ASKU);
        String u2 = TEL + phrase;
        second.setValue(URI.create(u2));

        assertTrue(first.equals(second));

        String u3 = TEL + phrase + "wrong";
        second.setValue(URI.create(u3));

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           TelPerson first = new TelPerson();
           first.setNullFlavor(NullFlavor.ASKU);
           String u = TEL + phrase;
           first.setValue(URI.create(u));

           TelPerson second = new TelPerson();
           second.setNullFlavor(NullFlavor.ASKU);
           String u2 = TEL + phrase;
           second.setValue(URI.create(u2));
           assertEquals(first.hashCode(), second.hashCode());

           String u3 = TEL + phrase + "wrong";
           second.setValue(URI.create(u3));

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           TelPerson first = new TelPerson();
           first.setNullFlavor(NullFlavor.ASKU);
           String u = TEL + phrase;
           first.setValue(URI.create(u));

           TelPerson second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }


}