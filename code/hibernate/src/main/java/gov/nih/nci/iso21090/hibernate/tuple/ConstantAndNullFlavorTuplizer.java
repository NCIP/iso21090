package gov.nih.nci.iso21090.hibernate.tuple;

import org.hibernate.mapping.Component;
import org.hibernate.tuple.component.PojoComponentTuplizer;

/**
 * Sets the constant and nullFlavor values in the target object by looking up values in the Spring based config file.
 * 
 * @author patelsat
 */
public class ConstantAndNullFlavorTuplizer extends PojoComponentTuplizer {

    /**
     * Default constructor.
     * 
     * @param component Component object for which the tuplizer is to be used
     */
    public ConstantAndNullFlavorTuplizer(Component component) {
        super(component);
    }
}