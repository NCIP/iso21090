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
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class IntTest {

    private static final String ED_TEXT_VALUE = "ed text";

    @Test
    public void testEquality() {

        Int firstInt = new Int();
        firstInt.setNullFlavor(NullFlavor.DER);
        firstInt.setValue(111);

        EdText text = new EdText();
        text.setValue(ED_TEXT_VALUE);
        firstInt.setOriginalText(text);
        
        Int uncertainty = new Int();
 //       uncertainty.setNullFlavor(NullFlavor.DER);
        uncertainty.setValue(111);
        firstInt.setUncertainty(uncertainty);
        
        assertTrue(firstInt.equals(firstInt));
        assertFalse(firstInt.equals(null));

        Int secondInt = new Int();
        secondInt.setNullFlavor(NullFlavor.DER);
        secondInt.setValue(111);
 
        /**
         * Note: Equality is not defined for the QTY datatype as it is an 
         * abstract type.  The QTY attributes (expression, originalText,
         * uncertainty and uncertaintyType) never participate in the
         * determination of equality (or hashcode) of specializations of QTY.
         **/  
        assertTrue(firstInt.equals(secondInt));
       }

       @Test
       public void testHashCode() {

           Int firstInt = new Int();
           firstInt.setNullFlavor(NullFlavor.DER);
           firstInt.setValue(111);
           
           EdText firstText = new EdText();
           firstInt.setOriginalText(firstText);           

           Int secondInt = new Int();
           secondInt.setNullFlavor(NullFlavor.DER);
           secondInt.setValue(111);

           EdText secondText = new EdText();
           secondInt.setOriginalText(secondText);           

           assertEquals(firstInt.hashCode(), secondInt.hashCode());

           secondInt.setValue(555);

           assertFalse(firstInt.hashCode() == secondInt.hashCode());

       }

       @Test
       public void testCloneable() {
           Int firstInt = new Int();
           firstInt.setNullFlavor(NullFlavor.DER);
           firstInt.setValue(111);
           
           Int secondInt = firstInt.clone();

           assertTrue(firstInt != secondInt);
           assertTrue(firstInt.equals(secondInt));
           assertEquals(firstInt.hashCode(), secondInt.hashCode());
       }
}
