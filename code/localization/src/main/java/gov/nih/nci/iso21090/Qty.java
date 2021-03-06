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
 * Represents iso datatype QTY.
 * @author lpower
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Qty extends Any implements Cloneable {

    /**
     *
     */
    private static final long serialVersionUID = 2L;

    /**
     *
     */
    private Ed expression;

    /**
     *
     */
    private UncertaintyType uncertaintyType;


    /**
     * @return the expression
     */
    public Ed getExpression() {
        return expression;
    }

    /**
     * @param expression the expression to set
     */
    public void setExpression(Ed expression) {
        this.expression = expression;
    }

    /**
     * @return the uncertaintyType
     */
    public UncertaintyType getUncertaintyType() {
        return uncertaintyType;
    }

    /**
     * @param uncertaintyType the uncertaintyType to set
     */
    public void setUncertaintyType(UncertaintyType uncertaintyType) {
        this.uncertaintyType = uncertaintyType;
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

        if (!(obj instanceof Qty)) {
            return false;
        }

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
            .append(super.hashCode())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Qty clone() {
        return (Qty) super.clone();
    }
}
