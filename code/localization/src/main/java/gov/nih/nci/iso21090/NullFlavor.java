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


