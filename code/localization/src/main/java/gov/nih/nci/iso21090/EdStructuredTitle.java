package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.STRUCTURED.TITLE type.
 * TODO Invariants checks
 
·  The media type must be text/x-hl7-title+xml
·  No value, data translations are allowed
·  The xml must be a valid instance of the structured title defined in Section
    
 * @author Vijay Parmar
 */
public final class EdStructuredTitle extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdStructuredTitle clone() {
        return (EdStructuredTitle) super.clone();
    }
}
