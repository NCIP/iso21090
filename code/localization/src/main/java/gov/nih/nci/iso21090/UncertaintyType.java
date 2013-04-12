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
 * Represents iso enum UncertaintyType.
 * @author lpower
 */
public enum UncertaintyType {

    /** uniform. */
    U,
    /** normal (gaussian). */
    N,
    /** log-normal. */
    LN,
    /** ? (gamma) */
    G,
    /** exponential. */
    E,
    /** ? */
    X2,
    /** t (student). */
    T,
    /** f. */
    F,
    /** ? (beta) */
    B;
}
