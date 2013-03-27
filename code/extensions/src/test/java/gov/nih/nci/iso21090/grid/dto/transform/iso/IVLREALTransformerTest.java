//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.iso._21090.IVLREAL;
import org.iso._21090.IVLTS;
import org.iso._21090.TS;
import org.junit.Test;

/**
 * Tests for timestamp interval transformer.
 */
public class IVLREALTransformerTest extends AbstractTransformerTestBase<IVLREALTransformer, IVLREAL, Ivl<Real>> {

    @Override
    public Ivl<Real> makeDtoSimple() {
        Ivl<Real> result = new Ivl<Real>();
        result.setAny(getReal(1));
        result.setHigh(getReal(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getReal(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getReal(3));

        return result;
    }

    @Override
    public IVLREAL makeXmlSimple() {
        IVLREAL result = new IVLREAL();
        result.setHigh(getREAL(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getREAL(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getREAL(3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Real> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Real> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
//        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
//        assertEquals(expected.getHigh().getPrecision(), x.getHigh().getPrecision());
        assertTrue(expected.getHigh().equals(x.getHigh()));  
        assertEquals(expected.getHighClosed(), x.getHighClosed());
//        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
//        assertEquals(expected.getLow().getPrecision(), x.getLow().getPrecision());   
        assertTrue(expected.getLow().equals(x.getLow()));         
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertTrue(expected.getWidth().equals(x.getWidth()));
    }

    @Override
    public void verifyXmlSimple(IVLREAL x) {
        IVLREAL expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(((org.iso._21090.Real) expected.getWidth()).getValue(), ((org.iso._21090.Real) x.getWidth()).getValue());

    }

    private Real getReal(int value) {
        Real result = new REALTransformerTest().makeDtoSimple();
        result.setValue(new Double(value));

        return result;
    }

    private org.iso._21090.Real getREAL(int value) {
        org.iso._21090.Real result = new REALTransformerTest().makeXmlSimple();
        result.setValue(new Double(value));

        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
         // set Any with equal high and low
         IVLREAL input = new IVLREAL();
         input.setWidth(getREAL(3));
         input.setHigh(getREAL(4));
         input.setLow(getREAL(4));
         Ivl<Real> output = IVLREALTransformer.INSTANCE.toDto(input);
         assertTrue(output.getAny().equals(getReal(4)));
//         assertEquals(output.getAny(), getReal(4));         
         // set Any with some high and no low
         IVLREAL input2 = new IVLREAL();
         input2.setWidth(getREAL(3));
         input2.setHigh(getREAL(5));
         Ivl<Real> output2 = IVLREALTransformer.INSTANCE.toDto(input2);
         assertTrue(output2.getAny().equals(getReal(5)));        
//         assertEquals(output2.getAny(), getReal(5));
         // set Any with no high and some low
         IVLREAL input3 = new IVLREAL();
         input3.setWidth(getREAL(3));
         input3.setHigh(getREAL(1));
         Ivl<Real> output3 = IVLREALTransformer.INSTANCE.toDto(input3);
         assertTrue(output3.getAny().equals(getReal(1)));
//         assertEquals(output3.getAny(), getReal(1));         
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
         // set Any with equal high and low
         Ivl<Real> input = new Ivl<Real>();
         input.setAny(getReal(3));
         IVLREAL output = IVLREALTransformer.INSTANCE.toXml(input);
         assertEquals(output.getHigh().getValue(), getREAL(3).getValue());
         assertEquals(output.getLow().getValue(), getREAL(3).getValue());
         assertTrue(output.isHighClosed());
         assertTrue(output.isLowClosed());
         // set Any with some high and low not being equal
         Ivl<Real> input2 = new Ivl<Real>();
         input2.setWidth(getReal(3));
         input2.setHigh(getReal(5));
         input2.setLow(getReal(1));
         input2.setAny(getReal(3));
         IVLREAL output2 = IVLREALTransformer.INSTANCE.toXml(input2);
         assertEquals(output2.getHigh().getValue(), getREAL(5).getValue());
         assertEquals(output2.getLow().getValue(), getREAL(1).getValue());
    }

}
