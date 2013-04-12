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


/**
 * Represents the iso ST data type.
 * A SC is a coded ST.
 * @author mshestopalov
 */
public final class Sc extends St implements Cloneable {
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