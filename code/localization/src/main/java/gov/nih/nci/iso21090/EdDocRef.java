package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.DOC.REF type.
 * TODO Invariants checks
 . ED.DOC.REF constrains ED.DOC so that the document must be provided by means of a
reference.
·  A reference is required  
 * @author Vijay Parmar
 */
public final class EdDocRef extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdDocRef clone() {
        return (EdDocRef) super.clone();
    }
}
