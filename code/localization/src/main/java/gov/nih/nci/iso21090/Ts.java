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

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents iso data type TS.
 * @author lpower
 */
public final class Ts extends Qty implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Date value;

    /**
     * @return the value
     */
    public Date getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Date value) {
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

        if (!(obj instanceof Ts)) {
            return false;
        }

        Ts x = (Ts) obj;

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
    public Ts clone() {
        return (Ts) super.clone();
    }
}
