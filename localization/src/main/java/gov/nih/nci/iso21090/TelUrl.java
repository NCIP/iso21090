package gov.nih.nci.iso21090;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Represents the iso TEL.URL data type.
 * No use codes
 * TEL.URL constrains TEL so that it must point to a locatable resource that returns binary content.
 * The URL scheme must be file, nfs, ftp, cid (for SOAP attachments), http, or https
 * @author lpower
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class TelUrl extends Tel implements Cloneable {

    private static final long serialVersionUID = 1L;
    /** scheme. */
    public static final String SCHEME_FILE = "file";
    /** scheme. */
    public static final String SCHEME_NFS = "nfs";
    /** scheme. */
    public static final String SCHEME_FTP = "ftp";
    /** scheme. */
    public static final String SCHEME_CID = "cid";
    /** scheme. */
    public static final String SCHEME_HTTP = "http";
    /** scheme. */
    public static final String SCHEME_HTTPS = "https";

    /** set of allowed URI schemes. */
    public static final List<String> SCHEMES = Collections.unmodifiableList(Arrays.asList(
        SCHEME_FILE,
        SCHEME_NFS,
        SCHEME_FTP,
        SCHEME_CID,
        SCHEME_HTTP,
        SCHEME_HTTPS));

    /** {@inheritDoc} */
    @Override
    protected List<String> getAllowedSchemes() {
        return SCHEMES;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TelUrl clone() {
        return (TelUrl) super.clone();
    }
}