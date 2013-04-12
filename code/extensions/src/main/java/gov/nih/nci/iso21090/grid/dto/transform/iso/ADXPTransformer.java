//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

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

//import org.iso._21090.AddressPartType;

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
    public org.iso._21090.ADXP toXml(Adxp input) {
        // Don't worry about null here - this is a package-protected class and
        // the AD converter
        // has the responsibility to detect null.
        org.iso._21090.ADXP x = new org.iso._21090.ADXP();
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
    public Adxp toDto(org.iso._21090.ADXP input)  throws DtoTransformException {
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
    public static Adxp createAddressPart(AddressPartType type) throws DtoTransformException {
        if (type == null) {
            return new Adxp();
        }
        Class<? extends Adxp> klass = types.get(type);
        try
        {
        Adxp value = klass.newInstance();
        if (value == null) {
            throw new UnsupportedOperationException(type.name());
        }
        return value;
        }
        catch(InstantiationException e)
        {
        	throw new DtoTransformException("Failed instantiating Adxp. ", e);
        }
        catch(IllegalAccessException e)
        {
        	throw new DtoTransformException("Failed instantiating Adxp. ", e);
        }
    }

    private static Map<AddressPartType, Class<? extends Adxp>> types = new HashMap<AddressPartType, Class<? extends Adxp>>();

    static {
        types.put(AddressPartType.ADL, AdxpAdl.class);
        types.put(AddressPartType.AL, AdxpAl.class);
        types.put(AddressPartType.BNN, AdxpBnn.class);
        types.put(AddressPartType.BNR, AdxpBnr.class);
        types.put(AddressPartType.BNS, AdxpBns.class);
        types.put(AddressPartType.CAR, AdxpCar.class);
        types.put(AddressPartType.CEN, AdxpCen.class);
        types.put(AddressPartType.CNT, AdxpCnt.class);
        types.put(AddressPartType.CPA, AdxpCpa.class);
        types.put(AddressPartType.CTY, AdxpCty.class);
        types.put(AddressPartType.DAL, AdxpDal.class);
        types.put(AddressPartType.DEL, AdxpDel.class);
        types.put(AddressPartType.DINST, AdxpDinst.class);
        types.put(AddressPartType.DINSTA, AdxpDinsta.class);
        types.put(AddressPartType.DINSTQ, AdxpDinstq.class);
        types.put(AddressPartType.DIR, AdxpDir.class);
        types.put(AddressPartType.DMOD, AdxpDmod.class);
        types.put(AddressPartType.DMODID, AdxpDmodid.class);
        types.put(AddressPartType.POB, AdxpPob.class);
        types.put(AddressPartType.PRE, AdxpPre.class);
        types.put(AddressPartType.SAL, AdxpSal.class);
        types.put(AddressPartType.STA, AdxpSta.class);
        types.put(AddressPartType.STB, AdxpStb.class);
        types.put(AddressPartType.STR, AdxpStr.class);
        types.put(AddressPartType.STTYP, AdxpSttyp.class);
        types.put(AddressPartType.UNID, AdxpUnid.class);
        types.put(AddressPartType.UNIT, AdxpUnit.class);
        types.put(AddressPartType.ZIP, AdxpZip.class);
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.ADXP[] createXmlArray(int size) throws DtoTransformException {
        return new org.iso._21090.ADXP[size];
    }
}
