package gov.nih.nci.iso21090.hibernate.tuple;

import gov.nih.nci.iso21090.hibernate.node.ComplexNode;
import gov.nih.nci.iso21090.hibernate.node.Constant;

import gov.nih.nci.iso21090.NullFlavor;


import org.hibernate.HibernateException;
import org.hibernate.mapping.Component;
import org.hibernate.tuple.component.PojoComponentTuplizer;

/**
 * Sets constant and nullFlavor in the component.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class ConstantAndNullFlavorTuplizer extends PojoComponentTuplizer {
    private final String componentClassName;
    
    /**
     * Default constructor.
     * 
     * @param component component for which this tuplizer is instantiated.
     */
    public ConstantAndNullFlavorTuplizer(final Component component) {
        super(component);
        this.componentClassName = component.getRoleName();
    }
    

    /**
     * Sets the property values first followed by constant values.
     * 
     * @param entity entiry in which the values are to be set
     * @param values array of values to be set in the entity
     */
    @Override
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.AvoidDeeplyNestedIfStmts" })
    public void setPropertyValues(final Object entity, final Object[] values) {

        super.setPropertyValues(entity, values);

        int allValuesNullCount = 0;
        for (int i = 0; i < values.length; i++) {
            if (null == values[i]) {
                allValuesNullCount = allValuesNullCount + 1;
            }
        }
        
        ComplexNode complexNode = getComplexNodeFromHelper();
        if (complexNode != null) {
            try {
                if (values.length == allValuesNullCount) {
                    // 1.1 If all values from DB are null, then set Entity.nullFlavor = NullFlavor 
                    // 1.2 TODO Ensure Local and Global Constants are considered before applying any NullFlavor.
                     Helper.invokeMethod(entity, Constant.SET_METHOD_PREFIX + Constant.NULL_FLAVOR, NullFlavor.class,  
                            Constant.NULL_FLAVOR_NI);
                } else {
                    // 2. if Components Spring Bean has constants, then apply those constants.
                        Helper.traverseComplexNodeAndInvokeMethod(complexNode, entity, 0);
                }
            } catch (final Error e)    {
                throw new HibernateException(e);
            } catch (Exception e) {
                throw new HibernateException(e);
            }
        }
            
    }
    
    /**
     * Retrieves the RootNode to be processed from the constant config file. 
     * @return RootNode instance
     */
    protected ComplexNode getComplexNodeFromHelper() {
        try {
            return Helper.getComplexNodeBean(this.componentClassName);
        } catch (final Error e)    {
            throw new HibernateException(e);
        } catch (SecurityException e) {
            throw new HibernateException(e);
        } 
    }
    
}