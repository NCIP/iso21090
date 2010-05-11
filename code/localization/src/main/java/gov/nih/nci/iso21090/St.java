package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso ST data type.
 * A ST SHALL have at least one character or else be NULL.
 * @author Scott Miller
 */
public class St extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private String value;

    /**
     * @return the value
     */
    public String getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof St)) {
            return false;
        }


        St x = (St) o;

        return new EqualsBuilder()
            .appendSuper(super.equals(o))
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
    public St clone() {
        return (St) super.clone();
    }
}