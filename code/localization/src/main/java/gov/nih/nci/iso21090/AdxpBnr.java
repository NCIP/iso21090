package gov.nih.nci.iso21090;

/**
 * Represents the iso data type.
 * @author lpower
 *
 */
public class AdxpBnr extends Adxp {
    private static final long serialVersionUID = 1L;

    /**
     * default ctor.
     */
    public AdxpBnr() {
        super(AddressPartType.BNR);
    }
}
