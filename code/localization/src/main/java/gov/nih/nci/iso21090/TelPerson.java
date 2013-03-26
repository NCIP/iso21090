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
 * Represents the iso TEL.PERSON data type.
 * TEL.PERSON constrains TEL so that it must refer to a method of communication with a person.
 * The URL scheme must be tel, x-text-fax, x-text-tel or mailto
 * @author lpower
 */
public class TelPerson extends Tel implements Cloneable {

    private static final long serialVersionUID = 1L;

    /** set of allowed URI schemes. */
    public static final List<String> SCHEMES = Collections.unmodifiableList(Arrays.asList(
        TelPhone.SCHEME_TEL,
        TelPhone.SCHEME_X_TEXT_FAX,
        TelPhone.SCHEME_X_TEXT_TEL,
        TelEmail.SCHEME_MAILTO));

    /** {@inheritDoc} */
    @Override
    protected List<String> getAllowedSchemes() {
        return SCHEMES;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public TelPerson clone() {
        return (TelPerson) super.clone();
    }
}
