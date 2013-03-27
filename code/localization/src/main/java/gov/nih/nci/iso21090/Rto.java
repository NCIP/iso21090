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
 * A quantity constructed as the quotient of a numerator quantity divided by a denominator
 * quantity.
 *
 * @author Vijay Parmar
 */
public class Rto extends Qty implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Qty numerator;
    private Qty denominator;

   

    /**
	 * @return the numerator
	 */
	public Qty getNumerator() {
		return numerator;
	}

	/**
	 * @param numerator the numerator to set
	 */
	public void setNumerator(Qty numerator) {
		if(numerator.getUncertainty()!=null ){
			throw new IllegalArgumentException("Uncertainty not allowed in RTO");
		}	
		this.numerator = numerator;
	}

	/**
	 * @return the denominator
	 */
	public Qty getDenominator() {
		return denominator;
	}

	/**
	 * @param denominator the denominator to set
	 */
	public void setDenominator(Qty denominator) {
		if(denominator.getUncertainty()!=null ){
			throw new IllegalArgumentException("Uncertainty not allowed in RTO");
		}
		this.denominator = denominator;
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

        if (!(obj instanceof Rto)) {
            return false;
        }

        Rto x = (Rto) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getNumerator(), x.getNumerator())
            .append(this.getDenominator(), x.getDenominator())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getNumerator())
            .append(this.getDenominator())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Rto clone() {
        return (Rto) super.clone();
    }
}
