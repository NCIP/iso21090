package gov.nih.nci.iso21090;

/**
 * Represents the iso enum.
 * @author lpower
 */
@SuppressWarnings("checkstyle.allowMissingJavadoc")
public enum AddressPartType {

    /** address line. An address line is for either 
     * <ul><li>an {@link #ADL additional locator},
     * <li>a {@link #DAL delivery address} 
     * <li> or a {@link #SAL street address}.</ul>
     * An address generally has only a 
     * {@link #DAL delivery address line} or a {@link #SAL street address line}, but not both */
    AL,
    /** additional locator.
    This can be a unit designator, such as apartment number, 
    suite number, or floor. There may be several unit designators 
    in an address (e.g., "3rd floor, Appt. 342"). This can also be a 
    designator pointing away from the location, rather than 
    specifying a smaller location within some larger one (e.g., 
    Dutch "t.o." means "opposite to" for house boats located 
    across the street facing houses) 
     */
    ADL,
    /**unit identifier. The number or name of a specific unit contained within a 
    building or complex, as assigned by that building or complex 
     */
    UNID,
    /**unit designator.
    Indicates the type of specific unit contained within a building or 
    complex. E.g. Apartment, Floor  */
    UNIT,
    /**delivery address line.
    A delivery address line is frequently used instead of breaking 
    out delivery mode, delivery installation, etc. An address 
    generally has only a delivery address line or a street address 
    line, but not both     */
    DAL,
    /**delivery installation type.
    Indicates the type of delivery installation (the facility to which 
    the mail will be delivered prior to final shipping via the delivery 
    mode.) Example: post office, letter carrier depot, community 
    mail center, station, etc 
     */
    DINST,
    /**delivery installation area.
    The location of the delivery installation, usually a town or city, 
    and is only required if the area is different from the 
    municipality. Area to which mail delivery service is provided 
    from any postal facility or service such as an individual letter 
    carrier, rural route, or postal route 
     */
    DINSTA,
    /**delivery installation qualifier.
    A number, letter or name identifying a delivery installation. 
    E.g., for Station A, the delivery installation qualifier would be 
    ‘A’ */
    DINSTQ,
    /**delivery mode.
    Indicates the type of service offered, method of delivery. For 
    example: post office box, rural route, general delivery, etc 
     */
    DMOD,
    /**delivery mode identifier.
    Represents the routing information such as a letter carrier 
    route number. It is the identifying number of the designator 
    (the box number or rural route number) 
     */
    DMODID,
    /**street address line.
    A street address line is frequently used instead of breaking out 
    build number, street name, street type, etc. An address 
    generally has only a delivery address line or a street address 
    line, but not both. 
    3*/
    SAL,
    /**building number.
    The number of a building, house or lot alongside the street. 
    Also known as "primary street number". This does not number 
    the street but rather the building 
     */
    BNR,
    /**building number numeric.
    The numeric portion of a building number  
     */
    BNN,
    /**building number suffix.
    Any alphabetic character, fraction or other text that may 
    appear after the numeric portion of a building number  
     */
    BNS,
    /** street name. 
     * The name of the street, including the type 
     */
    STR,
    /**street name base.
    The base name of a roadway or artery recognized by a 
    municipality (excluding street type and direction)  
     */
    STB,
    /**street type. 
    The designation given to the street. (e.g. Street, Avenue, 
    Crescent, etc.)  
     */
    STTYP,
    /**direction. (e.g., N, S, W, E)   
     */
    DIR,
    /**intersection. An intersection denotes that the actual address is located at 
    the intersection or two or more streets 
     */
    INT,
    /**care of.  The name of the party who will take receipt at the specified 
    address, and will take on responsibility for ensuring delivery to 
    the target recipient  
     */
    CAR,
    /**census tract. A geographic sub-unit delineated for demographic purposes  
     */
    CEN,
    /** country.
     */
    CNT,
    /**county or parish.
    A sub-unit of a state or province. (49 of the United States of 
    America use the term "county;" Louisiana uses the term 
    "parish")  
     */
    CPA,
    /**municipality. The name of the city, town, village, or other community or 
    delivery center  
     */
    CTY,
    /**delimiter. Delimiters are printed without framing white space. If no value 
    component is provided, the delimiter appears as a line break. 
     */
    DEL,
    /**post box. A numbered box located in a post station  
     */
    POB,
    /**precinct. A subsection of a municipality  
     */
    PRE,
    /**state or province.
    A sub-unit of a country with limited sovereignty in a federally 
    organized country 
     */
    STA,
    /**postal code. A postal code designating a region defined by the postal 
    service*/
    ZIP
}
