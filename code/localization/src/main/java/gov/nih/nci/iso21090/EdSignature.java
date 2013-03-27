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

/**
 * Represents the iso ED.SIGNATURE type.
 * TODO Invariants checks
. No value, data, reference, integrity check, thumbnail, compression, language or
translations are allowed
. The media type must be text/xml  
 * @author Vijay Parmar
 */
public final class EdSignature extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdSignature clone() {
        return (EdSignature) super.clone();
    }
}
