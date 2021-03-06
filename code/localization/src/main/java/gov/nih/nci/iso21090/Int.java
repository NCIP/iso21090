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
 * Integer numbers (-1,0,1,2, 100, 3398129, etc.) are precise numbers that are results of
 * counting and enumerating. Integer numbers are discrete, the set of integers is
 * infinite but countable. No arbitrary limit is imposed on the range of integer
 * numbers..
 *
 * @author Naveen Amiruddin
 *
 */
public final class Int extends Qty implements Cloneable {

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

        if (!(obj instanceof Int)) {
            return false;
        }

        Int x = (Int) obj;

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
    public Int clone() {
        return (Int) super.clone();
    }
}
