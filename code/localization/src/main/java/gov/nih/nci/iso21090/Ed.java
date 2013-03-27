//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso ED type.
 *
 * TODO Add Invariants checks.
 * Either reference, data, value or xml must be provided if not null
 * Only one of reference, data, value or xml may be specified
 * An integrityCheckAlgorithm must be provided if an integrityCheck is provided
 * if a thumbnail is provided, it must not use a reference
 * if a thumbnail is provided, it must not have a thumbnail
 * Compression can only be specified if data is provided as a binary
 * mediaType cannot be null
 * if value is used, the mediaType is plain text
 * a character set must not be asserted for plain text or xml content (for plain text refer
 * to 6.7.4, and implicitly derived for the XML content)
 * translations may not contain translations
 *
 * @author lpower, Vijay Parmar, Daniel Dumitru
 */
public class Ed extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;  

    private Compression compression;
    private byte[] data;
    //This has been commented out to be consistent with ISO xsd
    //private St description;
    private String value;
    private String mediaType = EdMediaType.TEXT_PLAIN.getDescription();

    /**
     * @return the mediaType
     */
    public String getMediaType() {
        return mediaType;
    }

    /**
     * @param mediaType the mediaType to set
     */
    public void setMediaType(String mediaType) {
        if (mediaType == null) {
            throw new IllegalArgumentException("mediaType must not be null");
        }
        if ((this.value != null && this.value.length() > 0) 
                && !mediaType.equalsIgnoreCase(EdMediaType.TEXT_PLAIN.getDescription())) {
            throw new IllegalArgumentException("If value is set, mediaType must equal to " 
                + EdMediaType.TEXT_PLAIN.getDescription());
        }
        this.mediaType = mediaType;        
    }

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
     * @param value the value to set
     */
    public void setValue(String value) {
        if (!mediaType.equalsIgnoreCase(EdMediaType.TEXT_PLAIN.getDescription())) {
            throw new IllegalArgumentException("If value is set, mediaType must equal to " 
                + EdMediaType.TEXT_PLAIN.getDescription());
        }
        this.value = value;
        setMediaType(EdMediaType.TEXT_PLAIN.getDescription());
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

        //two nonNull values of type Ed are equal if and only if their mediaType and data are equal
        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getData(), x.getData())
            .append(this.getMediaType(), x.getMediaType())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getData())
            .append(this.getMediaType())            
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
