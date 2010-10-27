package gov.nih.nci.iso21090;


/**
 * TS.DATETIME.FULL constrains TS.DATETIME so that it must contain reference to a
 * particular second with a timezone.
 * 
 * TODO Invariants 
. A timezone is required
·  A full time, including seconds and timezone, is required
 * @author Vijay Parmar
 */
public final class TsDatetimeFull extends TsDatetime implements Cloneable {

}
