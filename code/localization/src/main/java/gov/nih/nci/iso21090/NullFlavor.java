package gov.nih.nci.iso21090;

/**
 * Represents the iso NullFlavor type.
 * @author lpower
 */
public enum NullFlavor {

    /** no information. */
    NI("No Information"),
    /** invalid. */
    INV("Invalid"),
    /** other. */
    OTH ("Other"),
    /** negative infinity. */
    NINF ("Negative Infinity"),
    /** positive infinity. */
    PINF ("Positive Infinity"),
    /** unencoded. */
    UNC("Unencoded"),
    /** derived. */
    DER("Derived"),
    /** unknown. */
    UNK ("Unknown"),
    /** asked but unknown. */
    ASKU ("Asked but Unknown"),
    /** temporarily unavailable. */
    NAV ("Temporarily Unavailable"),
    /** sufficient quantity. */
    QS("Sufficient Quantity"),
    /** not asked. */
    NASK ("Not Asked"),
    /** trace. */
    TRC ("Trace"),
    /** masked. */
    MSK ("Masked"),
    /** not applicable. */
    NA ("Not Applicable");

   private String description;

   /**
    * @param description description to set
    */
   private NullFlavor(String description) {
       this.description = description;
   }

   /**
    * @return the description
    */
   public String getDescription() {
       return description;
   }



}


