package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents iso datatype QTY.  Note: Equality is not defined for the QTY
 * datatype as it is an abstract type.  The QTY attributes (expression,
 * originalText, uncertainty and uncertaintyType) never participate in the
 * determination of equality or hashcode generation of specializations of QTY.
 * 
 * @author lpower, Dan Dumitru
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Qty extends Any implements Cloneable {

    private static final long serialVersionUID = 2L;

    private Ed expression;
    private EdText originalText;
    private Qty uncertainty;
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
     * @return the originalText
     */
    public EdText getOriginalText() {
        return originalText;
    }

    /**
     * @param originalText the originalText to set
     */
    public void setOriginalText(EdText originalText) {
        this.originalText = originalText;
    }

    /**
     * @return the uncertainty
     */
    public Qty getUncertainty() {
        return uncertainty;
    }

    /**
     * @param uncertainty the uncertainty to set
     */
    public void setUncertainty(Qty uncertainty) {
        if (uncertainty  != null) {
            if (!(uncertainty instanceof gov.nih.nci.iso21090.Qty) 
                    && !(uncertainty instanceof gov.nih.nci.iso21090.Real) 
                    && !(uncertainty instanceof gov.nih.nci.iso21090.Pq) 
                    && !(uncertainty instanceof gov.nih.nci.iso21090.Ts)) {
                    throw new IllegalArgumentException(
                        "QTY.uncertainty must be an instance of Real, Pq, or Ts");
            }
            if (uncertainty.getExpression() !=  null
                    || uncertainty.getUncertainty() !=  null
                    || uncertainty.getUncertaintyType() !=  null
                    || uncertainty.getOriginalText() !=  null
                    || uncertainty.getNullFlavor() !=  null) {
                throw new IllegalArgumentException(
            "Expression, Uncertainty, Uncertainty Type, originalText, and Null Flavor not allowed in QTY.uncertainty");
            }
        }
        this.uncertainty = uncertainty;
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
