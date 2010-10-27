package gov.nih.nci.iso21090;


/**
 * Specializes the iso ST data type.
 * A SC is a coded ST.
 * TODO invariants check
 . If there is a code, there must also be some content on the SC (and therefore the SC
must not be null)
·  The originalText value of the CD must be null (The originalText is the SC.value)
 * 
 * @author Vijay Parmar
 */
public final class ScNt extends Sc implements Cloneable {
    private Cd code;

    /**
     * @param code the code to set
     */
    public void setCode(Cd code) {
        this.code = code;
    }

    /**
     * @return the code
     */
    public Cd getCode() {
        return code;
    }
}

