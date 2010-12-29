package gov.nih.nci.iso21090;

import java.util.HashSet;
import java.util.Set;

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
    private Set<Pqr> translations;


    /**
     * @return the translations
     */
    public Set<Pqr> getTranslations() {
        if (this.translations == null) {
            this.translations = new HashSet<Pqr>();
        }
        return translations;
    }

    /**
     * @param translations the translations to set
     */
    public void setTranslations(Set<Pqr> translations) {
        this.translations = translations;
    }

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
            .append(this.getPrecision(), x.getPrecision())
            .append(this.getValue(), x.getValue())            
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getUnit())
            .append(this.getPrecision())
            .append(this.getValue())              
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
