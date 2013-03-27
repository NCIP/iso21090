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

import java.util.HashSet;

import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class EnTest {

    @Test
    public void testEquality() {
        En first = new En();
        first.setNullFlavor(NullFlavor.ASKU);

        first.getPart().add(new Enxp(EntityNamePartType.DEL));
        first.getPart().add(new Enxp(EntityNamePartType.FAM));
        first.addPart(new Enxp(EntityNamePartType.GIV));
        
        first.setUses(new HashSet<EntityNameUse>());        
        first.getUses().add(EntityNameUse.A);
        first.getUses().add(EntityNameUse.ABC);        

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        En second = new En();
        second.setNullFlavor(NullFlavor.ASKU);
        second.getPart().add(new Enxp(EntityNamePartType.DEL));
        second.getPart().add(new Enxp(EntityNamePartType.FAM));
        second.getPart().add(new Enxp(EntityNamePartType.GIV));

        second.getUses().add(EntityNameUse.A);
        second.getUses().add(EntityNameUse.ABC); 

        assertTrue(first.equals(second));

        second.getPart().remove(new Enxp(EntityNamePartType.DEL));
        second.getPart().add(new Enxp(EntityNamePartType.SFX));
        
        second.getUses().remove(EntityNameUse.A);
        second.getUses().add(EntityNameUse.C);

        assertFalse(first.equals(second));

    }

    @Test
    public void testHashCode() {
        En first = new En();
        first.setNullFlavor(NullFlavor.ASKU);

        first.getPart().add(new Enxp(EntityNamePartType.DEL));
        first.getPart().add(new Enxp(EntityNamePartType.FAM));
        first.getPart().add(new Enxp(EntityNamePartType.GIV));
        
        first.getUses().add(EntityNameUse.A);
        first.getUses().add(EntityNameUse.ABC);

        En second = new En();
        second.setNullFlavor(NullFlavor.ASKU);
        second.getPart().add(new Enxp(EntityNamePartType.DEL));
        second.getPart().add(new Enxp(EntityNamePartType.FAM));
        second.getPart().add(new Enxp(EntityNamePartType.GIV));

        second.getUses().add(EntityNameUse.A);
        second.getUses().add(EntityNameUse.ABC);

        assertEquals(first.hashCode(), second.hashCode());

        second.getPart().remove(new Enxp(EntityNamePartType.DEL));
        second.getPart().add(new Enxp(EntityNamePartType.SFX));
        
        second.getUses().remove(EntityNameUse.A);
        second.getUses().add(EntityNameUse.C);

        assertFalse(first.hashCode() == second.hashCode());

    }

    @Test
    public void testCloneable() {
        En first = new En();
        first.setNullFlavor(NullFlavor.ASKU);

        first.getPart().add(new Enxp(EntityNamePartType.DEL));
        first.getPart().add(new Enxp(EntityNamePartType.FAM));
        first.getPart().add(new Enxp(EntityNamePartType.GIV));

        En second = first.clone();

        assertTrue(first != second);
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }
}
