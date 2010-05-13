package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Compression;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.ED;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 *
 * @author max
 */
public class EDTextTransformerTest extends AbstractTransformerTestBase<EDTextTransformer, org.iso._21090.EdText, EdText>{

    @Override
    public org.iso._21090.EdText makeXmlSimple() {
        ED x = new EDTransformerTest().makeXmlSimple();
        org.iso._21090.EdText edText = new org.iso._21090.EdText();
        edText.setValue(x.getValue());
        edText.setNullFlavor(x.getNullFlavor());
        return edText;
    }

    @Override
    public EdText makeDtoSimple() {
        Ed x = new EDTransformerTest().makeDtoSimple();
        EdText edText = new EdText();
        edText.setValue(x.getValue());
        edText.setNullFlavor(x.getNullFlavor());
        return edText;
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.EdText x) {
        assertEquals(EDTransformerTest.VALUE, x.getValue());
    }

    @Override
    public void verifyDtoSimple(EdText x) {
        assertEquals(EDTransformerTest.VALUE, x.getValue());
    }

    public org.iso._21090.EdText makeXmlNullFlavored() {
    	org.iso._21090.EdText x = new org.iso._21090.EdText();
        x.setNullFlavor(NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(EdText dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testEdTextNull() throws Exception {
        EdText ed = new EdText();
        ed.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        org.iso._21090.EdText result = EDTextTransformer.INSTANCE.toXml(ed);
        assertNotNull(result);
        assertEquals(NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.getValue());
    }

    @Test(expected = IllegalArgumentException.class )
    public void testCompression() {
        EdText edText = new EdText();
        edText.setCompression(Compression.DF);
    }

    @Test(expected = IllegalArgumentException.class )
    public void testData() {
        EdText edText = new EdText();
        edText.setData(new byte[1]);
    }
    
}