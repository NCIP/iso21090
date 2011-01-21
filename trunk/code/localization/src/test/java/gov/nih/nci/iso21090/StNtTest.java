package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class StNtTest {
    @Test
    public void testEquality() {
        StNt first = new StNt();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setValue("value");
        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        StNt second = new StNt();
        second.setNullFlavor(NullFlavor.ASKU);
        second.setValue("value");

        assertTrue(first.equals(second));

        second.setValue("not value");

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           StNt first = new StNt();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           StNt second = new StNt();
           second.setNullFlavor(NullFlavor.ASKU);
           second.setValue("value");

           assertEquals(first.hashCode(), second.hashCode());
           second.setValue("not value");
           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           StNt first = new StNt();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           StNt second = (StNt) first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
