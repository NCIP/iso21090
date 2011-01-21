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
