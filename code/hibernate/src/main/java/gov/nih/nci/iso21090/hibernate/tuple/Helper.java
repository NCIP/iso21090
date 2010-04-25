package gov.nih.nci.iso21090.hibernate.tuple;

import gov.nih.nci.iso21090.hibernate.node.ComplexNode;
import gov.nih.nci.iso21090.hibernate.node.Constant;
import gov.nih.nci.iso21090.hibernate.node.ConstantNode;
import gov.nih.nci.iso21090.hibernate.node.Node;
import gov.nih.nci.iso21090.DSet;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Helper class for the ConstantAndNullFlavorTuplizer.
 * 
 * @author patelsat
 *
 */
/**
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class Helper {

    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("IsoConstants.xml");

    /**
     * Returns the RootNode by reading the Spring configuration file.
     * 
     * @param dataTypeObject key in the file
     * @return RootNode instance or null
     */
    public static ComplexNode getComplexNodeBean(String dataTypeObject)    {
        ComplexNode complexNode = null;
        String[] abc = ctx.getBeanDefinitionNames();
        for (int i = 0; i < abc.length; i++) {
            if (dataTypeObject.startsWith(abc[i])) {
                complexNode = (ComplexNode) ctx.getBean(abc[i]);
            }
        }
        return complexNode;
    }


    /**
     * Traverses the complex node structure and invokes in the method to set the value.
     * @param currentNode node being processed
     * @param entity Entity in which the value is to be set
     * @param count counter
     * @throws NoSuchFieldException exception
     * @throws ClassNotFoundException exception
     * @throws IllegalAccessException exception
     * @throws InstantiationException exception
     * @throws InvocationTargetException exception
     * @throws NoSuchMethodException exception
     */
    @SuppressWarnings({"PMD.CyclomaticComplexity", "PMD.AvoidDeeplyNestedIfStmts", "PMD.CollapsibleIfStatements" })
    public static void traverseComplexNodeAndInvokeMethod(final Node currentNode, final Object entity, int count) 
        throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, 
        InstantiationException, InvocationTargetException, NoSuchMethodException {

        if (currentNode != null) {
            if (currentNode instanceof ComplexNode) {
                for (final Node innerNode : ((ComplexNode) currentNode).getInnerNodes()) {
                    if (innerNode instanceof ComplexNode) {
                        traverseComplexNodeAndInvokeMethod(innerNode, entity, count + 1);
                    } else {
                        String constantValue = ((ConstantNode) innerNode).getConstantValue();
                        if (((ConstantNode) innerNode).getInstance().getClass().isEnum()) {
                            Class clazz = ((ConstantNode) innerNode).getInstance().getClass();
                            invokeMethod(entity, Constant.SET_METHOD_PREFIX + clazz.getSimpleName(), clazz, 
                                    constantValue);
                        } else {
                            String methodName = (String) ((ConstantNode) innerNode).getName();
                            methodName = Constant.SET_METHOD_PREFIX
                                + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
                                
                            if (entity instanceof DSet) {
                                Class clzz = Class.forName(currentNode.getIsoClassName());

                                if (((DSet) entity).getItem().isEmpty()) {

                                    Method[] methods = clzz.getMethods();
                                    for (int j = 0; j < methods.length; j++) {
                                        if (methods[j].getName().equals(methodName)) {
                                            Method m = methods[j];
                                            Object objInstance = clzz.newInstance();
                                            m.invoke(objInstance, constantValue);
                                            ((DSet) entity).getItem().add(objInstance);
                                            break;
                                        }    
                                    }
                                } else {
                                    Iterator iterator = ((DSet) entity).getItem().iterator();
                                    while (iterator.hasNext()) {
                                        Object objInstance = clzz.newInstance();
                                        invokeMethod(objInstance, methodName, null, constantValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }    
    }
    

    /**
     * Invokes into the setter methos to set the value.
     * @param entity Entity in which the value is to be set
     * @param methodName method to be invoked
     * @param parameterTypeClass type of the paramter
     * @param parameterValue value of the parameter
     * @return the return value after invoking the method 
     * @throws NoSuchFieldException exception
     * @throws ClassNotFoundException exception
     * @throws IllegalAccessException exception
     * @throws InstantiationException exception
     * @throws InvocationTargetException exception
     * @throws NoSuchMethodException exception
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static Object invokeMethod(Object entity, String methodName, 
            Class parameterTypeClass, String parameterValue) 
            throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, 
            InstantiationException, InvocationTargetException, 
            NoSuchMethodException {
        Object returnObject = null;
        Method method = null;

        if (parameterTypeClass == null)    {
            Method[] methodArray = entity.getClass().getMethods();
            for (int i = 0; i < methodArray.length; i++) {
                if (methodArray[i].getName().equals(methodName)) {
                    method = methodArray[i];
                    break;
                }
            }
            if (method != null) {
                if (parameterValue == null) {
                    returnObject = method.invoke(entity);
                } else {
                    returnObject = method.invoke(entity, parameterValue);
                }
            }
        } else {

            method = entity.getClass().getMethod(methodName, parameterTypeClass);
            if (parameterTypeClass.isEnum()) {
                returnObject = method.invoke(entity, Enum.valueOf(parameterTypeClass, parameterValue));
            } else    {
                returnObject = method.invoke(entity, parameterValue);
            }
        }
        return returnObject;
    }
}
