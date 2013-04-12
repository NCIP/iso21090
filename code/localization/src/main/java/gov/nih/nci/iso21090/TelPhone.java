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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * Represents the iso TEL.PHONE data type.
 * TEL.PHONE constrains TEL.PERSON so it must refer to some telephone based communication system with a person.
 * The URL scheme must be tel, x-text-fax, or x-text-tel.
 * @author lpower
 */
public final class TelPhone extends TelPerson implements Cloneable {

    /** scheme. */
    public static final String SCHEME_TEL = "tel";
    /** scheme. */
    public static final String SCHEME_X_TEXT_FAX = "x-text-fax";
    /** scheme. */
    public static final String SCHEME_X_TEXT_TEL = "x-text-tel";

    /** set of allowed URI schemes. */
    public static final List<String> TEL_PHONE_SCHEMES = Collections.unmodifiableList(Arrays.asList(
            SCHEME_TEL,
            SCHEME_X_TEXT_FAX,
            SCHEME_X_TEXT_TEL));

    private static final long serialVersionUID = 1L;

    /** {@inheritDoc} */
    @Override
    protected List<String> getAllowedSchemes() {
        return TEL_PHONE_SCHEMES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelPhone clone() {
        return (TelPhone) super.clone();
    }
}
