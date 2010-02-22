package gov.nih.nci.iso21090.reference.service;

import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.grid.dto.transform.iso.IITransformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.IdTransformer;
import gov.nih.nci.iso21090.reference.dto.PersonDTO;
import gov.nih.nci.iso21090.reference.transform.PersonTransformer;

import java.rmi.RemoteException;

import org.apache.log4j.Logger;

/**  
 * @created by Introduce Toolkit version 1.3
 * 
 */
public class ISO21090ReferenceServiceImpl extends ISO21090ReferenceServiceImplBase {
    private static final Logger logger = Logger.getLogger(ISO21090ReferenceServiceImpl.class);
    private PersonEntityServiceRemote personService = new PersonEntityServiceBean();

    public ISO21090ReferenceServiceImpl() throws RemoteException {
        super();
    }

  public gov.nih.nci.iso21090.extensions.Id createPerson(gov.nih.nci.iso21090.reference.impl.Person person) throws RemoteException {
        try {
            PersonDTO personDto = PersonTransformer.INSTANCE.toDto(person);
            Ii id = personService.createPerson(personDto);
            return IdTransformer.INSTANCE.toXml(id);
        } catch (Exception e) {
            logger.error("Error creating person.", e);
            throw new RemoteException("Error creating person.", e);
        }
    }

  public gov.nih.nci.iso21090.reference.impl.Person getPersonById(gov.nih.nci.iso21090.extensions.Id id) throws RemoteException {
        try {
            Ii ii = IITransformer.INSTANCE.toDto(id);
            PersonDTO personDto = personService.getPerson(ii);
            return PersonTransformer.INSTANCE.toXml(personDto);
        } catch (Exception e) {
            logger.error("Error retrieving person.", e);
            throw new RemoteException("Error retrieving person.", e);
        }
    }

  public void updatePerson(gov.nih.nci.iso21090.reference.impl.Person person) throws RemoteException {
        try {
            PersonDTO personDto = PersonTransformer.INSTANCE.toDto(person);
            personService.updatePerson(personDto);
        } catch (Exception e) {
            logger.error("Error updating person.", e);
            throw new RemoteException("Error updating person.", e);
        }
    }

  public void deletePerson(gov.nih.nci.iso21090.reference.impl.Person person) throws RemoteException {
        try {
            PersonDTO personDto = PersonTransformer.INSTANCE.toDto(person);
            personService.deletePerson(personDto);
        } catch (Exception e) {
            logger.error("Error deleting person.", e);
            throw new RemoteException("Error deleting person.", e);
        }
    }

}

