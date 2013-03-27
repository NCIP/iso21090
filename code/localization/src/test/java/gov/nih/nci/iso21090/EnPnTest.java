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
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.EnPn;
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
 * @author lpower
 */
public class EnPnTest {

    public EnPnTest() {
    }

    @Test
    public void testRestrictions() {
        EnPn enpn = new EnPn();
        Enxp e = new Enxp(EntityNamePartType.DEL);
        enpn.getPart().add(e);

        e = new Enxp(EntityNamePartType.FAM);
        enpn.getPart().add(e);

        e = new Enxp(EntityNamePartType.GIV);
        enpn.getPart().add(e);

        e = new Enxp(EntityNamePartType.PFX);
        enpn.getPart().add(e);

        e = new Enxp(EntityNamePartType.SFX);
        enpn.getPart().add(e);

        e = new Enxp(null);
        enpn.getPart().add(e);
        assertEquals(6, enpn.getPart().size());

    }
    
    @Test
    public void testCloneable() {
        EnPn first = new EnPn();
        first.setNullFlavor(NullFlavor.ASKU);

        first.getPart().add(new Enxp(EntityNamePartType.DEL));
        first.getPart().add(new Enxp(EntityNamePartType.FAM));
        first.getPart().add(new Enxp(EntityNamePartType.GIV));
        first.getPart().add(new Enxp(null));
        
        EnPn second = first.clone();

        assertTrue(first != second);
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());
    }
    
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        EnPn test = new EnPn();
        List<Enxp> part2 = test.getPart();
        Enxp enxp1 = new Enxp(EntityNamePartType.FAM);
        enxp1.setValue("test");
        part2.add(enxp1);

        Enxp enxp2 = new Enxp(EntityNamePartType.GIV);
        enxp2.setValue("test2");
        part2.add(enxp2);
        
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(test);
        oos.flush();
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        EnPn test2 = (EnPn) ois.readObject();

        assertEquals(test, test2);
    }
}