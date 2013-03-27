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

package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

public class RealTest {
    private static Double VALUE_DOUBLE = new Double(111.0);
    private static Double VALUE2_DOUBLE = new Double(555.0);    
    private static final Integer PRECISION = new Integer(1);
    private static final Integer PRECISION2 = new Integer(2);    
    @Test
    public void testEquality() {

        Real uncertainty1 = new Real();
        uncertainty1.setValue(VALUE_DOUBLE);
        uncertainty1.setPrecision(PRECISION);

        Real first = new Real();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setValue(VALUE2_DOUBLE);
        first.setPrecision(PRECISION2);        

        first.setUncertainty(uncertainty1);
        first.setUncertaintyType(UncertaintyType.U);

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

//        Real uncertainty2 = new Real();
//        uncertainty2.setValue(VALUE_DOUBLE);
//        uncertainty2.setPrecision(PRECISION);     

        Real second = new Real();
        second.setNullFlavor(NullFlavor.ASKU);
        
//      The QTY attributes (expression,
//      originalText, uncertainty and uncertaintyType) never participate in the
//      determination of equality or hashcode generation of specializations of QTY.
        assertFalse(first.equals(second));

//      second.setUncertainty(uncertainty2);

        second.setValue(VALUE2_DOUBLE);
        second.setPrecision(PRECISION2);  
        assertTrue(first.equals(second));
       }

       @Test
       public void testHashCode() {

           Real uncertainty1 = new Real();
//           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(VALUE_DOUBLE);
           uncertainty1.setPrecision(PRECISION);           

           Real first = new Real();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue(VALUE_DOUBLE);
           first.setPrecision(PRECISION);            
           
           first.setUncertainty(uncertainty1);
//         The NULL value (unknown) for the type code indicates that the probability 
//         distribution type (uncertainty type) is unknown.  In that case, uncertainty 
//         has the meaning of an informal guess if it is populated.
           first.setUncertaintyType(null);

//           Real uncertainty2 = new Real();
//           uncertainty2.setNullFlavor(NullFlavor.DER);
//           uncertainty2.setValue(VALUE_DOUBLE);

           Real second = new Real();
           second.setNullFlavor(NullFlavor.ASKU);

           assertFalse(first.hashCode() == second.hashCode());

           second.setValue(VALUE2_DOUBLE);
           second.setPrecision(PRECISION2);           

//         The QTY attributes (expression, originalText, uncertainty and uncertaintyType) 
//         never participate in the determination of equality or hashcode generation of 
//         specializations of QTY.
           assertFalse(first.hashCode() == second.hashCode());

           second.setValue(VALUE_DOUBLE);
           second.setPrecision(PRECISION);            
           assertTrue(first.hashCode() == second.hashCode());
       }

       @Test
       public void testCloneable() {
           Real uncertainty1 = new Real();
           uncertainty1.setValue(new Double(VALUE_DOUBLE));
           uncertainty1.setPrecision(PRECISION);          

           Real first = new Real();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setUncertainty(uncertainty1);
           first.setValue(VALUE2_DOUBLE);
           first.setPrecision(PRECISION2);            

           Real second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
