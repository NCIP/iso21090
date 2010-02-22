package gov.nih.nci.iso21090.reference.transform;

import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.IITransformer;
import gov.nih.nci.iso21090.grid.dto.transform.iso.STTransformer;
import gov.nih.nci.iso21090.reference.impl.Person;
import gov.nih.nci.iso21090.reference.dto.PersonDTO;

public final class PersonTransformer extends AbstractTransformer<Person, PersonDTO> 
    implements Transformer<Person, PersonDTO> {
    
    /**
     * Public singleton.
     */
    public static final PersonTransformer INSTANCE = new PersonTransformer();
    
    private PersonTransformer() { }

    /**
     * {@inheritDoc}
     */
    public Person toXml(PersonDTO input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Person person = new Person();
        person.setId(IITransformer.INSTANCE.toXml(input.getId()));
        person.setName(ENTransformer.ENPN_INSTANCE.toXml(input.getName()));
        person.setFavoriteColor(STTransformer.INSTANCE.toXml(input.getFavoriteColor()));
        return person;
    }
    
    /**
     * {@inheritDoc}
     */
    public PersonDTO toDto(Person input) throws DtoTransformException {
        if(input == null) {
            return null;
        }
        PersonDTO dto = new PersonDTO();
        dto.setId(IITransformer.INSTANCE.toDto(input.getId()));
        dto.setName(ENTransformer.ENPN_INSTANCE.toDto(input.getName()));
        dto.setFavoriteColor(STTransformer.INSTANCE.toDto(input.getFavoriteColor()));
        return dto;
    }

    /**
     * {@inheritDoc}
     */
    public Person[] createXmlArray(int arg0) throws DtoTransformException {
        return new Person[arg0];
    }

}
