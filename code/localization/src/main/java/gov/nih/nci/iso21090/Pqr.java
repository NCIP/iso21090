package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Physical Quantity Representation (PQR)
 * 
 * An extension of the coded value datatype representing a physical quantity using a unit from
    any code system. Used to show alternative representation for a physical quantity.
 * @author Vijay Parmar
 *
 */
public class Pqr extends Cd implements Cloneable {
	

   /**
    * @param originalText
    */
   public void setOriginalText(Cd originalText) {
   			throw new IllegalArgumentException("originalText not allowed in PQR");
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

        if (!(obj instanceof Pqr)) {
            return false;
        }

        Pqr x = (Pqr) obj;

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
            .appendSuper(super.hashCode())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Pqr clone() {
        return (Pqr) super.clone();
    }
}
