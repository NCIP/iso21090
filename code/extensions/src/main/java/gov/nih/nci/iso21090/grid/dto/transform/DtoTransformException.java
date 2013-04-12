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

package gov.nih.nci.iso21090.grid.dto.transform;

/**
 * General exception all transformers throw.
 */
public class DtoTransformException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Construct with empty cause and message.
     */
    public DtoTransformException() {
        super();
    }

    /**
     * Construct with given message and empty cause.
     *
     * @param message message.
     */
    public DtoTransformException(String message) {
        super(message);
    }

    /**
     * Construct with given cause and empty message.
     *
     * @param cause underlying cause.
     */
    public DtoTransformException(Throwable cause) {
        super(cause);
    }

    /**
     * Construct with given cause and message.
     *
     * @param message message.
     * @param cause underlying cause.
     */
    public DtoTransformException(String message, Throwable cause) {
        super(message, cause);
    }

}
