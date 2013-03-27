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
 * Represents the iso UpdateMode enum.
 * @author Vijay Parmar
 */
public enum UpdateMode {
    
    /** add. */
    A, 
    /** delete. */
    D, 
    /** replace. */
    R, 
    /** add or replace. */
    AR, 
    /** No Change. */
    N, 
    /** Unknown. */
    U, 
    /** key.*/
    K; 
    
}
