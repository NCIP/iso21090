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

import gov.nih.nci.iso21090.EnTn.SerializableNotNullPredicate;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

/**
 * Represents the iso datatype. EN.TN (Trivial Name)
 * 
 * @author Vijay Parmar
 */
public final class EnTn extends En implements Cloneable {

    private static final long serialVersionUID = 2L;

    /**
     * serializable predicate for notnull.
     */
    public static class SerializableNotNullPredicate implements Predicate, Serializable {
        private static final long serialVersionUID = 1L;
        
        /**
         * {@inheritDoc}
         */
        public boolean evaluate(Object object) {
            return object != null;
        }
       
        
    }

    private static final SerializableNotNullPredicate TN_PREDICATE = new SerializableNotNullPredicate();
    
    /**
     * Default ctor.
     */
    public EnTn() {
        super(TN_PREDICATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnTn clone() {
        return (EnTn) super.clone();
    }
}
