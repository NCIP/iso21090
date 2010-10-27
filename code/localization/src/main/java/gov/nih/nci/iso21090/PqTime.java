package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * PQ.TIME constraints PQ so that it must have units that describe a period of time.
 * TODO invariants are not clear.
 * 
 * @author Vijay Parmar
 *
 */
public class PqTime extends Pq implements Cloneable {

	
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

        if (!(o instanceof PqTime)) {
            return false;
        }

        PqTime x = (PqTime) o;

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
    public PqTime clone() {
        return (PqTime) super.clone();
    }
}
