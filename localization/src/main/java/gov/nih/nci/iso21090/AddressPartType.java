/**
 * The software subject to this notice and license includes both human readable
 * source code form and machine readable, binary, object code form. The iso-datatypes
 * Software was developed in conjunction with the National Cancer Institute 
 * (NCI) by NCI employees and 5AM Solutions, Inc. (5AM). To the extent 
 * government employees are authors, any rights in such works shall be subject 
 * to Title 17 of the United States Code, section 105. 
 *
 * This iso-datatypes Software License (the License) is between NCI and You. You (or 
 * Your) shall mean a person or an entity, and all other entities that control, 
 * are controlled by, or are under common control with the entity. Control for 
 * purposes of this definition means (i) the direct or indirect power to cause 
 * the direction or management of such entity, whether by contract or otherwise,
 * or (ii) ownership of fifty percent (50%) or more of the outstanding shares, 
 * or (iii) beneficial ownership of such entity. 
 *
 * This License is granted provided that You agree to the conditions described 
 * below. NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up, 
 * no-charge, irrevocable, transferable and royalty-free right and license in 
 * its rights in the iso-datatypes Software to (i) use, install, access, operate, 
 * execute, copy, modify, translate, market, publicly display, publicly perform,
 * and prepare derivative works of the iso-datatypes Software; (ii) distribute and 
 * have distributed to and by third parties the iso-datatypes Software and any 
 * modifications and derivative works thereof; and (iii) sublicense the 
 * foregoing rights set out in (i) and (ii) to third parties, including the 
 * right to license such rights to further third parties. For sake of clarity, 
 * and not by way of limitation, NCI shall have no right of accounting or right 
 * of payment from You or Your sub-licensees for the rights granted under this 
 * License. This License is granted at no charge to You.
 *
 * Your redistributions of the source code for the Software must retain the 
 * above copyright notice, this list of conditions and the disclaimer and 
 * limitation of liability of Article 6, below. Your redistributions in object 
 * code form must reproduce the above copyright notice, this list of conditions 
 * and the disclaimer of Article 6 in the documentation and/or other materials 
 * provided with the distribution, if any. 
 *
 * Your end-user documentation included with the redistribution, if any, must 
 * include the following acknowledgment: This product includes software 
 * developed by 5AM and the National Cancer Institute. If You do not include 
 * such end-user documentation, You shall include this acknowledgment in the 
 * Software itself, wherever such third-party acknowledgments normally appear.
 *
 * You may not use the names "The National Cancer Institute", "NCI", or "5AM" 
 * to endorse or promote products derived from this Software. This License does 
 * not authorize You to use any trademarks, service marks, trade names, logos or
 * product names of either NCI or 5AM, except as required to comply with the 
 * terms of this License. 
 *
 * For sake of clarity, and not by way of limitation, You may incorporate this 
 * Software into Your proprietary programs and into any third party proprietary 
 * programs. However, if You incorporate the Software into third party 
 * proprietary programs, You agree that You are solely responsible for obtaining
 * any permission from such third parties required to incorporate the Software 
 * into such third party proprietary programs and for informing Your 
 * sub-licensees, including without limitation Your end-users, of their 
 * obligation to secure any required permissions from such third parties before 
 * incorporating the Software into such third party proprietary software 
 * programs. In the event that You fail to obtain such permissions, You agree 
 * to indemnify NCI for any claims against NCI by such third parties, except to 
 * the extent prohibited by law, resulting from Your failure to obtain such 
 * permissions. 
 *
 * For sake of clarity, and not by way of limitation, You may add Your own 
 * copyright statement to Your modifications and to the derivative works, and 
 * You may provide additional or different license terms and conditions in Your 
 * sublicenses of modifications of the Software, or any derivative works of the 
 * Software as a whole, provided Your use, reproduction, and distribution of the
 * Work otherwise complies with the conditions stated in this License.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, 
 * (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY, 
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO 
 * EVENT SHALL THE NATIONAL CANCER INSTITUTE, 5AM SOLUTIONS, INC. OR THEIR 
 * AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; 
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR 
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF 
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
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
    //INT, //This has been removed to be consistent with ISO xsd
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
