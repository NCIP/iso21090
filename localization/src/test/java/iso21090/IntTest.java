package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.UncertaintyType;

import org.junit.Test;

public class IntTest {
    @Test
    public void testEquality() {

        Int uncertainty1 = new Int();
        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setOriginalText(new EdText());
        uncertainty1.setValue(111);
        uncertainty1.setUncertaintyType(UncertaintyType.F);

        Int first = new Int();
        first.setNullFlavor(NullFlavor.ASKU);
        EdText firstText = new EdText();
        firstText.setDescription("value");
        first.setOriginalText(firstText);
        first.setUncertaintyType(UncertaintyType.B);
        first.setUncertainty(uncertainty1);


        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setOriginalText(new EdText());
        uncertainty2.setValue(111);
        uncertainty2.setUncertaintyType(UncertaintyType.F);

        Int second = new Int();
        second.setNullFlavor(NullFlavor.ASKU);
        EdText secondText = new EdText();
        secondText.setDescription("value");
        second.setOriginalText(secondText);
        second.setUncertaintyType(UncertaintyType.B);
        second.setUncertainty(uncertainty2);

        assertTrue(first.equals(second));

        second.getUncertainty().setUncertaintyType(UncertaintyType.LN);

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setOriginalText(new EdText());
           uncertainty1.setValue(111);
           uncertainty1.setUncertaintyType(UncertaintyType.F);

           Int first = new Int();
           first.setNullFlavor(NullFlavor.ASKU);
           EdText firstText = new EdText();
           firstText.setDescription("value");
           first.setOriginalText(firstText);
           first.setUncertaintyType(UncertaintyType.B);
           first.setUncertainty(uncertainty1);

           Int uncertainty2 = new Int();
           uncertainty2.setNullFlavor(NullFlavor.DER);
           uncertainty2.setOriginalText(new EdText());
           uncertainty2.setValue(111);
           uncertainty2.setUncertaintyType(UncertaintyType.F);

           Int second = new Int();
           second.setNullFlavor(NullFlavor.ASKU);
           EdText secondText = new EdText();
           secondText.setDescription("value");
           second.setOriginalText(secondText);
           second.setUncertaintyType(UncertaintyType.B);
           second.setUncertainty(uncertainty2);

           assertEquals(first.hashCode(), second.hashCode());

           second.setValue(555);

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setOriginalText(new EdText());
           uncertainty1.setValue(111);
           uncertainty1.setUncertaintyType(UncertaintyType.F);

           Int first = new Int();
           first.setNullFlavor(NullFlavor.ASKU);
           EdText firstText = new EdText();
           firstText.setDescription("value");
           first.setOriginalText(firstText);
           first.setUncertaintyType(UncertaintyType.B);
           first.setUncertainty(uncertainty1);

           Int second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
