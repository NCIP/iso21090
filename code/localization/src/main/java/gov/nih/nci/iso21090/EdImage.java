package gov.nih.nci.iso21090;

/**
 * Represents the iso ED.TEXT type.
 * ED.TEXT constrains ED so that it may only contain images
 * The mediaType must start with "image/"
 * The content cannot be provided as a text or xml – it must be binary and/or reference
 *
 * @author lpower
 */
public final class EdImage extends Ed implements Cloneable {

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
    public EdImage clone() {
        return (EdImage) super.clone();
    }
}
