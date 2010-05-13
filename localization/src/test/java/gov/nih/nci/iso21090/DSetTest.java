package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.Bl;
import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.NullFlavor;

import java.util.HashSet;

import org.junit.Test;

public class DSetTest {

    @Test
    public void testEquality() {
        DSet<Bl> firstDSet = new DSet<Bl>();
        firstDSet.setItem(new HashSet<Bl>());
        for (int i = 0; i < 5; i++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(i % 2 == 0);
            firstDSet.getItem().add(temp);
        }

        assertTrue(firstDSet.equals(firstDSet));
        assertFalse(firstDSet.equals(null));

        DSet<Bl> secondDSet = new DSet<Bl>();
        secondDSet.setItem(new HashSet<Bl>());
        for (int i = 0; i < 5; i++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(i % 2 == 0);
            secondDSet.getItem().add(temp);
        }

        assertTrue(firstDSet.equals(secondDSet));

        secondDSet.getItem().clear();
        for (int z = 0; z < 2; z++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(false);
            secondDSet.getItem().add(temp);
        }

        assertFalse(firstDSet.equals(secondDSet));

    }

    @Test
    public void testHashCode() {
        DSet<Bl> firstDSet = new DSet<Bl>();
        firstDSet.setItem(new HashSet<Bl>());
        for (int i = 0; i < 5; i++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(i % 2 == 0);
            firstDSet.getItem().add(temp);
        }

        DSet<Bl> secondDSet = new DSet<Bl>();
        secondDSet.setItem(new HashSet<Bl>());
        for (int i = 0; i < 5; i++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(i % 2 == 0);
            secondDSet.getItem().add(temp);
        }

        assertEquals(firstDSet.hashCode(), secondDSet.hashCode());

        secondDSet.getItem().clear();
        for (int z = 0; z < 2; z++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(false);
            secondDSet.getItem().add(temp);
        }

        assertFalse(firstDSet.hashCode() == secondDSet.hashCode());

    }

    @Test
    public void testCloneable() {
        DSet<Bl> firstDSet = new DSet<Bl>();
        firstDSet.setItem(new HashSet<Bl>());
        for (int i = 0; i < 5; i++) {
            Bl temp = new Bl();
            temp.setNullFlavor(NullFlavor.ASKU);
            temp.setValue(i % 2 == 0);
            firstDSet.getItem().add(temp);
        }

        DSet<Bl> secondDSet = firstDSet.clone();

        assertTrue(firstDSet != secondDSet);
        assertTrue(firstDSet.equals(secondDSet));
        assertEquals(firstDSet.hashCode(), secondDSet.hashCode());
    }
}
