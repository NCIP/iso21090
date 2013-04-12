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
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.NullFlavor;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.junit.Test;

/**
 *
 * @author gax
 */
public class EnOnTest {

    public EnOnTest() {
    }

    @Test
    public void testRestrictions() {
        EnOn enon = new EnOn();
        Enxp e = new Enxp(EntityNamePartType.FAM);
        try {
            enon.getPart().add(e);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        e = new Enxp(EntityNamePartType.GIV);
        try {
            enon.getPart().add(e);
            fail();
        } catch (IllegalArgumentException ex) {
        }

        e = new Enxp(EntityNamePartType.DEL);
        enon.getPart().add(e);

        e = new Enxp(null);
        enon.getPart().add(e);

        assertEquals(2, enon.getPart().size());

    }
    
    @Test
    public void testCloneable() {
        EnOn first = new EnOn();
        first.setNullFlavor(NullFlavor.ASKU);

        first.getPart().add(new Enxp(EntityNamePartType.DEL));
        first.getPart().add(new Enxp(null));
        
        EnOn second = first.clone();

        assertTrue(first != second);
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }
    
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        EnOn test = new EnOn();
        List<Enxp> part2 = test.getPart();
        Enxp enxp = new Enxp(EntityNamePartType.DEL);
        enxp.setValue("test");
        part2.add(enxp);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.flush();
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EnOn test2 = (EnOn) ois.readObject();

        assertEquals(test, test2);
    }
}