package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import org.iso._21090.IVLPQ;
import org.iso._21090.PQ;
import org.junit.Test;

/**
 * Tests for PQ interval transformer.
 */
public class IVLPQTransformerTest extends AbstractTransformerTestBase<IVLPQTransformer, IVLPQ, Ivl<Pq>> {

    @Override
    public Ivl<Pq> makeDtoSimple() {
        Ivl<Pq> result = new Ivl<Pq>();
        result.setAny(getPq(1.1));
        result.setHigh(getPq(2.2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getPq(-1.1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getPq(3.3));

        return result;
    }

    @Override
    public IVLPQ makeXmlSimple() {
        IVLPQ result = new IVLPQ();
        result.setHigh(getPQ(2.2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getPQ(-1.1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getPQ(3.3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Pq> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Pq> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
        assertEquals(expected.getHigh(), x.getHigh());
        assertEquals(expected.getHighClosed(), x.getHighClosed());
        assertEquals(expected.getLow(), x.getLow());
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertEquals(expected.getWidth(), x.getWidth());
    }

    @Override
    public void verifyXmlSimple(IVLPQ x) {
        IVLPQ expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(((PQ) expected.getWidth()).getValue(), ((PQ) x.getWidth()).getValue());

    }

    private Pq getPq(double value) {
        Pq result = new PQTransformerTest().makeDtoSimple();
        result.setValue(new Double(value));
        return result;
    }

    private PQ getPQ(double value) {
        PQ result = new PQTransformerTest().makeXmlSimple();
        result.setValue(value);
        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
        // set Any with equal high and low
        IVLPQ input = new IVLPQ();
        input.setWidth(getPQ(3.3));
        input.setHigh(getPQ(4.4));
        input.setLow(getPQ(4.4));
        Ivl<Pq> output = IVLPQTransformer.INSTANCE.toDto(input);
        assertEquals(output.getAny(), getPq(4.4));
        // set Any with some high and no low
        IVLPQ input2 = new IVLPQ();
        input2.setWidth(getPQ(3.3));
        input2.setHigh(getPQ(5.5));
        Ivl<Pq> output2 = IVLPQTransformer.INSTANCE.toDto(input2);
        assertEquals(output2.getAny(), getPq(5.5));
        // set Any with no high and some low
        IVLPQ input3 = new IVLPQ();
        input3.setWidth(getPQ(3.3));
        input3.setHigh(getPQ(1.1));
        Ivl<Pq> output3 = IVLPQTransformer.INSTANCE.toDto(input3);
        assertEquals(output3.getAny(), getPq(1.1));
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
        // set Any with equal high and low
        Ivl<Pq> input = new Ivl<Pq>();
        input.setAny(getPq(3.3));
        IVLPQ output = IVLPQTransformer.INSTANCE.toXml(input);
        assertEquals(output.getHigh().getValue(), getPQ(3.3).getValue());
        assertEquals(output.getLow().getValue(), getPQ(3.3).getValue());
        assertTrue(output.isHighClosed());
        assertTrue(output.isLowClosed());
        // set Any with some high and low not being equal
        Ivl<Pq> input2 = new Ivl<Pq>();
        input2.setWidth(getPq(3.3));
        input2.setHigh(getPq(5.5));
        input2.setLow(getPq(1.1));
        input2.setAny(getPq(3.3));
        IVLPQ output2 = IVLPQTransformer.INSTANCE.toXml(input2);
        assertEquals(output2.getHigh().getValue(), getPQ(5.5).getValue());
        assertEquals(output2.getLow().getValue(), getPQ(1.1).getValue());
    }

}
