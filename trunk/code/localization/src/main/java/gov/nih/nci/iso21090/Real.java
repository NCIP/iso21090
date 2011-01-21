package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Real numbers. The initial use case is to capture a java.lang.Float in the caDSR data model.
 *
 * @author mshestopalov
 *
 */
public final class Real extends Qty implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Double value;

    /**
     *
     * @return value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value value of Double
     */
    public void setValue(Double value) {
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

        if (!(obj instanceof Real)) {
            return false;
        }

        Real x = (Real) obj;

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
    @Override
    public Real clone() {
        return (Real) super.clone();
    }
}
