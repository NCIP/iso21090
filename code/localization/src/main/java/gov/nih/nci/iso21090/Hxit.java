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
 * Represents the iso Hxit type.
 * TODO invariants check
 * 		If a controlActExtension is provided, a ControlActRoot must also be provided
 * 
 * @author Vijay Parmar
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Hxit implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    
    private String validTimeLow;
    private String validTimeHigh;
    private String controlActRoot;
    private String controlActExtension;
    

	/**
	 * @return the validTimeLow
	 */
	public String getValidTimeLow() {
		return validTimeLow;
	}

	/**
	 * @param validTimeLow the validTimeLow to set
	 */
	public void setValidTimeLow(String validTimeLow) {
		this.validTimeLow = validTimeLow;
	}

	/**
	 * @return the validTimeHigh
	 */
	public String getValidTimeHigh() {
		return validTimeHigh;
	}

	/**
	 * @param validTimeHigh the validTimeHigh to set
	 */
	public void setValidTimeHigh(String validTimeHigh) {
		this.validTimeHigh = validTimeHigh;
	}

	/**
	 * @return the controlActRoot
	 */
	public String getControlActRoot() {
		return controlActRoot;
	}

	/**
	 * @param controlActRoot the controlActRoot to set
	 */
	public void setControlActRoot(String controlActRoot) {
		this.controlActRoot = controlActRoot;
	}

	/**
	 * @return the controlActExtension
	 */
	public String getControlActExtension() {
		return controlActExtension;
	}

	/**
	 * @param controlActExtension the controlActExtension to set
	 */
	public void setControlActExtension(String controlActExtension) {
		this.controlActExtension = controlActExtension;
	}

	/**
     * Hash code seeding int for HashCodeBuilder.
     */
    protected static final int HASH_CODE_SEED_1 = 15;
    /**
     * Hash code seeding int for HashCodeBuilder.
     */
    protected static final int HASH_CODE_SEED_2 = 51;


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

        if (!(obj instanceof Hxit)) {
            return false;
        }


        Hxit x = (Hxit) obj;

        return new EqualsBuilder()
            .append(this.getControlActRoot(), x.getControlActRoot())
            .append(this.getControlActExtension(), x.getControlActExtension())
            .isEquals();
        
        /*.append(this.getValidTimeLow(), x.getValidTimeLow())
        .append(this.getValidTimeHigh(), x.getValidTimeHigh())*/
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getControlActRoot())
            .append(this.getControlActExtension())
            .toHashCode();
    }
    
    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Hxit clone() {

        Hxit snapshot = null;
        try {
            snapshot = (Hxit) BeanUtils.cloneBean(this);
        } catch (Exception e) {
            throw new IsoCloneException(e);
        }

        return snapshot;
    }

}