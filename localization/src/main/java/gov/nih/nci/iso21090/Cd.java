package gov.nih.nci.iso21090;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the iso CD type.
 * @author lpower
 */
public final class Cd extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;

    private String code;
    private String codeSystem;
    private String codeSystemName;
    private String codeSystemVersion;
    private St displayName;
    private EdText originalText;

    /**
     * @return the originalText
     */
    public EdText getOriginalText() {
        return originalText;
    }
    /**
     * @param originalText the originalText to set
     */
    public void setOriginalText(EdText originalText) {
        this.originalText = originalText;
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
     * @return the codeSystemName
     */
    public String getCodeSystemName() {
        return codeSystemName;
    }
    /**
     * @param codeSystemName the codeSystemName to set
     */
    public void setCodeSystemName(String codeSystemName) {
        this.codeSystemName = codeSystemName;
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
     * @return the displayName
     */
    public St getDisplayName() {
        return displayName;
    }
    /**
     * @param displayName the displayName to set
     */
    public void setDisplayName(St displayName) {
        this.displayName = displayName;
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

        if (!(obj instanceof Cd)) {
            return false;
        }

        Cd x = (Cd) obj;

        return new EqualsBuilder()
            .appendSuper(super.equals(obj))
            .append(this.getCode(), x.getCode())
            .append(this.getCodeSystem(), x.getCodeSystem())
            .append(this.getCodeSystemName(), x.getCodeSystemName())
            .append(this.getCodeSystemVersion(), x.getCodeSystemVersion())
            .append(this.getDisplayName(), x.getDisplayName())
            .append(this.getOriginalText(), x.getOriginalText())
            .isEquals();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {

        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
            .append(this.getCode())
            .append(this.getCodeSystem())
            .append(this.getCodeSystemName())
            .append(this.getCodeSystemVersion())
            .append(this.getDisplayName())
            .append(this.getOriginalText())
            .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Cd clone() {
        return (Cd) super.clone();
    }
}
