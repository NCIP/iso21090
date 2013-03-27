//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso Any type.
 * @author Scott Miller
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Any implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private NullFlavor nullFlavor;
    /**
     * Hash code seeding int for HashCodeBuilder.
     */
    protected static final int HASH_CODE_SEED_1 = 15;
    /**
     * Hash code seeding int for HashCodeBuilder.
     */
    protected static final int HASH_CODE_SEED_2 = 51;

    /**
     * @return the nullFlavor
     */
    public NullFlavor getNullFlavor() {
        return this.nullFlavor;
    }


    /**
     * @param nullFlavor the NullFlavor to set
     */
    public void setNullFlavor(NullFlavor nullFlavor) {
        this.nullFlavor = nullFlavor;
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

        if (!(obj instanceof Any)) {
            return false;
        }


        Any x = (Any) obj;

        return new EqualsBuilder()
            .append(this.getNullFlavor(), x.getNullFlavor())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getNullFlavor())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Any clone() {

        Any snapshot = null;
        try {
            snapshot = (Any) BeanUtils.cloneBean(this);
        } catch (Exception e) {
            throw new IsoCloneException(e);
        }

        return snapshot;
    }

}