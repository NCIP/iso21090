package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.NullFlavor;
import org.junit.Test;

public class IITransformerTest extends AbstractTransformerTestBase<IITransformer,org.iso._21090.Ii,Ii>{
     /**
     * The identifier name.
     */
    public static final String IDENTIFIER_NAME = "identifier name";

    /**
     * The ii root value.
     */
    public static final String ROOT = "2.16.840.1.113883.3.26.4.4.3";

    @Override
    public Ii makeDtoSimple() {
          Ii id = new Ii();
          id.setDisplayable(Boolean.TRUE);
          id.setRoot(ROOT);
          id.setIdentifierName(IDENTIFIER_NAME);
          id.setExtension("123");
          id.setReliability(IdentifierReliability.ISS);
          id.setScope(IdentifierScope.VER);
          return id;
    }

    @Override
    public org.iso._21090.Ii makeXmlSimple() {
    	org.iso._21090.Ii id = new org.iso._21090.Ii();
          id.setDisplayable(Boolean.TRUE);
          id.setRoot(ROOT);
          id.setIdentifierName(IDENTIFIER_NAME);
          id.setExtension("123");
          id.setReliability(org.iso._21090.IdentifierReliability.ISS);
          id.setScope(org.iso._21090.IdentifierScope.VER);
          return id;
    }

    @Override
    public void verifyDtoSimple(Ii x) {
        assertEquals(x.getDisplayable(),Boolean.TRUE);
        assertEquals(x.getRoot(),ROOT);
        assertEquals(x.getIdentifierName(),IDENTIFIER_NAME);
        assertEquals(x.getExtension(), "123");
        assertNull(x.getNullFlavor());
        assertEquals(x.getReliability(),IdentifierReliability.ISS);
        assertEquals(x.getScope(),IdentifierScope.VER);

    }

    @Override
    public void verifyXmlSimple(org.iso._21090.Ii x) {
        assertEquals(x.getRoot(), ROOT);
        assertEquals(x.getIdentifierName(), IDENTIFIER_NAME);
        assertEquals(x.getExtension(), "123");
        assertNull(x.getNullFlavor());
        assertEquals(x.getReliability(), org.iso._21090.IdentifierReliability.ISS);
        assertEquals(x.getScope(), org.iso._21090.IdentifierScope.VER);
    }

    @Test
    public void testNullFlavorConversion() {
    	org.iso._21090.Ii x = new org.iso._21090.Ii();
        x.setNullFlavor(NullFlavor.ASKU);
        Ii y = IITransformer.INSTANCE.toDto(x);
        assertNotNull(y);
        assertEquals(y.getNullFlavor(), gov.nih.nci.iso21090.NullFlavor.ASKU);
        x = IITransformer.INSTANCE.toXml(y);
        assertNotNull(x);
        assertEquals(x.getNullFlavor(), NullFlavor.ASKU);
    }
}
