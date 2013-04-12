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
 * Represents the iso CodingRationale type.
 * @author Vijay Parmar
 */
public enum CodingRationale {

    /** original: Originally produced code. */
    O,
    /** post-coded: post-coded from free text source. */
    P,
    /** required: Required by the specification describing the use of the coded concept.*/
    R;
}

