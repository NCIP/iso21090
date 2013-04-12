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
 *
 * @author mshestopalov
 *
 */
public class IsoCloneException extends RuntimeException {

    private static final long serialVersionUID = -8652832715145657044L;

    /**
     * const.
     * @param e runtime.
     */
    public IsoCloneException(Throwable e) {
        super(e);
    }

}
