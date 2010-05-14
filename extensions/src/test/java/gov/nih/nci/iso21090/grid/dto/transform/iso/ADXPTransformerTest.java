package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

public class ADXPTransformerTest {

    @Test
    public void testCreateAddressPart() {
        verify(new AdxpAdl(), AddressPartType.ADL);
        verify(new AdxpAl(), AddressPartType.AL);
        verify(new AdxpBnn(), AddressPartType.BNN);
        verify(new AdxpBnr(), AddressPartType.BNR);
        verify(new AdxpBns(), AddressPartType.BNS);
        verify(new AdxpCar(), AddressPartType.CAR);
        verify(new AdxpCen(), AddressPartType.CEN);
        verify(new AdxpCnt(), AddressPartType.CNT);
        verify(new AdxpCpa(), AddressPartType.CPA);
        verify(new AdxpCty(), AddressPartType.CTY);
        verify(new AdxpDal(), AddressPartType.DAL);
        verify(new AdxpDel(), AddressPartType.DEL);
        verify(new AdxpDinst(), AddressPartType.DINST);
        verify(new AdxpDinsta(), AddressPartType.DINSTA);
        verify(new AdxpDinstq(), AddressPartType.DINSTQ);
        verify(new AdxpDir(), AddressPartType.DIR);
        verify(new AdxpDmod(), AddressPartType.DMOD);
        verify(new AdxpDmodid(), AddressPartType.DMODID);
        verify(new AdxpPob(), AddressPartType.POB);
        verify(new AdxpPre(), AddressPartType.PRE);
        verify(new AdxpSal(), AddressPartType.SAL);
        verify(new AdxpSta(), AddressPartType.STA);
        verify(new AdxpStb(), AddressPartType.STB);
        verify(new AdxpStr(), AddressPartType.STR);
        verify(new AdxpSttyp(), AddressPartType.STTYP);
        verify(new AdxpUnid(), AddressPartType.UNID);
        verify(new AdxpUnit(), AddressPartType.UNIT);
        verify(new AdxpZip(), AddressPartType.ZIP);
    }

    private void verify(Adxp expectedType, AddressPartType adl) {
		try{
			Adxp adxp = ADXPTransformer.createAddressPart(adl);
			assertNotNull(adxp);
			assertEquals(expectedType.getClass(), adxp.getClass());
		}catch(gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException e){
    		assert false;
    	}
    }

    /**
     * This test will uncover missing entries in the Map< AddressPartType, Adxp>, if an exception is thrown then a AddressPartType is missing
     */
    @Test
    public void testCreateAddressPartUnknown() {
		try{
			for (AddressPartType type : AddressPartType.values()) {
				assertNotNull(ADXPTransformer.createAddressPart(type));
			}
		}catch(gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException e){
    		assert false;
    	}
    }
    @Test
    public void testCreateAddressPartWhenParamIsNull() {
        verify(new Adxp(), null);
    }

}
