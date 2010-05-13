package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;
//import gov.nih.nci.iso21090.extensions.Id;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import org.junit.Test;

public class IdArrayTransformerTest {

    @Test
    public void toXml_null() throws DtoTransformException {
        assertNull(IdArrayTransformer.INSTANCE.toXml(null));
    }

    @Test
    public void toXml_empty() throws DtoTransformException {
        assertEquals(0, IdArrayTransformer.INSTANCE.toXml(new Ii[0]).length);
    }

    @Test
    public void toXml_values() throws DtoTransformException {
        Ii a = makeDtoSimple();
        Ii b = makeDtoSimple("456");
        org.iso._21090.Ii[] xmls = IdArrayTransformer.INSTANCE.toXml(new Ii[] { a, b });

        assertNotNull(xmls);
        assertEquals(2, xmls.length);
        verifyXml(xmls[0], a);
        verifyXml(xmls[1], b);
    }

    @Test
    public void toDto_null() throws DtoTransformException {
        assertNull(IdArrayTransformer.INSTANCE.toDto(null));
    }

    @Test
    public void toDto_empty() throws DtoTransformException {
        assertEquals(0, IdArrayTransformer.INSTANCE.toDto(new org.iso._21090.Ii[0]).length);
    }

    @Test
    public void toDto_values() throws DtoTransformException {
    	org.iso._21090.Ii a = makeXmlSimple();
    	org.iso._21090.Ii b = makeXmlSimple("456");
        Ii[] dtos = IdArrayTransformer.INSTANCE.toDto(new org.iso._21090.Ii[] { a, b });

        assertNotNull(dtos);
        assertEquals(2, dtos.length);
        verifyDto(dtos[0], a);
        verifyDto(dtos[1], b);

    }

    private void verifyXml(org.iso._21090.Ii expected, Ii value) {
        verifyDto(value, expected);
    }

    private void verifyDto(Ii expected, org.iso._21090.Ii value) {
        assertEquals(expected.getDisplayable(), value.isDisplayable());
        assertEquals(expected.getRoot(), value.getRoot());
        assertEquals(expected.getIdentifierName(), value.getIdentifierName());
        assertEquals(expected.getExtension(), value.getExtension());
        assertEquals(expected.getNullFlavor().name(), value.getNullFlavor().name());
        assertEquals(expected.getReliability().name(), value.getReliability().name());
        assertEquals(expected.getScope().name(), value.getScope().name());
    }

    private org.iso._21090.Ii makeXmlSimple(String extension) {
    	org.iso._21090.Ii id = makeXmlSimple();
        id.setExtension(extension);
        return id;
    }

    private org.iso._21090.Ii makeXmlSimple() {
    	org.iso._21090.Ii id = new org.iso._21090.Ii();
        id.setDisplayable(Boolean.TRUE);
        id.setRoot(IITransformerTest.ROOT);
        id.setIdentifierName(IITransformerTest.IDENTIFIER_NAME);
        id.setExtension("123");
        id.setNullFlavor(org.iso._21090.NullFlavor.OTH);
        id.setReliability(org.iso._21090.IdentifierReliability.ISS);
        id.setScope(org.iso._21090.IdentifierScope.VER);
        return id;
    }

    private Ii makeDtoSimple(String extension) {
        Ii id = makeDtoSimple();
        id.setExtension(extension);
        return id;
    }

    private Ii makeDtoSimple() {
        Ii id = new Ii();
        id.setDisplayable(Boolean.TRUE);
        id.setRoot(IITransformerTest.ROOT);
        id.setIdentifierName(IITransformerTest.IDENTIFIER_NAME);
        id.setExtension("123");
        id.setNullFlavor(NullFlavor.OTH);
        id.setReliability(IdentifierReliability.ISS);
        id.setScope(IdentifierScope.VER);
        return id;
    }
}
