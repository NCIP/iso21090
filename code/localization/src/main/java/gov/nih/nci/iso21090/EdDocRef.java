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
 * Represents the iso ED.DOC.REF type.
 * TODO Invariants checks
 . ED.DOC.REF constrains ED.DOC so that the document must be provided by means of a
reference.
·  A reference is required  
 * @author Vijay Parmar
 */
public final class EdDocRef extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdDocRef clone() {
        return (EdDocRef) super.clone();
    }
}
