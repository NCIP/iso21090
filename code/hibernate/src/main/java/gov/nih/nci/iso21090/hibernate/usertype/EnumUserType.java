//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright Science Applications International Corporation
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.hibernate.usertype;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;
 
import org.hibernate.MappingException;
import org.hibernate.usertype.ParameterizedType;
import org.hibernate.usertype.UserType;
 
/**
 * Enum Usertype class maps the Enum in the Java class to varchar in the database.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings({"PMD.AbstractNaming", "PMD.TooManyMethods" })
public class EnumUserType implements UserType, ParameterizedType, Serializable {
   
    /**
     * Enum class as set by the parameter enumClassName. 
     */
    private Class clazz = null;
   
   
    /**
     * @param params Properties object containing enumClassName property
     */
    public void setParameterValues(Properties params) {
        String enumClassName = params.getProperty("enumClassName");
        if (enumClassName == null) {
            throw new MappingException("enumClassName parameter not specified");
        }
      
        try {
            this.clazz = Class.forName(enumClassName);
        } catch (java.lang.ClassNotFoundException e) {
            throw new MappingException("enumClass " + enumClassName + " not found", e);
        }
   }
   
    
    /**
     * SQL type for the UserType. Varchar in this case. 
     */
    private static final int[] SQL_TYPES = {Types.VARCHAR};
    
    /**
     * @return returns SQL type of the UserType. Varchar in this case.
     */
    @SuppressWarnings("PMD.MethodReturnsInternalArray")
    public int[] sqlTypes() {
        return SQL_TYPES;
    }
 
    /**
     * @return returns the Enum class as set by the enumClassName parameter
     */
    public Class returnedClass() {
        return clazz;
    }
 
    /**
     * @param resultSet ResultSet object
     * @param names name of the columns being used by this UserType
     * @param owner Object in which the property is to be set
     * @return returns the Enum created from value obtained from the resultSet
     * @throws SQLException throws exception when error occurs in accessing the resultSet
     */
    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner)
                             throws SQLException {
        String name = resultSet.getString(names[0]);
        Object result = null;
        if (!resultSet.wasNull()) {
            result = Enum.valueOf(clazz, name);
        }
        return result;
    }
 
   
    /**
     * @param preparedStatement statement in which the value is to be set
     * @param value object that needs to be converted and set in the preparedStatement
     * @param index location of the parameter
     * @throws SQLException throws exception when error occurs in accessing the statement
     */
    public void nullSafeSet(PreparedStatement preparedStatement, Object value, int index) 
                          throws SQLException {
        if (null == value) {
            preparedStatement.setNull(index, Types.VARCHAR);
        } else {
            preparedStatement.setString(index, ((Enum) value).name());
        }
    }
 
    /**
     * @param value value to be copied
     * @return returns the same value which is passed in the parameter
     */
    public Object deepCopy(Object value) {
        return value;
    }
 
    /**
     * @return returns false
     */
    public boolean isMutable() {
        return false;
    }
 
    /**
     * @param cached copy of the cachec object
     * @param owner parent object
     * @return returns the cached object
     */
    public Object assemble(Serializable cached, Object owner) {
        return cached;
    }
 
    /**
     * @param value value that needs to be disassembled
     * @return returns the value parameter
     */
    public Serializable disassemble(Object value) {
        return (Serializable) value;
    }
 
    /**
     * @param original original object
     * @param target target object
     * @param owner parent object
     * @return returns original object
     */
    public Object replace(Object original, Object target, Object owner) {
        return original;
    }
    
    /**
     * @param x object whose hashcode is to be determined
     * @return hashcode of the object
     */
    public int hashCode(Object x) {
        return x.hashCode();
    }
    /**
     * @param x first parameter in comparison
     * @param y second parameter in the comparison
     * @return returns comparison result
     */
    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    public boolean equals(Object x, Object y) {
        if (x == y) {
            return true;
        }
        if (null == x || null == y) {
            return false;
        }
        return x.equals(y);
    }
}
