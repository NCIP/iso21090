package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;

import org.apache.commons.lang.math.NumberUtils;
import org.junit.Test;

public class AdTest {
	
	private static final String FORMAT_STRING = "yyyyMMddHHmmss.SSSSZ";	
    private static final String VALUE_DATE = "19800928023033.0978-0000";
    private static final String CODE = "code";
    private static final String CODE_SYSTEM = "code system";
    private static final String VALUE = "value";      
    private static Double VALUE_DOUBLE = new Double(12345);
    private static final String UNIT = "test unit";
    private static final Integer PRECISION = NumberUtils.INTEGER_ONE;
    
    @Test
    public void testEquality() {
        Ad first = new Ad();
        
        first.setNullFlavor(NullFlavor.ASKU);
        first.setPart(new ArrayList<Adxp>());
        first.getPart().add(new AdxpAdl());
        first.getPart().add(new AdxpAl());
        first.addPart(new AdxpBnn());
        
        first.setUses(new HashSet<PostalAddressUse>());
        first.getUses().add(PostalAddressUse.H);
        first.getUses().add(PostalAddressUse.HP);
        
        first.setUseablePeriod(makeDtoSimpleIvlTs());

        assertTrue(first.equals(first));
        assertFalse(first.equals(null));

        Ad second = new Ad();
        second.setNullFlavor(NullFlavor.DER);
        second.setPart(new ArrayList<Adxp>());
        second.getPart().add(new AdxpAdl());
        second.getPart().add(new AdxpAl());
        second.getPart().add(new AdxpBnn());
        second.setUses(new HashSet<PostalAddressUse>());
        second.getUses().add(PostalAddressUse.H);
        second.getUses().add(PostalAddressUse.HP);
        
        second.setUseablePeriod(makeDtoSimpleIvlTs());

        assertFalse(first.equals(second));

        second.setNullFlavor(NullFlavor.ASKU);

        assertTrue(first.equals(second));

        second.getPart().remove(new AdxpAdl());
        second.getPart().add(new AdxpCen());
        second.getUses().remove(PostalAddressUse.H);
        second.getUses().add(PostalAddressUse.PST);
        second.setUseablePeriod(new Ivl<Ts>());

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

        first.setUses(new HashSet<PostalAddressUse>());
        first.getUses().add(PostalAddressUse.H);
        first.getUses().add(PostalAddressUse.HP);
        first.setUseablePeriod(makeDtoSimpleIvlTs());

        Ad second = new Ad();
        second.setNullFlavor(NullFlavor.ASKU);
        second.setPart(new ArrayList<Adxp>());
        second.getPart().add(new AdxpAdl());
        second.getPart().add(new AdxpAl());
        second.getPart().add(new AdxpBnn());
        second.setUses(new HashSet<PostalAddressUse>());
        second.getUses().add(PostalAddressUse.H);
        second.getUses().add(PostalAddressUse.HP);
        second.setUseablePeriod(makeDtoSimpleIvlTs());

        assertEquals(first.hashCode(), second.hashCode());

        second.getPart().remove(new AdxpAdl());
        second.getPart().add(new AdxpCen());
        second.getPart().add(new AdxpDal());
        second.getPart().add(new AdxpDinst());
        second.getUses().remove(PostalAddressUse.H);
        second.getUses().add(PostalAddressUse.PHYS);
        second.setUseablePeriod(new Ivl<Ts>());

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
        first.setUses(new HashSet<PostalAddressUse>());
        first.getUses().add(PostalAddressUse.H);
        first.getUses().add(PostalAddressUse.HP);
        first.setUseablePeriod(makeDtoSimpleIvlTs());

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
    
    public Ivl<Ts> makeDtoSimpleIvlTs() {
        Ivl<Ts> result = new Ivl<Ts>();
        result.setAny(getTs(1));
        result.setHigh(getTs(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getTs(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getPq(3));

        return result;
    }
    

    private Ts getTs(int offsetFromDefault) {
        Ts result = makeDtoSimpleTs();
        Calendar c = Calendar.getInstance();
        c.setTime(result.getValue());
        c.add(Calendar.DATE, offsetFromDefault);
        result.setValue(c.getTime());

        return result;
    }
    
    private Pq getPq(double value) {
        Pq result = makeDtoSimplePq();
        result.setValue(new Double(value));
        return result;
    }
    
    public Pq makeDtoSimplePq() {
        Pq x = new Pq();
        x.setValue(VALUE_DOUBLE);
        x.setPrecision(PRECISION);
        x.setUnit(UNIT);
        
        Pq uncertainty = new Pq();
        uncertainty.setValue(VALUE_DOUBLE);
        uncertainty.setPrecision(PRECISION);
        uncertainty.setUnit(UNIT);
        x.setUncertainty(uncertainty);
        
        x.setTranslations(new HashSet<Pqr>());
        Pqr pqr = new Pqr();
        pqr.setCode(CODE);
        pqr.setCodeSystem(CODE_SYSTEM);
        St name = new St();
        name.setValue(VALUE);
        pqr.setDisplayName(name);
        x.getTranslations().add(pqr);
        
        return x;
    }
    
    public Ts makeDtoSimpleTs() {
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_STRING);
        //sdf.setLenient(false);
        Ts x = new Ts();
        try {
            x.setValue(sdf.parse(VALUE_DATE));
        } catch (ParseException pe) {
            throw new RuntimeException(pe);
        }
        return x;
    }

}
