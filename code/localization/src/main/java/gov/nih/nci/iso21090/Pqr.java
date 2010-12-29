package gov.nih.nci.iso21090;

import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Physical Quantity Representation (PQR)
 * 
 * An extension of the coded value datatype representing a physical quantity using a unit from
 * any code system. Used to show alternative representation for a physical quantity.
 * 
 * Only code, codeSystem, and optionally displayName allowed
 * 
 * @author Vijay Parmar, Daniel Dumitru
 *
 */
public class Pqr extends Cd implements Cloneable {

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOriginalText(EdText originalText) {
        throw new IllegalArgumentException("originalText not allowed in PQR");
    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setCodeSystemName(String codeSystemName) {
//        throw new IllegalArgumentException("codeSystemName not allowed in PQR");
//    }
//
//    /**
//     * {@inheritDoc}
//     */
//    @Override
//    public void setCodeSystemVersion(String codeSystemVersion) {
//        throw new IllegalArgumentException("codeSystemVersion not allowed in PQR");
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueSet(String valueSet) {
        throw new IllegalArgumentException("valueSet not allowed in PQR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValueSetVersion(String valueSetVersion) {
        throw new IllegalArgumentException("valueSetVersion not allowed in PQR");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setTranslations(Set<Cd> translations) {
        throw new IllegalArgumentException("translations not allowed in PQR");
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
          .append(this.getCode(), x.getCode())
          .append(this.getCodeSystem(), x.getCodeSystem())
          .isEquals();
        }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
        .append(this.getCode())
        .append(this.getCodeSystem())
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