package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.DOC type.
 * * TODO Invariants checks
 . mediaType.must be text/plain, text/html, text/xml or application/pdf  
 * @author Vijay Parmar
 */
public class EdDoc extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdDoc clone() {
        return (EdDoc) super.clone();
    }
}
