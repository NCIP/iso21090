package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.DOC.INLINE type.
 * * TODO Invariants checks
 * ED.DOC.INLINE constrains ED.DOC so that the document must not be provided by means of
a reference.  
 * @author Vijay Parmar
 */
public final class EdDocInline extends EdDoc implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdDocInline clone() {
        return (EdDocInline) super.clone();
    }
}
