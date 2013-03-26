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

import org.junit.Test;

public class RealTest {
    @Test
    public void testEquality() {

        Int uncertainty1 = new Int();
        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setValue(111);

        Real first = new Real();
        first.setNullFlavor(NullFlavor.ASKU);

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setValue(111);

        Real second = new Real();
        second.setNullFlavor(NullFlavor.ASKU);
        
        assertTrue(first.equals(second));
       }

       @Test
       public void testHashCode() {

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(111);

           Real first = new Real();
           first.setNullFlavor(NullFlavor.ASKU);

           Int uncertainty2 = new Int();
           uncertainty2.setNullFlavor(NullFlavor.DER);
           uncertainty2.setValue(111);

           Real second = new Real();
           second.setNullFlavor(NullFlavor.ASKU);

           assertEquals(first.hashCode(), second.hashCode());

           second.setValue(new Double(555.0));

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Real uncertainty1 = new Real();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(new Double(111.0));

           Real first = new Real();
           first.setNullFlavor(NullFlavor.ASKU);

           Real second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
