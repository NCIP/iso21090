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
import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.St;

import org.junit.Test;

public class CdTest {

	 
	
	@Test
     public void testEquality() {
         Cd first = new Cd();
         first.setNullFlavor(NullFlavor.ASKU);
         first.setCode("COD");
         first.setCodeSystem("code system");
         first.setCodeSystemName("code system name");
         first.setCodeSystemVersion("code system version");
         St name = new St();
         name.setValue("value");
         
         first.setDisplayName(name);
         EdText text = new EdText();
         text.setValue("ed text");
         first.setOriginalText(text);
         
         assertTrue(first.equals(first));
         assertFalse(first.equals(null));

         Cd second = new Cd();
         second.setNullFlavor(NullFlavor.ASKU);
         second.setCode("COD");
         second.setCodeSystem("code system");
         second.setCodeSystemName("code system name");
         second.setCodeSystemVersion("code system version");
         St name2 = new St();
         name2.setValue("value");
         second.setDisplayName(name2);
         EdText text2 = new EdText();
         text2.setValue("ed text");
         second.setOriginalText(text2);
        

         assertTrue(first.equals(second));

         second.getDisplayName().setValue("not value");

         assertFalse(first.equals(second));

        }

        @Test
        public void testHashCode() {

            Cd first = new Cd();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setCode("COD");
            first.setCodeSystem("code system");
            first.setCodeSystemName("code system name");
            first.setCodeSystemVersion("code system version");
            St name = new St();
            name.setValue("value");
            first.setDisplayName(name);
            EdText text = new EdText();
            text.setValue("ed text");
            first.setOriginalText(text);

            Cd second = new Cd();
            second.setNullFlavor(NullFlavor.ASKU);
            second.setCode("COD");
            second.setCodeSystem("code system");
            second.setCodeSystemName("code system name");
            second.setCodeSystemVersion("code system version");
            St name2 = new St();
            name2.setValue("value");
            second.setDisplayName(name2);
            EdText text2 = new EdText();
            text2.setValue("ed text");
            second.setOriginalText(text2);

            assertEquals(first.hashCode(), second.hashCode());

            second.getDisplayName().setValue("not value");

            assertFalse(first.hashCode() == second.hashCode());

        }

        @Test
        public void testCloneable() {
            Cd first = new Cd();
            first.setNullFlavor(NullFlavor.ASKU);
            first.setCode("COD");
            first.setCodeSystem("code system");
            first.setCodeSystemName("code system name");
            first.setCodeSystemVersion("code system version");
            St name = new St();
            name.setValue("value");
            first.setDisplayName(name);
            EdText text = new EdText();
            text.setValue("ed text");
            first.setOriginalText(text);

            Cd second = first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());

        }
}
