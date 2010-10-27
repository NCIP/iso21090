package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso Cd.CS type.
 * CD.CS constrains CD so that it may only contain plain text code. 
 * code SHALL only contain characters that are either a letter, a digit, or one of '.', '-', '_' or ':'.
 * Code systems that are used with CS SHALL NOT define code symbols or expression syntaxes that contain 
 * whitespace or any other characters not in this list.
 *
 * 
 * @author Vijay Parmar
 */
public final class CdCs extends Cd implements Cloneable {

    private static final long serialVersionUID = 1L;

    
    /**
     * @param  code data to set - code is required in CD.CS
     */
    @Override
    public void setCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Code is required in CD.CS");
        }
        super.setCode(code);
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

        if (!(obj instanceof Cd)) {
            return false;
        }

        Cd x = (Cd) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getCode(), x.getCode())
            .append(this.getCodeSystem(), x.getCodeSystem())
            .isEquals();
    }
   
    /**
     * {@inheritDoc}
     */
    @Override
    public CdCs clone() {
        return (CdCs) super.clone();
    }
}
