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
