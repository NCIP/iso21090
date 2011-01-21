package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.TEXT type.
 * ED.TEXT constrains ED so that it may only contain plain text.
 * The content cannot be provided as data - it must be text.
 * Compression is not allowed
 *
 * @author lpower
 */
public final class EdText extends Ed implements Cloneable {

    private static final long serialVersionUID = 1L;

    /**
     * @param compression the compression to set - not allowed for ED.TEXT
     */
    @Override
    public void setCompression(Compression compression) {
        if (compression != null) {
            throw new IllegalArgumentException("compression not allowed in ED.TEXT");
        }
    }

    /**
     * @param  data data to set - not allowed for ED.TEXT
     */
    @Override
    public void setData(byte[] data) {
        if (data != null) {
            throw new IllegalArgumentException("data not allowed in ED.TEXT");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EdText clone() {
        return (EdText) super.clone();
    }
}
