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
 * Physical Quantity (PQ)
 * A dimensioned quantity expressing the result of measuring.
 * @author Naveen Amiruddin
 *
 */
public class Pq extends Pqv implements Cloneable {
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

    /* (non-Javadoc)
     * @see gov.nih.nci.iso21090.Qty#setOriginalText(gov.nih.nci.iso21090.EdText)
     */
    /**
     * @param originalText originalText
     */
    public final void setOriginalText(EdText originalText) {
        throw new IllegalArgumentException("originalText not allowed in PQ");
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
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Pq clone() {
        return (Pq) super.clone();
    }
}
