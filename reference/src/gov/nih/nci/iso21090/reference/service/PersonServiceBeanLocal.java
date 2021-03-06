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

import gov.nih.nci.iso21090.reference.data.Person;

/**
 * @author aevansel
 */
public interface PersonServiceBeanLocal {
   
    /**
     * Creates a new person.
     * @param person The person to create
     * @return The identifier of the newly created person
     */
    public Long create(Person person);
    
    /**
     * Retrieves the person with the given id
     * @param id The id of the person to retrieve
     * @return The person with the given id or null if no such person exists
     */
    public Person getById(Long id);
    
    /**
     * Updates the given person
     * @param person The person to update
     */
    public void update(Person person);
    
    /**
     * Deletes the given person
     * @param person The person to delete
     */
    public void delete(Person person);
    
}
