package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Positive Integer numbers (1,2, 100, 3398129, etc.) are precise numbers that are results of
 * counting and enumerating. 
 *
 * @author Vijay Parmar
 */
public final class IntPos extends Int implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Integer value;

    /**
     *
     * @return value
     */
    public Integer getValue() {
        return value;
    }

    /**
    *
    * @param value value of Integer
    */
   public void setValue(Integer value) {
	   if(value!=null){
		   if(value < 1){
			   throw new IllegalArgumentException("Negative or zero integer values not allowed in INT.POS");
	   		}
	   	}
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

        if (!(obj instanceof IntPos)) {
            return false;
        }

        IntPos x = (IntPos) obj;

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
    public IntPos clone() {
        return (IntPos) super.clone();
    }
}
