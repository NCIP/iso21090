package gov.nih.nci.iso21090;

import java.io.Serializable;

import org.apache.commons.collections.Predicate;

/**
 * Represents the iso datatype. EN.ON (Organization Name)
 * @author lpower
 */
public final class EnOn extends En implements Cloneable {

    private static final long serialVersionUID = 2L;

    /**
     * serializable predicate that dissallows FAM and GIV parts.
     */
    private static class EnOnPredicate implements Predicate, Serializable {

        private static final long serialVersionUID = 1L;

        /**
         *  {@inheritDoc}
         */
        @SuppressWarnings("PMD.MissingBreakInSwitch")
        public boolean evaluate(Object object) {
            if (object == null) {
                return false;
            }

            Enxp e = (Enxp) object;
            if (e.getType() == null) {
                return true;
            }

            switch (e.getType()) {
                case FAM:
                case GIV:
                    return false;
                default:
                    return true;
            }
        }
    }

    private static final EnOnPredicate ON_PREDICATE = new EnOnPredicate();

    /**
     * Default ctor.
     */
    public EnOn() {
        super(ON_PREDICATE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EnOn clone() {
        return (EnOn) super.clone();
    }
}
