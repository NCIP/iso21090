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

package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BlNonNullTest {
     @Test
     public void testEquality() {
         BlNonNull first = new BlNonNull();
         
         first.setValue(true);
         assertTrue(first.equals(first));
         assertFalse(first.equals(null));

         BlNonNull second = new BlNonNull();
         
         second.setValue(true);

         assertTrue(first.equals(second));

         second.setValue(false);

         assertFalse(first.equals(second));

        }

        @Test
        public void testHashCode() {

            BlNonNull first = new BlNonNull();
            
            first.setValue(true);

            BlNonNull second = new BlNonNull();
            
            second.setValue(true);

            assertEquals(first.hashCode(), second.hashCode());
            second.setValue(false);
            assertFalse(first.hashCode() == second.hashCode());

        }

        @Test
        public void testCloneable() {
            BlNonNull first = new BlNonNull();
            
            first.setValue(true);

            BlNonNull second = (BlNonNull) first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());

        }
}
