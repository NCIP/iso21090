//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso TEL data type.
 * @author lpower
 */
public class Tel extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private URI value;

    /**
     * the schemes that this Tel will allow (null to allow any).
     * @return null.
     */
    protected List<String> getAllowedSchemes() {
        return null;
    }

    /**
     * @return the value
     */
    public URI getValue() {
        return value;
    }
    /**
     * @param value the value to set
     */
    public void setValue(URI value) {

        if (!isAllowed(value, getAllowedSchemes())) {
            throw new IllegalArgumentException(value.getScheme());
        }
        this.value = value;
    }

    private static boolean isAllowed(URI uri, List<String> allowedSchemes) {
        return uri != null && isAllowed(uri.getScheme(), allowedSchemes);
    }

    /**
     * @param scheme the scheme part of a URI.
     * @param allowedSchemes the schemes that we consider to be valid.
     * @return true if allowedSchemes is null or if it contains scheme.
     */
    protected static boolean isAllowed(String scheme, List<String> allowedSchemes) {
        if (scheme == null) {
            return false;
        }
        return allowedSchemes == null || allowedSchemes.contains(scheme.toLowerCase(Locale.getDefault()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Tel)) {
            return false;
        }

        Tel x = (Tel) o;

        return new EqualsBuilder()
            .appendSuper(super.equals(o))
            .append(this.getValue(), x.getValue())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getValue())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public Tel clone() {
        return (Tel) super.clone();
    }
}
