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

import java.io.Serializable;

import org.apache.commons.collections.Predicate;


/**
 * Represents the iso datatype. EN.PN (Person Name)
 * @author lpower
 */
public final class EnPn extends En implements Cloneable {

    private static final long serialVersionUID = 2L;

    /**
     * serializable predicate for notnull.
     */
    private static class SerializableNotNullPredicate implements Predicate, Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * {@inheritDoc}
         */
        public boolean evaluate(Object object) {
            return object != null;
        }
    }

    private static final SerializableNotNullPredicate PN_PREDICATE = new SerializableNotNullPredicate();

    /**
     * Default ctor.
     */
    public EnPn() {
        super(PN_PREDICATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnPn clone() {
        return (EnPn) super.clone();
    }
}
