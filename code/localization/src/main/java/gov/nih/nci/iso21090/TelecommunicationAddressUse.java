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
 * Represents the iso TelecommunicationAddressUse enum.
 * @author lpower
 */
public enum TelecommunicationAddressUse {
    
    /** home address. */
    H, 
    /** primary home. */
    HP, 
    /** vacation home. */
    HV, 
    /** work place. */
    WP, 
    /** direct. */
    DIR, 
    /** public. */
    PUB, 
    /** bad address. */
    BAD, 
    /** temporary address. */
    TMP, 
    /** answering service. */
    AS, 
    /** emergency contact. */
    EC, 
    /** mobile contact. */
    MC, 
    /** pager. **/
    PG;
}
