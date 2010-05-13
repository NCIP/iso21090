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
    public void testDescription() {
        St st = new St();
        st.setValue(phrase);    	
        t.setDescription(st);
        assertEquals(t.getDescription().getValue(), phrase);
    }

    @Test
    public void testValue() {
        t.setValue(phrase);
        assertEquals(t.getValue(), phrase);
    }
}