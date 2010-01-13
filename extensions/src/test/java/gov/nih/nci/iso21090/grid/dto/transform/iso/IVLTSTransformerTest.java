package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.iso._21090.IVLTS;
import org.iso._21090.TS;
import org.junit.Test;

/**
 * Tests for timestamp interval transformer.
 */
public class IVLTSTransformerTest extends AbstractTransformerTestBase<IVLTSTransformer, IVLTS, Ivl<Ts>> {

    @Override
    public Ivl<Ts> makeDtoSimple() {
        Ivl<Ts> result = new Ivl<Ts>();
        result.setAny(getTs(1));
        result.setHigh(getTs(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getTs(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
        result.setWidth(getTs(3));

        return result;
    }

    @Override
    public IVLTS makeXmlSimple() {
        IVLTS result = new IVLTS();
        result.setHigh(getTS(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getTS(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
        result.setWidth(getTS(3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Ts> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Ts> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
        assertEquals(expected.getHigh(), x.getHigh());
        assertEquals(expected.getHighClosed(), x.getHighClosed());
        assertEquals(expected.getLow(), x.getLow());
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertEquals(expected.getOriginalText(), x.getOriginalText());
        assertEquals(expected.getWidth(), x.getWidth());
    }

    @Override
    public void verifyXmlSimple(IVLTS x) {
        IVLTS expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(expected.getOriginalText().getValue(), x.getOriginalText().getValue());
        assertEquals(((TS) expected.getWidth()).getValue(), ((TS) x.getWidth()).getValue());

    }

    private Ts getTs(int offsetFromDefault) {
        Ts result = new TSTransformerTest().makeDtoSimple();
        Calendar c = Calendar.getInstance();
        c.setTime(result.getValue());
        c.add(Calendar.DATE, offsetFromDefault);
        result.setValue(c.getTime());

        return result;
    }

    private TS getTS(int offsetFromDefault) {
        SimpleDateFormat sdf = new SimpleDateFormat(TSTransformer.FORMAT_STRING);
        TS result = new TSTransformerTest().makeXmlSimple();
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(result.getValue()));
        } catch (ParseException e) {
            fail(e.toString());
        }
        c.add(Calendar.DATE, offsetFromDefault);
        result.setValue(sdf.format(c.getTime()));

        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
         // set Any with equal high and low
         IVLTS input = new IVLTS();
         input.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
         input.setWidth(getTS(3));
         input.setHigh(getTS(4));
         input.setLow(getTS(4));
         Ivl<Ts> output = IVLTSTransformer.INSTANCE.toDto(input);
         assertEquals(output.getAny(), getTs(4));
         // set Any with some high and no low
         IVLTS input2 = new IVLTS();
         input2.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
         input2.setWidth(getTS(3));
         input2.setHigh(getTS(5));
         Ivl<Ts> output2 = IVLTSTransformer.INSTANCE.toDto(input2);
         assertEquals(output2.getAny(), getTs(5));
         // set Any with no high and some low
         IVLTS input3 = new IVLTS();
         input3.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
         input3.setWidth(getTS(3));
         input3.setHigh(getTS(1));
         Ivl<Ts> output3 = IVLTSTransformer.INSTANCE.toDto(input3);
         assertEquals(output3.getAny(), getTs(1));
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
         // set Any with equal high and low
         Ivl<Ts> input = new Ivl<Ts>();
         input.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
         input.setAny(getTs(3));
         IVLTS output = IVLTSTransformer.INSTANCE.toXml(input);
         assertEquals(output.getHigh().getValue(), getTS(3).getValue());
         assertEquals(output.getLow().getValue(), getTS(3).getValue());
         assertTrue(output.isHighClosed());
         assertTrue(output.isLowClosed());
         // set Any with some high and low not being equal
         Ivl<Ts> input2 = new Ivl<Ts>();
         input2.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
         input2.setWidth(getTs(3));
         input2.setHigh(getTs(5));
         input2.setLow(getTs(1));
         input2.setAny(getTs(3));
         IVLTS output2 = IVLTSTransformer.INSTANCE.toXml(input2);
         assertEquals(output2.getHigh().getValue(), getTS(5).getValue());
         assertEquals(output2.getLow().getValue(), getTS(1).getValue());
    }

}
