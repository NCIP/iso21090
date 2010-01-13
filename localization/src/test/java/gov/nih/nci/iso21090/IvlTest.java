package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.UncertaintyType;

import org.junit.Test;

public class IvlTest {

    @Test
    public void testEquality() {

        Ivl<Int> first = new Ivl<Int>();

        Int uncertainty1 = new Int();
        uncertainty1.setNullFlavor(NullFlavor.DER);
        uncertainty1.setOriginalText(new EdText());
        uncertainty1.setValue(111);
        uncertainty1.setUncertaintyType(UncertaintyType.F);

        first.setAny(uncertainty1);

        Int firstHigh = new Int();
        firstHigh.setNullFlavor(NullFlavor.ASKU);
        EdText firstHighText = new EdText();
        firstHighText.setDescription("value");
        firstHigh.setOriginalText(firstHighText);
        firstHigh.setUncertaintyType(UncertaintyType.B);
        firstHigh.setUncertainty(uncertainty1);
        firstHigh.setValue(111);

        first.setHigh(firstHigh);
        first.setHighClosed(true);
        first.setLow(firstHigh.clone());
        first.setLowClosed(false);
        first.setNullFlavor(NullFlavor.DER);
        first.setOriginalText(new EdText());
        first.setWidth(uncertainty1.clone());


        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Ivl<Int> second = new Ivl<Int>();

        Int uncertainty2 = new Int();
        uncertainty2.setNullFlavor(NullFlavor.DER);
        uncertainty2.setOriginalText(new EdText());
        uncertainty2.setValue(111);
        uncertainty2.setUncertaintyType(UncertaintyType.F);

        second.setAny(uncertainty2);

        Int secondHigh = new Int();
        secondHigh.setNullFlavor(NullFlavor.ASKU);
        EdText secondHighText = new EdText();
        secondHighText.setDescription("value");
        secondHigh.setOriginalText(secondHighText);
        secondHigh.setUncertaintyType(UncertaintyType.B);
        secondHigh.setUncertainty(uncertainty2);
        secondHigh.setValue(111);

        second.setHigh(secondHigh);
        second.setHighClosed(true);
        second.setLow(secondHigh.clone());
        second.setLowClosed(false);
        second.setNullFlavor(NullFlavor.DER);
        second.setOriginalText(new EdText());
        second.setWidth(uncertainty2.clone());

        assertTrue(first.equals(second));

        second.getHigh().setValue(222);

        assertFalse(first.equals(second));

       }

       @Test
       public void testHashCode() {

           Ivl<Int> first = new Ivl<Int>();

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setOriginalText(new EdText());
           uncertainty1.setValue(111);
           uncertainty1.setUncertaintyType(UncertaintyType.F);

           first.setAny(uncertainty1);

           Int firstHigh = new Int();
           firstHigh.setNullFlavor(NullFlavor.ASKU);
           EdText firstHighText = new EdText();
           firstHighText.setDescription("value");
           firstHigh.setOriginalText(firstHighText);
           firstHigh.setUncertaintyType(UncertaintyType.B);
           firstHigh.setUncertainty(uncertainty1);
           firstHigh.setValue(111);

           first.setHigh(firstHigh);
           first.setHighClosed(true);
           first.setLow(firstHigh.clone());
           first.setLowClosed(false);
           first.setNullFlavor(NullFlavor.DER);
           first.setOriginalText(new EdText());
           first.setWidth(uncertainty1.clone());

           Ivl<Int> second = new Ivl<Int>();

           Int uncertainty2 = new Int();
           uncertainty2.setNullFlavor(NullFlavor.DER);
           uncertainty2.setOriginalText(new EdText());
           uncertainty2.setValue(111);
           uncertainty2.setUncertaintyType(UncertaintyType.F);

           second.setAny(uncertainty2);

           Int secondHigh = new Int();
           secondHigh.setNullFlavor(NullFlavor.ASKU);
           EdText secondHighText = new EdText();
           secondHighText.setDescription("value");
           secondHigh.setOriginalText(secondHighText);
           secondHigh.setUncertaintyType(UncertaintyType.B);
           secondHigh.setUncertainty(uncertainty2);
           secondHigh.setValue(111);

           second.setHigh(secondHigh);
           second.setHighClosed(true);
           second.setLow(secondHigh.clone());
           second.setLowClosed(false);
           second.setNullFlavor(NullFlavor.DER);
           second.setOriginalText(new EdText());
           second.setWidth(uncertainty2.clone());

           assertEquals(first.hashCode(), second.hashCode());

           second.getHigh().setValue(555);

           assertFalse(first.hashCode() == second.hashCode());

       }

       @Test
       public void testCloneable() {
           Ivl<Int> first = new Ivl<Int>();

           Int uncertainty1 = new Int();
           uncertainty1.setNullFlavor(NullFlavor.DER);
           uncertainty1.setOriginalText(new EdText());
           uncertainty1.setValue(111);
           uncertainty1.setUncertaintyType(UncertaintyType.F);

           first.setAny(uncertainty1);

           Int firstHigh = new Int();
           firstHigh.setNullFlavor(NullFlavor.ASKU);
           EdText firstHighText = new EdText();
           firstHighText.setDescription("value");
           firstHigh.setOriginalText(firstHighText);
           firstHigh.setUncertaintyType(UncertaintyType.B);
           firstHigh.setValue(111);

           first.setHigh(firstHigh);
           first.setHighClosed(true);
           first.setLow(firstHigh.clone());
           first.setLowClosed(false);
           first.setNullFlavor(NullFlavor.DER);
           first.setOriginalText(new EdText());
           first.setWidth(uncertainty1.clone());

           Ivl<Int> second = first.clone();

           assertTrue(first != second);
           assertTrue(first.equals(second));
           assertEquals(first.hashCode(), second.hashCode());

       }
}
