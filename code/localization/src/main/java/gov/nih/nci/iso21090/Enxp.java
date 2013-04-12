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

import java.io.Serializable;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso datatype.
 * @author lpower
 */
public final class Enxp implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String codeSystem;
    private String codeSystemVersion;
    private Set<EntityNamePartQualifier> qualifier;
    private EntityNamePartType type;
    private String value;
    private static final int HASH_CODE_SEED_1 = 17;
    private static final int HASH_CODE_SEED_2 = 71;

    /**
     * Const. that takes a type.
     * @param type to set
     */
    public Enxp(EntityNamePartType type) {
        this.type = type;
    }

    /**
     * Default const.
     */
    public Enxp() {
        // empty on purpose.
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the codeSystem
     */
    public String getCodeSystem() {
        return codeSystem;
    }

    /**
     * @param codeSystem the codeSystem to set
     */
    public void setCodeSystem(String codeSystem) {
        this.codeSystem = codeSystem;
    }

    /**
     * @return the codeSystemVersion
     */
    public String getCodeSystemVersion() {
        return codeSystemVersion;
    }

    /**
     * @param codeSystemVersion the codeSystemVersion to set
     */
    public void setCodeSystemVersion(String codeSystemVersion) {
        this.codeSystemVersion = codeSystemVersion;
    }

    /**
     * @return the qualifier
     */
    public Set<EntityNamePartQualifier> getQualifier() {
        return qualifier;
    }

    /**
     * @param qualifier the qualifier to set
     */
    public void setQualifier(Set<EntityNamePartQualifier> qualifier) {
        this.qualifier = qualifier;
    }

    /**
     * @return type of entity.
     */
    public EntityNamePartType getType() {
        return type;
    }

    /**
     * @param type to set
     */
    public void setType(EntityNamePartType type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(String value) {
        this.value = value;
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

        if (!(obj instanceof Enxp)) {
            return false;
        }

        Enxp x = (Enxp) obj;

        return new EqualsBuilder()
            .append(this.getCode(), x.getCode())
            .append(this.getType(), x.getType())
            .append(this.getValue(), x.getValue())
            .append(this.getCodeSystem(), x.getCodeSystem())
            .append(this.getCodeSystemVersion(), x.getCodeSystemVersion())
            .append(this.getQualifier(), x.getQualifier())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getCode())
            .append(this.getType())
            .append(this.getValue())
            .append(this.getCodeSystem())
            .append(this.getCodeSystemVersion())
            .append(this.getQualifier())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("PMD.ProperCloneImplementation")
    public Enxp clone() {

        Enxp snapshot = new Enxp();
        try {
            BeanUtils.copyProperties(snapshot, this);
        } catch (Exception e) {
            throw new IsoCloneException(e);
        }

        return snapshot;
    }

    /**
     * @return string representation of this class
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(" { ");
        sb.append("code: " + code);
        sb.append(" ; codeSystem: " + codeSystem);
        sb.append(" ; codeSystemVersion: " + codeSystemVersion);
        sb.append(" ; qualifier: " + qualifier);
        sb.append(" ; type: " + type);
        sb.append(" ; value: " + value);
        sb.append(" } ");

        return sb.toString();
    }

}
