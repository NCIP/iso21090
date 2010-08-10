package gov.nih.nci.iso21090;

/**
 * Represents iso enum UncertaintyType.
 * @author lpower
 */
public enum UncertaintyType {

    /** uniform. */
    U,
    /** normal (gaussian). */
    N,
    /** log-normal. */
    LN,
    /** ? (gamma) */
    G,
    /** exponential. */
    E,
    /** ? */
    X2,
    /** t (student). */
    T,
    /** f. */
    F,
    /** ? (beta) */
    B;
}
