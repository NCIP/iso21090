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
 * Specializes the iso ST data type.
 * A SC is a coded ST.
 * TODO invariants check
 . If there is a code, there must also be some content on the SC (and therefore the SC
must not be null)
�  The originalText value of the CD must be null (The originalText is the SC.value)
 * 
 * @author Vijay Parmar
 */
public final class ScNt extends Sc implements Cloneable {
    private Cd code;

    /**
     * @param code the code to set
     */
    public void setCode(Cd code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public Cd getCode() {
        return code;
    }
}
