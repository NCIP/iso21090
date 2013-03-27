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

package gov.nih.nci.iso21090.reference.convert;

import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.St;

/**
 * @author aevansel
 */
public class StConverter {
    
    /**
     * Convert an St to a String.
     * @param iso an ST.
     * @return a string.
     */
    public static String convertToString(St iso) {
        if (iso == null) {
            // this should not happen, but we might be able to help convert
            return null;
        }
        if (iso.getNullFlavor() != null) {
            return null;
        }
        if (iso.getValue() == null || iso.getValue().length() == 0) {
            throw new IllegalArgumentException("nullFlavor or value must be set");
        }
        return iso.getValue();
    }
    
    /**
     * @param value a string to parse.
     * @return an iso ST
     */
    public static St convertToSt(String value) {
        St iso = new St();
        if (value == null || value.length() == 0) {
            iso.setNullFlavor(NullFlavor.NI);
        } else {
            iso.setValue(value);
        }
        return iso;
    }
}
