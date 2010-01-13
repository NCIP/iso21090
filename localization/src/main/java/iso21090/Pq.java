package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Physical Quantity (PQ)/
 * A dimensioned quantity expressing the result of measuring.
 * @author Naveen Amiruddin
 *
 */
public final class Pq extends Pqv implements Cloneable {
    private static final long serialVersionUID = 1L;

    private String unit;

    /**
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
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

        if (!(o instanceof Pq)) {
            return false;
        }

        Pq x = (Pq) o;

        return new EqualsBuilder()
            .appendSuper(super.equals(o))
            .append(this.getUnit(), x.getUnit())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getUnit())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pq clone() {
        return (Pq) super.clone();
    }
}
