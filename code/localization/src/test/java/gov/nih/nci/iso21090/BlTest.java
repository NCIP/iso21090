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
import gov.nih.nci.iso21090.Bl;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class BlTest {
     @Test
     public void testEquality() {
         Bl first = new Bl();
         first.setNullFlavor(NullFlavor.ASKU);
         first.setValue(true);
         assertTrue(first.equals(first));
         assertFalse(first.equals(null));

         Bl second = new Bl();
         second.setNullFlavor(NullFlavor.ASKU);
         second.setValue(true);

         assertTrue(first.equals(second));

         second.setValue(false);

         assertFalse(first.equals(second));

        }

        @Test
        public void testHashCode() {

            Bl first = new Bl();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setValue(true);

            Bl second = new Bl();
            second.setNullFlavor(NullFlavor.ASKU);
            second.setValue(true);

            assertEquals(first.hashCode(), second.hashCode());
            second.setValue(false);
            assertFalse(first.hashCode() == second.hashCode());

        }

        @Test
        public void testCloneable() {
            Bl first = new Bl();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setValue(true);

            Bl second = first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());

        }
}
