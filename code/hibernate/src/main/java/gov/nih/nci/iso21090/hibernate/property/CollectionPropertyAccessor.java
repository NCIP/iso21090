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

package gov.nih.nci.iso21090.hibernate.property;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.hibernate.PropertyAccessException;
import org.hibernate.PropertyNotFoundException;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.Setter;
import org.hibernate.util.ReflectHelper;

/**
 * Accesses property values via a addPart method.
 * 
 * @author patelsat
 */
@SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.AvoidDuplicateLiterals" })
public class CollectionPropertyAccessor implements PropertyAccessor {

    private static final Log LOG  =  LogFactory.getLog(CollectionPropertyAccessor.class);

    /**
     * Inner class for setting property in the target object.
     * 
     * @author patelsat
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static final class CollectionPropertySetter implements Setter {
        private final Class clazz;
        private final transient Method method;
        private final String propertyName;

        /**
         * Default constructor.
         * 
         * @param clazz
         * @param method
         * @param propertyName
         */
        private CollectionPropertySetter(Class clazz, Method method, String propertyName) {
            this.clazz = clazz;
            this.method = method;
            this.propertyName = propertyName;
        }

        /**
         * Sets the value in the target object.
         * 
         * @param target target object in which value is to be set
         * @param value value to be set in the target object
         * @param factory SessionFactory object 
         */
        @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.AvoidCatchingNPE", "PMD.ExcessiveMethodLength" })
        public void set(Object target, Object value, SessionFactoryImplementor factory) {
            try {
                method.invoke(target, new Object[] {value});
            } catch (NullPointerException npe) {
                if (value == null && method.getParameterTypes()[0].isPrimitive()) {
                    throw new PropertyAccessException(
                            npe, 
                            "Null value was assigned to a property of primitive type", 
                            true, 
                            clazz, 
                            propertyName);
                } else {
                    throw new PropertyAccessException(
                            npe, 
                            "NullPointerException occurred while calling", 
                            true, 
                            clazz, 
                            propertyName);
                }
            } catch (InvocationTargetException ite) {
                throw new PropertyAccessException(
                        ite, 
                        "Exception occurred inside", 
                        true, 
                        clazz, 
                        propertyName);
            } catch (IllegalAccessException iae) {
                throw new PropertyAccessException(
                        iae, 
                        "IllegalAccessException occurred while calling", 
                        true, 
                        clazz, 
                        propertyName);
                //cannot occur
            } catch (IllegalArgumentException iae) {
                if (value == null && method.getParameterTypes()[0].isPrimitive()) {
                    throw new PropertyAccessException(
                            iae, 
                            "Null value was assigned to a property of primitive type", 
                            true, 
                            clazz, 
                            propertyName);
                } else {
                    LOG.error(
                            "IllegalArgumentException in class: " 
                            + clazz.getName()
                            + ", setter method of property: " 
                            + propertyName);
                    LOG.error(
                            "expected type: "  
                            + method.getParameterTypes()[0].getName() 
                            + ", actual value: " 
                            + (value == null ? null : value.getClass().getName()));
                    throw new PropertyAccessException(
                            iae, 
                            "IllegalArgumentException occurred while calling", 
                            true, 
                            clazz, 
                            propertyName);
                }
            }
        }

        /**
         * @return returns the method whioh is to be used to set the value
         */
        public Method getMethod() {
            return method;
        }

        /**
         * @return returns the name of the method
         */
        public String getMethodName() {
            return method.getName();
        }

        /**
         * @return returns the setter object
         */
        Object readResolve() {
            return createSetter(clazz, propertyName);
        }

        /** 
         * @return returns String representation
         */
        public String toString() {
            return "CollectionPropertySetter(" 
                + clazz.getName() 
                + '.' 
                + propertyName 
                + ')';
        }
    }

    /**
     * @param theClass Target class in which the value is to be set
     * @param propertyName Target property name
     * @return returns Setter class instance
     */
    public Setter getSetter(Class theClass, String propertyName) {
        return createSetter(theClass, propertyName);
    }
    
    /**
     * @param theClass Target class in which the value is to be set
     * @param propertyName Target property name
     * @return returns Setter class instance
     */
    private static Setter createSetter(Class theClass, String propertyName) {
        CollectionPropertySetter result  =  getSetterOrNull(theClass, propertyName);
        if (result == null) {
            throw new PropertyNotFoundException(
                    "Could not find a setter for property "  
                    + propertyName 
                    + " in class " 
                    + theClass.getName());
        }
        return result;
    }

    /**
     * @param theClass Target class in which the value is to be set
     * @param propertyName Target property name
     * @return returns Setter class instance
     */
    @SuppressWarnings("PMD.AccessorClassGeneration")
    private static CollectionPropertySetter getSetterOrNull(Class theClass, String propertyName) {

        if (theClass == Object.class || theClass == null) {
            return null;
        }

        Method method  =  setterMethod(theClass, propertyName);

        if (method != null) {
            if (!ReflectHelper.isPublic(theClass, method)) {
                method.setAccessible(true); 
            }
            return new CollectionPropertySetter(theClass, method, propertyName);
        } else {
            CollectionPropertySetter setter  =  getSetterOrNull(theClass.getSuperclass(), propertyName);
            if (setter == null) {
                Class[] interfaces = theClass.getInterfaces();
                for (int i = 0; setter == null && i < interfaces.length; i++) {
                    setter = getSetterOrNull(interfaces[i], propertyName);
                }
            }
            return setter;
        }
    }

    /**
     * @param theClass Target class in which the value is to be set
     * @param propertyName Target property name
     * @return returns setter method instance
     */
    @SuppressWarnings("PMD.UnusedFormalParameter")    
    private static Method setterMethod(Class theClass, String propertyName) {

        //CollectionPropertyGetter getter  =  getGetterOrNull(theClass, propertyName);
        //Class returnType  =  (getter == null) ? null : getter.getReturnType();

        Method[] methods  =  theClass.getDeclaredMethods();
        Method potentialSetter  =  null;
        for (int i = 0; i < methods.length; i++) {
            String methodName  =  methods[i].getName();

            if ("addPart".equals(methodName) && methods[i].getParameterTypes().length == 1) {
                //String testStdMethod  =  Introspector.decapitalize(methodName.substring(3));
                //String testOldMethod  =  methodName.substring(3);
                //if (testStdMethod.equals(propertyName) || testOldMethod.equals(propertyName)) {
                    potentialSetter  =  methods[i];
                    //if (returnType == null || methods[i].getParameterTypes()[0].equals(returnType)) {
                    //    return potentialSetter;
                    //}
                //}
            }
        }
        return potentialSetter;
    }

    /**
     * @param theClass Target class from which the value is to be retrieved
     * @param propertyName Target property name
     * @return returns Getter class instance
     */
    public Getter getGetter(Class theClass, String propertyName) {
        return new CollectionPropertyGetter();
    }

    /**
     * A Getter which will always return null. It should not be called anyway.
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    private static class CollectionPropertyGetter implements Getter {

        /**
         * @param target target
         * @return always null
         */
        public Object get(Object target) {
            return null;
        }

        /**
         * @param target target object
         * @param map map of properties
         * @param session Hibernate Session object
         * @return always null
         */
        public Object getForInsert(Object target, Map map, SessionImplementor session) {
            return null;
        }

        /**
         * @return always returns Object.class
         */
        public Class getReturnType() {
            return Object.class;
        }

        /**
         * @return always null
         */
        public String getMethodName() {
            return null;
        }

        /**
         * @return always null
         */
        public Method getMethod() {
            return null;
        }

    }
}
