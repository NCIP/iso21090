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
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class IiTest {
     @Test
     public void testEquality() {
         Ii first = new Ii();
         first.setNullFlavor(NullFlavor.ASKU);
         first.setDisplayable(true);
         first.setExtension("100");
         first.setIdentifierName("name");
         first.setReliability(IdentifierReliability.ISS);
         first.setRoot("1.2.3");
         first.setScope(IdentifierScope.BUSN);

         assertTrue(first.equals(first));
         assertFalse(first.equals(null));

         Ii second = new Ii();
         second.setNullFlavor(NullFlavor.ASKU);
         second.setDisplayable(true);
         second.setExtension("100");
         second.setIdentifierName("name");
         second.setReliability(IdentifierReliability.ISS);
         second.setRoot("1.2.3");
         second.setScope(IdentifierScope.BUSN);

         assertTrue(first.equals(second));

         second.setExtension("200");
         assertFalse(first.equals(second));
         second.setIdentifierName("not name");
         assertFalse(first.equals(second));
         
        }

        @Test
        public void testHashCode() {

            Ii first = new Ii();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setDisplayable(true);
            first.setExtension("100");
            first.setIdentifierName("name");
            first.setReliability(IdentifierReliability.ISS);
            first.setRoot("1.2.3");
            first.setScope(IdentifierScope.BUSN);

            Ii second = new Ii();
            second.setNullFlavor(NullFlavor.ASKU);
            second.setDisplayable(true);
            second.setExtension("100");
            second.setIdentifierName("name");
            second.setReliability(IdentifierReliability.ISS);
            second.setRoot("1.2.3");
            second.setScope(IdentifierScope.BUSN);

            assertEquals(first.hashCode(), second.hashCode());

            second.setExtension("200");
            second.setIdentifierName("not name");

            assertFalse(first.hashCode() == second.hashCode());

        }

        @Test
        public void testCloneable() {
            Ii first = new Ii();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setDisplayable(true);
            first.setExtension("100");
            first.setIdentifierName("name");
            first.setReliability(IdentifierReliability.ISS);
            first.setRoot("1.2.3");
            first.setScope(IdentifierScope.BUSN);

            Ii second = first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());

        }
}
