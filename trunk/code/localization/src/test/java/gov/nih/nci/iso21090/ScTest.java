package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.Sc;

import org.junit.Test;

public class ScTest {
    @Test
    public void testEquality() {
        Sc first = new Sc();
        Cd firstCode = new Cd();
        firstCode.setCode("code");
        firstCode.setCodeSystem("code system");
        first.setCode(firstCode);
        first.setNullFlavor(NullFlavor.ASKU);
        first.setValue("value");
        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Sc second = new Sc();
        Cd secondCode = new Cd();
        secondCode.setCode("code");
        secondCode.setCodeSystem("code system");
        second.setCode(secondCode);
        second.setNullFlavor(NullFlavor.ASKU);
        second.setValue("value");
        assertTrue(first.equals(second));

        second.setValue("not value");

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           Sc first = new Sc();
           Cd firstCode = new Cd();
           firstCode.setCode("code");
           firstCode.setCodeSystem("code system");
           first.setCode(firstCode);
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           Sc second = new Sc();
           Cd secondCode = new Cd();
           secondCode.setCode("code");
           secondCode.setCodeSystem("code system");
           second.setCode(secondCode);
           second.setNullFlavor(NullFlavor.ASKU);
           second.setValue("value");

           assertEquals(first.hashCode(), second.hashCode());
           second.setValue("not value");
           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Sc first = new Sc();
           Cd firstCode = new Cd();
           firstCode.setCode("code");
           firstCode.setCodeSystem("code system");
           first.setCode(firstCode);
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue("value");

           Sc second = (Sc) first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
