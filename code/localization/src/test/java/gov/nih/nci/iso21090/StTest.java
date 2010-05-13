package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.St;

import org.junit.Test;

public class StTest {
    @Test
    public void testEquality() {
        St first = new St();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setValue("value");
        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        St second = new St();
        second.setNullFlavor(NullFlavor.ASKU);
        second.setValue("value");

        assertTrue(first.equals(second));

        second.setValue("not value");

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           St first = new St();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           St second = new St();
           second.setNullFlavor(NullFlavor.ASKU);
           second.setValue("value");

           assertEquals(first.hashCode(), second.hashCode());
           second.setValue("not value");
           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           St first = new St();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           St second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
