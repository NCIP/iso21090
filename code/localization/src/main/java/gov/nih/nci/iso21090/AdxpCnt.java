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

package gov.nih.nci.iso21090;

import java.util.Locale;

/**
 * The type CNT (Country) is bound to the 3 letter codes defined in ISO 3166.
 * @author lpower
 *
 */
public final class AdxpCnt extends Adxp {
    private static final long serialVersionUID = 1L;

    private static final int CODE_LENGTH = 3;

    /** type is always {@link AddressPartType#CNT}. */
    public AdxpCnt() {
        super(AddressPartType.CNT);
    }

    /**
     * http://en.wikipedia.org/wiki/ISO_3166-1_alpha-3.
     * @param code 3 letter uppercase iso code.
     */
    @SuppressWarnings("PMD.UnnecessaryCaseChange")
    @Override
    public void setCode(String code) {
        if (code != null && (code.length() != CODE_LENGTH || !code.toUpperCase(Locale.ENGLISH).equals(code))) {
            throw new IllegalArgumentException("must be ISO 3166-1 alpha-3 code");
        }
        super.setCode(code);
    }

}
