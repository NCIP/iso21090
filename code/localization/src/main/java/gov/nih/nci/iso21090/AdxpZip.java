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
 * Represents the iso data type.
 * @author lpower
 *
 */
public class AdxpZip extends Adxp {

    private static final long serialVersionUID = 1L;

    /**
     * default ctor.
     */
    public AdxpZip() {
        super(AddressPartType.ZIP);
    }
}
