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

package gov.nih.nci.iso21090.reference.convert;

import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.NullFlavor;

import org.apache.commons.lang.StringUtils;

/**
 * Utility class to generate ISO EN.PN type.
 */
public class PersonNameConverterUtil {

    private static void addEnxp(EnPn enpn, String value, EntityNamePartType type) {
        if (StringUtils.isNotEmpty(value)) {
            Enxp part = new Enxp(type);
            part.setValue(value);
            enpn.getPart().add(part);
        }
    }

    /**
     * @param firstName given name
     * @param middleName middle name
     * @param lastName family name
     * @param prefix prefix
     * @param suffix suffix
     * @return ISO EN Person Name
     */
    public static final EnPn convertToEnPn(String firstName, String middleName,
            String lastName, String prefix, String suffix) {
        EnPn enpn = new EnPn();
        addEnxp(enpn, lastName, EntityNamePartType.FAM);
        addEnxp(enpn, firstName, EntityNamePartType.GIV);
        addEnxp(enpn, middleName, EntityNamePartType.GIV);
        addEnxp(enpn, prefix, EntityNamePartType.PFX);
        addEnxp(enpn, suffix, EntityNamePartType.SFX);
        if (enpn.getPart().size() <= 0) {
            enpn.setNullFlavor(NullFlavor.NI);
        }
        return enpn;
    }
}
