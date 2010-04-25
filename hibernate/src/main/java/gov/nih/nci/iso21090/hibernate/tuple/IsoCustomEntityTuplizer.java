package gov.nih.nci.iso21090.hibernate.tuple;

import gov.nih.nci.iso21090.Any;
import gov.nih.nci.iso21090.NullFlavor;

import java.lang.reflect.Method;

import org.hibernate.HibernateException;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

/**
 * Tuplizer responsible for setting nullFlavor at the class/entity level.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class IsoCustomEntityTuplizer extends PojoEntityTuplizer {

    /**
     * Default constructor.
     * @param arg0 entityMetaModel
     * @param arg1 persistentClass
     */
    public IsoCustomEntityTuplizer(EntityMetamodel arg0, PersistentClass arg1) {
        super(arg0, arg1);

    }

    /**
     * Sets the values of the properties. In our case the nullFlavor.
     * 
     * @param entity Name of the entity.
     * @param values values to be set.
     */
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.AvoidDeeplyNestedIfStmts" })
    public void setPropertyValues(final Object entity, final Object[] values) {
        
        super.setPropertyValues(entity, values);
        
        try {
            String[] propertyNames = this.getEntityMetamodel().getPropertyNames();
            for (int i = 0; i < values.length; i++) {

                if (values[i] == null) {
                    Class propertyTypeClass = this.getMappedClass().getDeclaredField(propertyNames[i]).getType();
                    if (propertyTypeClass.getSuperclass().getName().equals(Any.class.getName())) {
                        Object propertyObjInstance = propertyTypeClass.newInstance();
                        
                        Method[] methods = propertyTypeClass.getMethods();
                        for (int j = 0; j < methods.length; j++) {
                            if (methods[j].getName().equals("setNullFlavor")) {
                                Method m = methods[j];
                                m.invoke(propertyObjInstance, Enum.valueOf(NullFlavor.class, "NI"));
                                break;
                            }
                        }
                        //Set property with propertyObjInstance
                        String methodName = "set" + propertyNames[i].substring(0, 1).toUpperCase()
                                            + propertyNames[i].substring(1);
                        Method m2 = entity.getClass().getMethod(methodName, propertyTypeClass);
                        m2.invoke(entity, propertyObjInstance);
                    }
                }
            }
            
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }
}
