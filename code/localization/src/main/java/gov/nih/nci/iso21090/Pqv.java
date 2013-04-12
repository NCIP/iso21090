//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Physical Quantity Value (PQV).
 * @author Naveen Amiruddin
 *
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Pqv extends Qty implements Cloneable {
    private static final long serialVersionUID = 1L;

    private Double value;
    private Integer precision = 0;

    /**
     *
     * @return value
     */
    public Double getValue() {
        return value;
    }

    /**
     *
     * @param value the value
     */
    public void setValue(Double value) {
        this.value = value;
    }

    /**
     *
     * @return precision
     */
    public Integer getPrecision() {
        return precision;
    }

    /**
     *
     * @param precision precision digits
     */
    public void setPrecision(Integer precision) {
        if (precision != null) {
            this.precision = precision;
        } else {
            this.precision = 0;
        }
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

        if (!(o instanceof Pqv)) {
            return false;
        }

        Pqv x = (Pqv) o;

        return new EqualsBuilder()
            .appendSuper(super.equals(o))
            .append(this.getValue(), x.getValue())
            .append(this.getPrecision(), x.getPrecision())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getValue())
            .append(this.getPrecision())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Pqv clone() {
        return (Pqv) super.clone();
    }
}
