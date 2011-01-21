package gov.nih.nci.iso21090.reference.service;

import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.reference.convert.IiConverter;
import gov.nih.nci.iso21090.reference.convert.PersonConverter;
import gov.nih.nci.iso21090.reference.data.Person;
import gov.nih.nci.iso21090.reference.dto.PersonDTO;

/**
 * @author aevansel
 */
public class PersonEntityServiceBean implements PersonEntityServiceRemote {
    private PersonServiceBeanLocal personService = new PersonServiceBean();
    
    /**
     * {@inheritDoc}
     */
    public Ii createPerson(PersonDTO person) {
        Person p = PersonConverter.convertToEntity(person);
        return IiConverter.convertToIi(personService.create(p));
    }
    
    /**
     * {@inheritDoc}
     */
    public void deletePerson(PersonDTO person) {
        Person p = personService.getById(IiConverter.convertToLong(person.getId()));
        personService.delete(p);
    }
    
    /**
     * {@inheritDoc}
     */
    public PersonDTO getPerson(Ii id) {
        Person person = personService.getById(IiConverter.convertToLong(id));
        return PersonConverter.convertToDto(person);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updatePerson(PersonDTO person) {
        Person p = personService.getById(IiConverter.convertToLong(person.getId()));
        personService.update(p);
    }

}
