package gov.nih.nci.iso21090;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso ED type.
 * @author lpower
 */
public class Ed extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Compression compression;
    private byte[] data;
    private St description;
    private String value;

    /**
     * @return the compression
     */
    public Compression getCompression() {
        return compression;
    }

    /**
     * @param compression the compression to set
     */
    public void setCompression(Compression compression) {
        this.compression = compression;
    }

    /**
     * @return the data
     */
    @SuppressWarnings("PMD.MethodReturnsInternalArray")
    public byte[] getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    @SuppressWarnings("PMD.ArrayIsStoredDirectly")
    public void setData(byte[] data) {
        this.data = data;
    }

    /**
     * @return the description
     */
    public St getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(St description) {
        this.description = description;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * Helper method that examines the data and compression fields together, and returns
     * clients the uncompressed data as a stream.  The only compressions supported now
     * are:
     * <ul>
     * <li>no compression
     * <li><code>GZ</code>
     * </ul>
     * Null data fields are returned as an InputSteam with nothing to read.
     * @return uncompressed data
     * @throws IOException if gzip stream is corrupted
     */
    public InputStream getDataUncompressed() throws IOException {
        byte[] myBytes = getData() == null ? new byte[] {} : getData();
        if (getCompression() == null) {
            return new ByteArrayInputStream(myBytes);
        }
        if (getCompression() == Compression.GZ) {
            return new GZIPInputStream(new ByteArrayInputStream(myBytes));
        }
        throw new IllegalArgumentException("Unsupported compression: " + getCompression());
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

        if (!(obj instanceof Ed)) {
            return false;
        }

        Ed x = (Ed) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getData(), x.getData())
            .append(this.getCompression(), x.getCompression())
            .append(this.getDescription(), x.getDescription())
            .append(this.getValue(), x.getValue())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getData())
            .append(this.getCompression())
            .append(this.getDescription())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Ed clone() {
        return (Ed) super.clone();
    }

}
