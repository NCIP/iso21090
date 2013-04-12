//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

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
