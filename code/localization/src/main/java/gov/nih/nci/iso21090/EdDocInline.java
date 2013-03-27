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
 * Represents the iso ED.DOC.INLINE type.
 * * TODO Invariants checks
 * ED.DOC.INLINE constrains ED.DOC so that the document must not be provided by means of
a reference.  
 * @author Vijay Parmar
 */
public final class EdDocInline extends EdDoc implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdDocInline clone() {
        return (EdDocInline) super.clone();
    }
}
