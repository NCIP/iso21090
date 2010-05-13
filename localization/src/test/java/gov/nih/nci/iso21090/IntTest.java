package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.NullFlavor;

import org.junit.Test;

public class IntTest {
    @Test
    public void testEquality() {

        Int uncertainty1 = new Int();
        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setValue(111);

        Int first = new Int();
        first.setNullFlavor(NullFlavor.ASKU);
        
        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setValue(111);

        Int second = new Int();
        second.setNullFlavor(NullFlavor.ASKU);
        
        assertTrue(first.equals(second));
       }

       @Test
       public void testHashCode() {

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(111);

           Int first = new Int();
           first.setNullFlavor(NullFlavor.ASKU);
           EdText firstText = new EdText();

           St st = new St();
           st.setValue("value");
           firstText.setDescription(st);
           
           Int uncertainty2 = new Int();
           uncertainty2.setNullFlavor(NullFlavor.DER);
           uncertainty2.setValue(111);

           Int second = new Int();
           second.setNullFlavor(NullFlavor.ASKU);
           EdText secondText = new EdText();

           St st2 = new St();
           st2.setValue("value");
           secondText.setDescription(st2);

           assertEquals(first.hashCode(), second.hashCode());

           second.setValue(555);

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(111);

           Int first = new Int();
           first.setNullFlavor(NullFlavor.ASKU);
           EdText firstText = new EdText();
           
           St st = new St();
           st.setValue("value");
           firstText.setDescription(st);

           Int second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
