package gov.nih.nci.iso21090.hibernate.tuple;

import java.util.Iterator;

import org.hibernate.mapping.Component;
import org.hibernate.mapping.Property;
import org.hibernate.tuple.component.PojoComponentTuplizer;


/**
 * Tuplizer for AD and EN family where the part attribute is mapped into 
 * the columns even though it is a collection.
 * 
 * @author patelsat
 *
 */
public class PartCollectionTuplizer extends PojoComponentTuplizer {
    
    private final Component component;

    private final String[] propertyNames;
    
    /**
     * Default constructor. 
     * 
     * @param parent Component for which the tuplizer is 
     */
    public PartCollectionTuplizer(Component parent) {
        super(parent);
        this.component = parent;
        propertyNames = new String[propertySpan];
        
        Iterator propertiesIterator = component.getPropertyIterator();
        
        int count = 0;
        while (propertiesIterator.hasNext()) {
            propertyNames[count++] = ((Property) propertiesIterator.next()).getName();
        }
    }

    /**
     * Sets the constant values in the collection elements prior to setting values in the 
     * target object. This is because the collection order after setting the values will 
     * not be possible to determine.
     * 
     * @param parent parent component object
     * @param values values to be set in the component
     */
    @Override
    public void setPropertyValues(Object parent, Object[] values) {
        
        //Set constant values first
        IsoConstantTuplizerHelper helper = new IsoConstantTuplizerHelper();
        for (int i = 0; i < propertySpan; i++) {
            helper.setConstantPartValues(parent, values[i], component.getRoleName(), propertyNames[i]);
        }
        
        super.setPropertyValues(parent, values);
    }
}
