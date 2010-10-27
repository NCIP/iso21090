package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso CS data type.
 * @author Vijay Parmar
 */
public class Cs extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Boolean value;

    /**
     * @return the value
     */
    public Boolean getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Boolean value) {
        this.value = value;
    }

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

        if (!(obj instanceof Cs)) {
            return false;
        }

        Cs x = (Cs) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getValue(), x.getValue())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getValue())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Cs clone() {
        return (Cs) super.clone();
    }
}
