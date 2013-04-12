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

import org.apache.log4j.Logger;

import gov.nih.nci.iso21090.reference.data.Person;

public class PersonServiceBean implements PersonServiceBeanLocal {
    private static final Logger logger = Logger.getLogger(PersonServiceBean.class);
    
    /**
     * {@inheritDoc}
     */
    public Long create(Person person) {
        logger.info("Person successfully created.");
        return 1L;
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Person person) {
        logger.info("Person successfully deleted.");
    }
    
    /**
     * {@inheritDoc}
     */
    public Person getById(Long id) {
        logger.info("Person retrieved.");
        Person person = new Person();
        person.setId(id);
        return person;
    }
    
    /**
     * {@inheritDoc}
     */
    public void update(Person person) {
       logger.info("Person successfully updated.");
    }

}
