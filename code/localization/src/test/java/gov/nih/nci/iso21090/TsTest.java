package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.Ts;

import java.util.Date;

import org.junit.Test;

public class TsTest {
    private final Date date = new Date();
    @Test
    public void testEquality() throws InterruptedException {

        Ts uncertainty1 = new Ts();
//        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setValue(date);

        Ts first = new Ts();
        first.setNullFlavor(NullFlavor.ASKU);
        first.setValue(date);

        first.setUncertainty(uncertainty1);
        first.setUncertaintyType(UncertaintyType.T);

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setValue(111);

        Ts second = new Ts();
        second.setNullFlavor(NullFlavor.ASKU);

//      The QTY attributes (expression,
//      originalText, uncertainty and uncertaintyType) never participate in the
//      determination of equality or hashcode generation of specializations of QTY.
        assertFalse(first.equals(second));

        second.setValue(date);
        assertTrue(first.equals(second));
        
        // prove that different date hashcodes -> !equal
        Date secondDate = new Date();
        int i = 0;
        while (secondDate.hashCode() == first.getValue().hashCode() && i < 100) {
        Thread.sleep(100);
        secondDate = new Date();
        ++i;
        }
        if (i == 100) {
            throw new RuntimeException("Unable to find different hash!");
        }
        second.setValue(secondDate);

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() throws InterruptedException  {

           Ts uncertainty1 = new Ts();
//           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(date);

           Ts first = new Ts();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue(date);

           first.setUncertainty(uncertainty1);
           first.setUncertaintyType(UncertaintyType.T);

//           Int uncertainty2 = new Int();
//           uncertainty2.setNullFlavor(NullFlavor.DER);
//           uncertainty2.setValue(111);

           Ts second = new Ts();
           second.setNullFlavor(NullFlavor.ASKU);
           second.setValue(date);

           assertEquals(first.hashCode(), second.hashCode());
           // prove that different date hashcodes -> different Ts hashcodes
           Date secondDate = new Date();
           int i = 0;
           while (secondDate.hashCode() == first.getValue().hashCode() && i < 100) {
               Thread.sleep(100);
               secondDate = new Date();
               ++i;
           }
           if (i == 100) {
               throw new RuntimeException("Unable to find different hash!");
           }
           second.setValue(secondDate);

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Ts uncertainty1 = new Ts();
//           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setValue(date);

           Ts first = new Ts();
           first.setNullFlavor(NullFlavor.ASKU);
           first.setValue(date);

           first.setUncertainty(uncertainty1);
           first.setUncertaintyType(UncertaintyType.T);           

           Ts second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }

}
