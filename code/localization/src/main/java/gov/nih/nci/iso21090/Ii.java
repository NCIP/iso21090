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

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents iso data type II.
 * @author lpower
 */
public final class Ii extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private Boolean displayable;
    private String extension;
    private String identifierName;
    private IdentifierReliability reliability;
    private String root;
    private IdentifierScope scope;

    /**
     * @return the displayable
     */
    public Boolean getDisplayable() {
        return displayable;
    }

    /**
     * @param displayable the displayable to set
     */
    public void setDisplayable(Boolean displayable) {
        this.displayable = displayable;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * @return the identifierName
     */
    public String getIdentifierName() {
        return identifierName;
    }

    /**
     * @param identifierName the identifierName to set
     */
    public void setIdentifierName(String identifierName) {
        this.identifierName = identifierName;
    }

    /**
     * @return the reliability
     */
    public IdentifierReliability getReliability() {
        return reliability;
    }

    /**
     * @param reliability the reliability to set
     */
    public void setReliability(IdentifierReliability reliability) {
        this.reliability = reliability;
    }

    /**
     * @return the root
     */
    public String getRoot() {
        return root;
    }

    /**
     * @param root the root to set
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * @return the scope
     */
    public IdentifierScope getScope() {
        return scope;
    }

    /**
     * @param scope the scope to set
     */
    public void setScope(IdentifierScope scope) {
        this.scope = scope;
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

        if (!(obj instanceof Ii)) {
            return false;
        }

        Ii x = (Ii) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getDisplayable(), x.getDisplayable())
            .append(this.getExtension(), x.getExtension())
            .append(this.getIdentifierName(), x.getIdentifierName())
            .append(this.getReliability(), x.getReliability())
            .append(this.getRoot(), x.getRoot())
            .append(this.getScope(), x.getScope())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getDisplayable())
            .append(this.getExtension())
            .append(this.getIdentifierName())
            .append(this.getReliability())
            .append(this.getRoot())
            .append(this.getScope())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ii clone() {
        return (Ii) super.clone();
    }
}
