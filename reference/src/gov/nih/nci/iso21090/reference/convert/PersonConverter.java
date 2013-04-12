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

package gov.nih.nci.iso21090.reference.convert;

import gov.nih.nci.iso21090.reference.data.Person;
import gov.nih.nci.iso21090.reference.dto.PersonDTO;

/**
 * @author aevansel
 */
public class PersonConverter {
    
    public static Person convertToEntity(PersonDTO personDto) {
        if (personDto == null) {
            return null;
        }
        
        Person person = new Person();
        person.setId(IiConverter.convertToLong(personDto.getId()));
        person.setFavoriteColor(StConverter.convertToString(personDto.getFavoriteColor()));
        EnPnConverter.convertToPersonName(personDto.getName(), person);
        return person;
    }
    
    public static PersonDTO convertToDto(Person person) {
        if (person == null) {
            return null;
        }
        PersonDTO dto = new PersonDTO();
        dto.setId(IiConverter.convertToIi(person.getId()));
        dto.setFavoriteColor(StConverter.convertToSt(person.getFavoriteColor()));
        dto.setName(PersonNameConverter.convertToEnPn(person));
        return dto;
    }
    
}
