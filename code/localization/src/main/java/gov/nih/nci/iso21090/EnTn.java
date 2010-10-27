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
