package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.iso21090.TelPerson;
import gov.nih.nci.iso21090.TelUrl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import java.net.URI;
import java.net.URISyntaxException;

import org.iso._21090.TEL;
import org.iso._21090.TELPerson;
import org.junit.Test;

/**
 * Tests tel transforms.
 */
public class TELTransformerTest extends AbstractTransformerTestBase<TELTransformer, TEL, Tel> {

    public static final URI MAILTO;
    public static final URI WEB;

    static {
        URI result = null;
        try {
            result = new URI("mailto:test@example.com");
        } catch (URISyntaxException e) {
        }
        MAILTO = result;

        result = null;
        try {
            result = new URI("http://www.example.com");
        } catch (URISyntaxException e) {
        }
        WEB = result;
    }

    @Override
    public Tel makeDtoSimple() {
        Tel tel = new TelPerson();
        tel.setValue(MAILTO);

        return tel;
    }

    @Override
    public TEL makeXmlSimple() {
        TEL tel = new org.iso._21090.TelUrl();
        tel.setValue("http://www.example.com");

        return tel;
    }

    @Override
    public void verifyDtoSimple(Tel x) {
        assertNotNull(x);
        assertTrue(x instanceof TelUrl);
        assertNull(x.getNullFlavor());
        assertEquals(x.getValue(), WEB);
    }

    @Override
    public void verifyXmlSimple(TEL x) {
        assertNotNull(x);
        assertTrue(x instanceof TELPerson);
        assertNull(x.getNullFlavor());
        assertEquals("mailto:test@example.com", x.getValue());
    }

    @Test
    public void testNullTel() throws Exception {
        Tel tel = new Tel();
        tel.setNullFlavor(NullFlavor.ASKU);
        TEL result = TELTransformer.INSTANCE.toXml(tel);
        assertNotNull(result);
        assertNull(result.getValue());
        assertEquals(org.iso._21090.NullFlavor.ASKU, result.getNullFlavor());

        tel = TELTransformer.INSTANCE.toDto(result);
        assertNotNull(tel);
        assertNull(tel.getValue());
        assertEquals(NullFlavor.ASKU, tel.getNullFlavor());
    }

    @Test(expected = DtoTransformException.class)
    public void testBadUri() throws Exception {
        TEL tel = new TEL();
        tel.setValue("http://!@#$%^&*(/");
        TELTransformer.INSTANCE.toDto(tel);
    }
}
