package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

//import org.iso._21090.AddressPartType;

/**
 * Transforms individual address parts.
 */
public final class AddressPartTypeTransformer 
    extends AbstractTransformer<org.iso._21090.AddressPartType, AddressPartType>
    implements Transformer<org.iso._21090.AddressPartType, AddressPartType> {
    /**
     * Public singleton.
     */

    public static final AddressPartTypeTransformer INSTANCE = new AddressPartTypeTransformer();

    private AddressPartTypeTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.AddressPartType toXml(AddressPartType input) {
        if (input == null) {
            return null;
        }
        return org.iso._21090.AddressPartType.fromValue(input.name());
    }

    /**
     * {@inheritDoc}
     */
    public AddressPartType toDto(org.iso._21090.AddressPartType input) {
        if (input == null) {
            return null;
        }
        return AddressPartType.valueOf(input.name());
    }

    /**
     * {@inheritDoc}
     */
    public org.iso._21090.AddressPartType[] createXmlArray(int size)
            throws DtoTransformException {
        return new org.iso._21090.AddressPartType[size];
    }


}
