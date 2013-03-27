//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.Adxp;
import gov.nih.nci.iso21090.AdxpAdl;
import gov.nih.nci.iso21090.AdxpAl;
import gov.nih.nci.iso21090.AdxpBnn;
import gov.nih.nci.iso21090.AdxpBnr;
import gov.nih.nci.iso21090.AdxpBns;
import gov.nih.nci.iso21090.AdxpCar;
import gov.nih.nci.iso21090.AdxpCen;
import gov.nih.nci.iso21090.AdxpCnt;
import gov.nih.nci.iso21090.AdxpCpa;
import gov.nih.nci.iso21090.AdxpCty;
import gov.nih.nci.iso21090.AdxpDal;
import gov.nih.nci.iso21090.AdxpDel;
import gov.nih.nci.iso21090.AdxpDinst;
import gov.nih.nci.iso21090.AdxpDinsta;
import gov.nih.nci.iso21090.AdxpDinstq;
import gov.nih.nci.iso21090.AdxpDir;
import gov.nih.nci.iso21090.AdxpDmod;
import gov.nih.nci.iso21090.AdxpDmodid;
import gov.nih.nci.iso21090.AdxpPob;
import gov.nih.nci.iso21090.AdxpPre;
import gov.nih.nci.iso21090.AdxpSal;
import gov.nih.nci.iso21090.AdxpSta;
import gov.nih.nci.iso21090.AdxpStb;
import gov.nih.nci.iso21090.AdxpStr;
import gov.nih.nci.iso21090.AdxpSttyp;
import gov.nih.nci.iso21090.AdxpUnid;
import gov.nih.nci.iso21090.AdxpUnit;
import gov.nih.nci.iso21090.AdxpZip;

import org.junit.Test;

/**
 *
 * @author gax
 */
public class AdxpTest {

    @Test
    public void testCreateAddressPart() {
        for (AddressPartType type: AddressPartType.values()){
            Adxp result = createAddressPart(type);
            assertEquals(type, result.getType());
            assertTrue("Adxp"+type.name(), result.getClass().getSimpleName().equalsIgnoreCase("Adxp"+type.name()));
        }


        Adxp result = createAddressPart(null);
        assertEquals(null, result.getType());
    }

    @Test
    public void testEquality() {
        for (AddressPartType type: AddressPartType.values()){
            Adxp first = createAddressPart(type);
            first.setCode("COD");
            first.setValue("value");
            assertTrue(first.equals(first));
            assertFalse(first.equals(null));

            Adxp second = createAddressPart(type);
            second.setCode("COD");
            second.setValue("value");

            assertTrue(first.equals(second));

            second.setCode("NCD");

            assertFalse(first.equals(second));
        }
    }

    @Test
    public void testHashCode() {
        for (AddressPartType type: AddressPartType.values()){
            Adxp first = createAddressPart(type);
            first.setCode("COD");
            first.setValue("value");

            Adxp second = createAddressPart(type);
            second.setCode("COD");
            second.setValue("value");

            assertEquals(first.hashCode(), second.hashCode());

            second.setCode("NCD");

            assertFalse(first.hashCode() == second.hashCode());
        }
    }

    @Test
    public void testCloneable() {
        for (AddressPartType type: AddressPartType.values()){
            Adxp first = createAddressPart(type);
            first.setCode("COD");
            first.setValue("value");

            Adxp second = first.clone();

            assertTrue(first != second);
            assertTrue(first.equals(second));
            assertEquals(first.hashCode(), second.hashCode());
        }
    }

     private static Adxp createAddressPart(AddressPartType type) {
        if (type == null) { return new Adxp(null); }

        switch (type) {
            case ADL: return new AdxpAdl();
            case AL : return new AdxpAl();
            case BNN: return new AdxpBnn();
            case BNR: return new AdxpBnr();
            case BNS: return new AdxpBns();
            case CAR: return new AdxpCar();
            case CEN: return new AdxpCen();
            case CNT: return new AdxpCnt();
            case CPA: return new AdxpCpa();
            case CTY: return new AdxpCty();
            case DAL: return new AdxpDal();
            case DEL: return new AdxpDel();
            case DINST: return new AdxpDinst();
            case DINSTA: return new AdxpDinsta();
            case DINSTQ: return new AdxpDinstq();
            case DIR: return new AdxpDir();
            case DMOD: return new AdxpDmod();
            case DMODID: return new AdxpDmodid();
            case POB: return new AdxpPob();
            case PRE: return new AdxpPre();
            case SAL: return new AdxpSal();
            case STA: return new AdxpSta();
            case STB: return new AdxpStb();
            case STR: return new AdxpStr();
            case STTYP: return new AdxpSttyp();
            case UNID: return new AdxpUnid();
            case UNIT: return new AdxpUnit();
            case ZIP: return new AdxpZip();

            // there must be a new type added
            default: throw new UnsupportedOperationException(type.name());
        }
    }
}