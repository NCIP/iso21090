package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.Ad;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.AdxpAdl;
import gov.nih.nci.iso21090.AdxpAl;
import gov.nih.nci.iso21090.AdxpBnn;
import gov.nih.nci.iso21090.AdxpCen;
import gov.nih.nci.iso21090.AdxpDal;
import gov.nih.nci.iso21090.AdxpDinst;
import gov.nih.nci.iso21090.NullFlavor;

import java.util.ArrayList;

import org.junit.Test;

public class AdTest {
    @Test
    public void testEquality() {
        Ad first = new Ad();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setPart(new ArrayList<Adxp>());
        first.getPart().add(new AdxpAdl());
        first.getPart().add(new AdxpAl());
        first.addPart(new AdxpBnn());

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Ad second = new Ad();
        second.setNullFlavor(NullFlavor.DER);
        second.setPart(new ArrayList<Adxp>());
        second.getPart().add(new AdxpAdl());
        second.getPart().add(new AdxpAl());
        second.getPart().add(new AdxpBnn());

        assertFalse(first.equals(second));

        second.setNullFlavor(NullFlavor.ASKU);

        assertTrue(first.equals(second));

        second.getPart().remove(new AdxpAdl());
        second.getPart().add(new AdxpCen());

        assertFalse(first.equals(second));

    }

    @Test
    public void testHashCode() {
        Ad first = new Ad();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setPart(new ArrayList<Adxp>());
        first.getPart().add(new AdxpAdl());
        first.getPart().add(new AdxpAl());
        first.getPart().add(new AdxpBnn());

        Ad second = new Ad();
        second.setNullFlavor(NullFlavor.ASKU);
        second.setPart(new ArrayList<Adxp>());
        second.getPart().add(new AdxpAdl());
        second.getPart().add(new AdxpAl());
        second.getPart().add(new AdxpBnn());


        assertEquals(first.hashCode(), second.hashCode());

        second.getPart().remove(new AdxpAdl());
        second.getPart().add(new AdxpCen());
        second.getPart().add(new AdxpDal());
        second.getPart().add(new AdxpDinst());

        assertFalse(first.hashCode() == second.hashCode());

    }

    @Test
    public void testCloneable() {

        Ad first = new Ad();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setPart(new ArrayList<Adxp>());
        first.getPart().add(new AdxpAdl());
        first.getPart().add(new AdxpAl());
        first.getPart().add(new AdxpBnn());


        Ad second = first.clone();

        assertTrue(first != second);
        assertTrue(first.equals(second));
        assertEquals(first.hashCode(), second.hashCode());

        Ad third = new Ad();
        third.setNullFlavor(NullFlavor.MSK);

        Ad fourth = third.clone();

        assertTrue(third != fourth);
        assertTrue(third.equals(fourth));
        assertEquals(third.hashCode(), fourth.hashCode());

    }
}
