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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.iso._21090.NullFlavor;
import org.iso._21090.TS;
import org.junit.Test;

/**
 *
 * @author max
 */
public class TSTransformerTest extends AbstractTransformerTestBase<TSTransformer, org.iso._21090.TS, Ts>{

    public final String VALUE_DATE = "19800928023033.0978-0000";
    public final String OVERFLOW_VALUE_DATE = "20090229000000.0000-0000";
    public org.iso._21090.UncertaintyType xmlUncertaintyType = org.iso._21090.UncertaintyType.F;
    public gov.nih.nci.iso21090.UncertaintyType dtoUncertaintyType = gov.nih.nci.iso21090.UncertaintyType.F;    

    @Override
    public TS makeXmlSimple() {
        TS x = new TS();
        x.setValue(VALUE_DATE);
        org.iso._21090.TS uncertainty = new org.iso._21090.TS();
        uncertainty.setValue(VALUE_DATE);
        x.setUncertainty(uncertainty);
        x.setUncertaintyType(xmlUncertaintyType);
        return x;
    }

    @Override
    public Ts makeDtoSimple() {
        SimpleDateFormat sdf = new SimpleDateFormat(TSTransformer.FORMAT_STRING);
        //sdf.setLenient(false);
        Ts x = new Ts();
        try {
            x.setValue(sdf.parse(VALUE_DATE));
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        Ts uncertainty = new Ts();
        try {
        	uncertainty.setValue(sdf.parse(VALUE_DATE));
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }        
        x.setUncertainty(uncertainty);
        x.setUncertaintyType(dtoUncertaintyType);        
        return x;
    }

    @Override
    public void verifyXmlSimple(TS x) {
        SimpleDateFormat sdf = new SimpleDateFormat(TSTransformer.FORMAT_STRING);
        //sdf.setLenient(false);
        try {
            Date number1 = sdf.parse(VALUE_DATE);
            Date number2 = sdf.parse(x.getValue());
            assertEquals(number1.getTime(), number2.getTime());
            
            number1 = sdf.parse(VALUE_DATE);
            assertEquals(number1.getTime(), number2.getTime());

            TS uncertainty = (TS)(x.getUncertainty());
            number1 = sdf.parse(VALUE_DATE);
            number2 = sdf.parse(uncertainty.getValue());
            assertEquals(number1.getTime(), number2.getTime());
            
            number1 = sdf.parse(VALUE_DATE);
            assertEquals(number1.getTime(), number2.getTime());
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        assertEquals(x.getUncertaintyType(), xmlUncertaintyType);
    }

    @Override
    public void verifyDtoSimple(Ts x) {
        SimpleDateFormat sdf = new SimpleDateFormat(TSTransformer.FORMAT_STRING);
        //sdf.setLenient(false);
        try {
            assertEquals(sdf.parse(VALUE_DATE).getTime(), x.getValue().getTime());
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        assertEquals(x.getUncertaintyType(), dtoUncertaintyType);
    }

    public TS makeXmlNullFlavored() {
        TS x = new TS();
        x.setNullFlavor(NullFlavor.NI);
        return x;
    }

    public void verifyDtoNullFlavored(Ts dto) {
        assertNull(dto.getValue());
        assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
    }

    @Test
    public void testTsNull() throws Exception {
        Ts ts = new Ts();
        ts.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
        TS result = TSTransformer.INSTANCE.toXml(ts);
        assertNotNull(result);
        assertEquals(NullFlavor.ASKU, result.getNullFlavor());
        assertNull(result.getValue());
    }

    @Test(expected = ParseException.class)
    public void testOverflow() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(TSTransformer.FORMAT_STRING);
        sdf.setLenient(false);
        Ts ts = new Ts();
        ts.setValue(sdf.parse(OVERFLOW_VALUE_DATE));

        TS result = TSTransformer.INSTANCE.toXml(ts);
        assertNotNull(result);
    }
}
