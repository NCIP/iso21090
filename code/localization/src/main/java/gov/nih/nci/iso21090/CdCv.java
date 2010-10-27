package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Constrains CD. 
 * Coded data, specifying only a code, code system, and optionally display name 
 * and original text. Used only as the type of properties of other datatypes.
 *
 * Invariants :
	- No translations are allowed
 	- No source is allowed

 * @author Vijay Parmar
 */
public  class CdCv extends Cd implements Cloneable {

    private static final long serialVersionUID = 1L;

    /* (non-Javadoc)
     * @see gov.nih.nci.iso21090.Cd#setSource(gov.nih.nci.iso21090.Cd)
     */
    public void setSource(Cd source) {
    	if(source!=null){
            throw new IllegalArgumentException("Source is not allowed in CD.CV");
    	}
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
    public CdCv clone() {
        return (CdCv) super.clone();
    }
}
