//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import org.iso._21090.INT;
import org.iso._21090.IVLINT;
import org.junit.Test;

/**
 * Tests for integer interval transformer.
 */
public class IVLINTTransformerTest extends AbstractTransformerTestBase<IVLINTTransformer, IVLINT, Ivl<Int>> {

    @Override
    public Ivl<Int> makeDtoSimple() {
        Ivl<Int> result = new Ivl<Int>();
        result.setAny(getInt(1));
        result.setHigh(getInt(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getInt(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getInt(3));

        return result;
    }

    @Override
    public IVLINT makeXmlSimple() {
        IVLINT result = new IVLINT();
        result.setHigh(getINT(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getINT(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getINT(3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Int> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Int> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
        assertEquals(expected.getHigh(), x.getHigh());
        assertEquals(expected.getHighClosed(), x.getHighClosed());
        assertEquals(expected.getLow(), x.getLow());
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertEquals(expected.getWidth(), x.getWidth());
    }

    @Override
    public void verifyXmlSimple(IVLINT x) {
        IVLINT expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(((INT) expected.getWidth()).getValue(), ((INT) x.getWidth()).getValue());

    }

    private Int getInt(int value) {
        Int result = new INTTransformerTest().makeDtoSimple();
        result.setValue(value);
        return result;
    }

    private INT getINT(int value) {
        INT result = new INTTransformerTest().makeXmlSimple();
        result.setValue(value);
        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
         // set Any with equal high and low
         IVLINT input = new IVLINT();
         input.setWidth(getINT(3));
         input.setHigh(getINT(4));
         input.setLow(getINT(4));
         Ivl<Int> output = IVLINTTransformer.INSTANCE.toDto(input);
         assertEquals(output.getAny(), getInt(4));
         // set Any with some high and no low
         IVLINT input2 = new IVLINT();
         input2.setWidth(getINT(3));
         input2.setHigh(getINT(5));
         Ivl<Int> output2 = IVLINTTransformer.INSTANCE.toDto(input2);
         assertEquals(output2.getAny(), getInt(5));
         // set Any with no high and some low
         IVLINT input3 = new IVLINT();
         input3.setWidth(getINT(3));
         input3.setHigh(getINT(1));
         Ivl<Int> output3 = IVLINTTransformer.INSTANCE.toDto(input3);
         assertEquals(output3.getAny(), getInt(1));
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
         // set Any with equal high and low
         Ivl<Int> input = new Ivl<Int>();
         input.setAny(getInt(3));
         IVLINT output = IVLINTTransformer.INSTANCE.toXml(input);
         assertEquals(output.getHigh().getValue(), getINT(3).getValue());
         assertEquals(output.getLow().getValue(), getINT(3).getValue());
         assertTrue(output.isHighClosed());
         assertTrue(output.isLowClosed());
         // set Any with some high and low not being equal
         Ivl<Int> input2 = new Ivl<Int>();
         input2.setWidth(getInt(3));
         input2.setHigh(getInt(5));
         input2.setLow(getInt(1));
         input2.setAny(getInt(3));
         IVLINT output2 = IVLINTTransformer.INSTANCE.toXml(input2);
         assertEquals(output2.getHigh().getValue(), getINT(5).getValue());
         assertEquals(output2.getLow().getValue(), getINT(1).getValue());
    }

}
