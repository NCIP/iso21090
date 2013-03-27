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

import gov.nih.nci.iso21090.EntityNamePartQualifier;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class EnxpTest {
    @Test
    public void testCreateEntityPart() {
        for (EntityNamePartType type: EntityNamePartType.values()){
            Enxp result = new Enxp(type);
            assertEquals(type, result.getType());
        }


        Enxp result = new Enxp(null);
        assertEquals(null, result.getType());
    }

    @Test
    public void testEquality() {
        for (EntityNamePartType type: EntityNamePartType.values()){
            Enxp first = new Enxp(type);
            first.setCode("COD");
            first.setCodeSystem("code system");
            first.setCodeSystemVersion("version");
            Set<EntityNamePartQualifier> qualifier =
                new HashSet<EntityNamePartQualifier>();
            qualifier.add(EntityNamePartQualifier.AC);
            qualifier.add(EntityNamePartQualifier.AD);
            qualifier.add(EntityNamePartQualifier.BR);
            first.setQualifier(qualifier);
            first.setValue("value");
            assertTrue(first.equals(first));
            assertFalse(first.equals(null));

            Enxp second = new Enxp(type);
            second.setCode("COD");
            second.setCodeSystem("code system");
            second.setCodeSystemVersion("version");
            Set<EntityNamePartQualifier> qualifier2 =
                new HashSet<EntityNamePartQualifier>();
            qualifier2.add(EntityNamePartQualifier.AC);
            qualifier2.add(EntityNamePartQualifier.AD);
            qualifier2.add(EntityNamePartQualifier.BR);
            second.setQualifier(qualifier2);
            second.setValue("value");

            assertTrue(first.equals(second));

            second.getQualifier().remove(EntityNamePartQualifier.AC);
            second.getQualifier().remove(EntityNamePartQualifier.LS);

            assertFalse(first.equals(second));
        }
    }

    @Test
    public void testHashCode() {
        for (EntityNamePartType type: EntityNamePartType.values()){
            Enxp first = new Enxp(type);
            first.setCode("COD");
            first.setCodeSystem("code system");
            first.setCodeSystemVersion("version");
            Set<EntityNamePartQualifier> qualifier =
                new HashSet<EntityNamePartQualifier>();
            qualifier.add(EntityNamePartQualifier.AC);
            qualifier.add(EntityNamePartQualifier.AD);
            qualifier.add(EntityNamePartQualifier.BR);
            first.setQualifier(qualifier);
            first.setValue("value");

            Enxp second = new Enxp(type);
            second.setCode("COD");
            second.setCodeSystem("code system");
            second.setCodeSystemVersion("version");
            Set<EntityNamePartQualifier> qualifier2 =
                new HashSet<EntityNamePartQualifier>();
            qualifier2.add(EntityNamePartQualifier.AC);
            qualifier2.add(EntityNamePartQualifier.AD);
            qualifier2.add(EntityNamePartQualifier.BR);
            second.setQualifier(qualifier2);
            second.setValue("value");

            assertEquals(first.hashCode(), second.hashCode());

            second.getQualifier().remove(EntityNamePartQualifier.AC);
            second.getQualifier().remove(EntityNamePartQualifier.LS);

            assertFalse(first.hashCode() == second.hashCode());
        }
    }

    @Test
    public void testCloneable() {
        for (EntityNamePartType type: EntityNamePartType.values()){
            Enxp first = new Enxp(type);
            first.setCode("COD");
            first.setCodeSystem("code system");
            first.setCodeSystemVersion("version");
            Set<EntityNamePartQualifier> qualifier =
                new HashSet<EntityNamePartQualifier>();
            qualifier.add(EntityNamePartQualifier.AC);
            qualifier.add(EntityNamePartQualifier.AD);
            qualifier.add(EntityNamePartQualifier.BR);
            first.setQualifier(qualifier);
            first.setValue("value");

            Enxp second = first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());
        }
    }
}
