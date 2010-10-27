package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents data where coded values are associated with a specific order.
 *
 * @author Vijay Parmar
 *
 */
public class Co extends Qty implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Integer value;
    private Cd code;

    /**
	 * @return the code
	 */
	public Cd getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Cd code) {
		this.code = code;
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

        if (!(obj instanceof Co)) {
            return false;
        }

        Co x = (Co) obj;
        if(x.getCode()==null || this.getCode()==null){
        	return false;
        }

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
    public Co clone() {
        return (Co) super.clone();
    }
}
