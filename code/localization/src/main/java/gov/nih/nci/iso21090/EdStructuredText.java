package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.STRUCTURED.TEXT type.
 * TODO Invariants checks
 the media type must be text/x-hl7-text+xml
·  No value, data translations are allowed
·  The xml must be a valid instance of the structured text defined in Section  
 * @author Vijay Parmar
 */
public final class EdStructuredText extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

 
    /**
     * {@inheritDoc}
     */
    @Override
    public EdStructuredText clone() {
        return (EdStructuredText) super.clone();
    }
}
