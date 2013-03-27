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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the iso TEL.EMAIL data type.
 * TEL.EMAIL constrains the TEL.PERSON type to be an email address.
 * The URL scheme must be mailto
 * @author lpower
 */
public final class TelEmail extends TelPerson implements Cloneable {

    private static final long serialVersionUID = 1L;

    /** scheme. */
    public static final String SCHEME_MAILTO = "mailto";

    /** set of allowed URI schemes. */
    public static final List<String> TEL_EMAIL_SCHEMES = Collections.unmodifiableList(Arrays.asList(SCHEME_MAILTO));

    /** {@inheritDoc} */
    @Override
    protected List<String> getAllowedSchemes() {
        return TEL_EMAIL_SCHEMES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelEmail clone() {
        return (TelEmail) super.clone();
    }
}
