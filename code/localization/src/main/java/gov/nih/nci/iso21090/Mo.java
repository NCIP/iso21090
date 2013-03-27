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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 *A MO is a quantity expressing the amount of money in some currency..
 * TODO
 * @author Naveen Amiruddin
 *
 */
public class Mo extends Qty implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Integer value;
    private Integer precision;
    private String currency;

    /**
	 * @return the precision
	 */
	public Integer getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(Integer precision) {
		this.precision = precision;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

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

        if (!(obj instanceof Mo)) {
            return false;
        }

        Mo x = (Mo) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getValue(), x.getValue())
            .append(this.getPrecision(), x.getPrecision())
            .append(this.getCurrency(), x.getCurrency())
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
            .append(this.getCurrency())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Mo clone() {
        return (Mo) super.clone();
    }
}
