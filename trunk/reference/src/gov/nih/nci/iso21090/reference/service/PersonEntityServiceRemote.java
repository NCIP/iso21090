package gov.nih.nci.iso21090.reference.service;

import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.reference.dto.PersonDTO;


/**
 * @author aevansel
 */
public interface PersonEntityServiceRemote {
    
    /**
     * API to get a person
     * @param id db id
     * @return The person dto
     */
    public PersonDTO getPerson(Ii id);
    
    /**
     * API to create a person
     * @param person person
     * @return db id
     */
    public Ii createPerson(PersonDTO person);
    
    /**
     * API to update a person
     * @param person the person dto tp update
     */
    public void updatePerson(PersonDTO person);
    
    /**
     * API to delete a person
     * @param person The person dto to delete
     */
    public void deletePerson(PersonDTO person);

}
