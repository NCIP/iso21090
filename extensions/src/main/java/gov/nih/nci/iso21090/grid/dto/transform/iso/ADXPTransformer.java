package gov.nih.nci.iso21090.grid.dto.transform.iso;

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
import gov.nih.nci.iso21090.AdxpInt;
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
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashMap;
import java.util.Map;

import org.iso._21090.ADXP;

/**
 * Transforms the parts of an address. Should only be used by the ADTransformer class.
 */
final class ADXPTransformer extends AbstractTransformer<org.iso._21090.ADXP, Adxp>
    implements Transformer<org.iso._21090.ADXP, Adxp> {

    public static final ADXPTransformer INSTANCE = new ADXPTransformer();

    private ADXPTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public ADXP toXml(Adxp input) {
        // Don't worry about null here - this is a package-protected class and
        // the AD converter
        // has the responsibility to detect null.
        ADXP x = new ADXP();
        x.setCode(input.getCode());
        x.setCodeSystem(input.getCodeSystem());
        x.setValue(input.getValue());
        org.iso._21090.AddressPartType type = AddressPartTypeTransformer.INSTANCE.toXml(input.getType());
        x.setType(type);
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Adxp toDto(ADXP input) {
        // Don't worry about null here - this is a package-protected class and
        // the AD converter
        // has the responsibility to detect null.
        AddressPartType type = AddressPartTypeTransformer.INSTANCE.toDto(input.getType());
        Adxp d = createAddressPart(type);
        d.setCode(input.getCode());
        d.setCodeSystem(input.getCodeSystem());
        d.setValue(input.getValue());
        return d;
    }

    /**
     * factory method to create an address part.
     *
     * @param type the typeof the part to create.
     * @return an address part with the
     */
    public static Adxp createAddressPart(AddressPartType type) {
        if (type == null) {
            return new Adxp();
        }
        Adxp value = types.get(type);
        if (value == null) {
            throw new UnsupportedOperationException(type.name());
        }
        return value;
    }

    private static Map<AddressPartType, Adxp> types = new HashMap<AddressPartType, Adxp>();

    static {
        types.put(AddressPartType.ADL, new AdxpAdl());
        types.put(AddressPartType.AL, new AdxpAl());
        types.put(AddressPartType.BNN, new AdxpBnn());
        types.put(AddressPartType.BNR, new AdxpBnr());
        types.put(AddressPartType.BNS, new AdxpBns());
        types.put(AddressPartType.CAR, new AdxpCar());
        types.put(AddressPartType.CEN, new AdxpCen());
        types.put(AddressPartType.CNT, new AdxpCnt());
        types.put(AddressPartType.CPA, new AdxpCpa());
        types.put(AddressPartType.CTY, new AdxpCty());
        types.put(AddressPartType.DAL, new AdxpDal());
        types.put(AddressPartType.DEL, new AdxpDel());
        types.put(AddressPartType.DINST, new AdxpDinst());
        types.put(AddressPartType.DINSTA, new AdxpDinsta());
        types.put(AddressPartType.DINSTQ, new AdxpDinstq());
        types.put(AddressPartType.DIR, new AdxpDir());
        types.put(AddressPartType.DMOD, new AdxpDmod());
        types.put(AddressPartType.DMODID, new AdxpDmodid());
        types.put(AddressPartType.INT, new AdxpInt());
        types.put(AddressPartType.POB, new AdxpPob());
        types.put(AddressPartType.PRE, new AdxpPre());
        types.put(AddressPartType.SAL, new AdxpSal());
        types.put(AddressPartType.STA, new AdxpSta());
        types.put(AddressPartType.STB, new AdxpStb());
        types.put(AddressPartType.STR, new AdxpStr());
        types.put(AddressPartType.STTYP, new AdxpSttyp());
        types.put(AddressPartType.UNID, new AdxpUnid());
        types.put(AddressPartType.UNIT, new AdxpUnit());
        types.put(AddressPartType.ZIP, new AdxpZip());
    }

    /**
     * {@inheritDoc}
     */
    public ADXP[] createXmlArray(int size) throws DtoTransformException {
        return new ADXP[size];
    }
}
