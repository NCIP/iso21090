package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents iso data type QSET.
 * TODO
 * @author lpower
 * @param <T>
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Urg<T extends Qty> extends Any implements Cloneable {

    private static final long serialVersionUID = 2L;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Urg<?>)) {
            return false;
        }

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(super.hashCode())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings({"unchecked", "PMD.CloneThrowsCloneNotSupportedException" })
    @Override
    public Urg<T> clone() {
        return (Urg<T>) super.clone();
    }
}
