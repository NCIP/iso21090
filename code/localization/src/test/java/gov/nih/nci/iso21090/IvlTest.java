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
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class IvlTest {

    @Test
    public void testEquality() {

        Ivl<Int> first = new Ivl<Int>();

        Int uncertainty1 = new Int();
        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setValue(111);

        first.setAny(uncertainty1);

        Int firstHigh = new Int();
        firstHigh.setNullFlavor(NullFlavor.ASKU);
        firstHigh.setValue(111);

        first.setHigh(firstHigh);
        first.setHighClosed(true);
        first.setLow(firstHigh.clone());
        first.setLowClosed(false);
        first.setNullFlavor(NullFlavor.DER);
        first.setWidth(uncertainty1.clone());


        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Ivl<Int> second = new Ivl<Int>();

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setValue(111);

        second.setAny(uncertainty2);

        Int secondHigh = new Int();
        secondHigh.setNullFlavor(NullFlavor.ASKU);
        secondHigh.setValue(111);

        second.setHigh(secondHigh);
        second.setHighClosed(true);
        second.setLow(secondHigh.clone());
        second.setLowClosed(false);
        second.setNullFlavor(NullFlavor.DER);
        second.setWidth(uncertainty2.clone());

        assertTrue(first.equals(second));

        second.getHigh().setValue(222);

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           Ivl<Int> first = new Ivl<Int>();

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(111);

           first.setAny(uncertainty1);

           Int firstHigh = new Int();
           firstHigh.setNullFlavor(NullFlavor.ASKU);
           firstHigh.setValue(111);

           first.setHigh(firstHigh);
           first.setHighClosed(true);
           first.setLow(firstHigh.clone());
           first.setLowClosed(false);
           first.setNullFlavor(NullFlavor.DER);
           first.setWidth(uncertainty1.clone());

           Ivl<Int> second = new Ivl<Int>();

           Int uncertainty2 = new Int();
           uncertainty2.setNullFlavor(NullFlavor.DER);
           uncertainty2.setValue(111);

           second.setAny(uncertainty2);

           Int secondHigh = new Int();
           secondHigh.setNullFlavor(NullFlavor.ASKU);
           secondHigh.setValue(111);

           second.setHigh(secondHigh);
           second.setHighClosed(true);
           second.setLow(secondHigh.clone());
           second.setLowClosed(false);
           second.setNullFlavor(NullFlavor.DER);
           second.setWidth(uncertainty2.clone());

           assertEquals(first.hashCode(), second.hashCode());

           second.getHigh().setValue(555);

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Ivl<Int> first = new Ivl<Int>();

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(111);

           first.setAny(uncertainty1);

           Int firstHigh = new Int();
           firstHigh.setNullFlavor(NullFlavor.ASKU);
           firstHigh.setValue(111);

           first.setHigh(firstHigh);
           first.setHighClosed(true);
           first.setLow(firstHigh.clone());
           first.setLowClosed(false);
           first.setNullFlavor(NullFlavor.DER);
           first.setWidth(uncertainty1.clone());

           Ivl<Int> second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
