package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.SIGNATURE type.
 * TODO Invariants checks
. No value, data, reference, integrity check, thumbnail, compression, language or
translations are allowed
. The media type must be text/xml  
 * @author Vijay Parmar
 */
public final class EdSignature extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdSignature clone() {
        return (EdSignature) super.clone();
    }
}
