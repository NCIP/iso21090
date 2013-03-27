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

import gov.nih.nci.iso21090.Ii;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.usertype.UserType;

/**
 * Ii Usertype class maps the Ii.extension in the Java class to varchar in the database.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.TooManyMethods")
public class IiUserType implements UserType, Serializable {

    /**
     * SQL type for this usertype.
     */
    private static final int[] SQL_TYPES = {Types.VARCHAR};

    /**
     * @return returns SQL type for the column to which the UserType is mapped
     */
    @SuppressWarnings("PMD.MethodReturnsInternalArray")
    public int[] sqlTypes() {
        return SQL_TYPES;
    }

    /**
     * @return returns the class of the instance being managed by the UserType
     */
    public Class returnedClass() {
        return Ii.class;
    }

    /**
     * @param x first object to compare
     * @param y second object to compare
     * @return comparison result
     * @throws HibernateException
     */
    @SuppressWarnings("PMD.CompareObjectsWithEquals")
    public boolean equals(Object x, Object y) {
        if (x == y) {
            return true;
        } else if (x == null || y == null) {
            return false;
        } else {
            return x.equals(y);
        }
    }

    /**
     * @param resultSet resultset object
     * @param names names of the columns in the resultset
     * @param owner parent object on which the value is to be set
     * @return returns URI object 
     * @throws SQLException throws exception when error occurs in accessing the resultSet
     */
    public Object nullSafeGet(ResultSet resultSet, String[] names, Object owner) 
            throws SQLException {
        String extension = resultSet.getString(names[0]);
        Ii ii = new Ii();
        ii.setExtension(extension);
        return ii;
    }

    /**
     * @param statement prepared statement object
     * @param value value being set in the statement
     * @param index location of the value in the statement
     * @throws SQLException throws exception when error occurs in accessing the statement
     */
    public void nullSafeSet(PreparedStatement statement, Object value, int index) 
            throws SQLException {
        if (value == null) {
            statement.setString(index, null);
        } else {
            Ii ii = (Ii) value;
            statement.setString(index, ii.getExtension());
        }
    }

    /**
     * @param value value being copied
     * @return copied value
     */
    public Object deepCopy(Object value) {
        if (value == null) {
            return null;
        }
        return ((Ii) value).clone();
    }

    /**
     * @return returns false
     */
    public boolean isMutable() {
        return false;
    }

    /**
     * @param arg0 object to be assembled
     * @param arg1 object to be assembled
     * @return returns assembled object
     */
    public Object assemble(Serializable arg0, Object arg1) {
        return (Serializable) deepCopy(arg0);
    }

    /**
     * @param arg0 object to be disassembled
     * @return returns disassembled object
     */
    public Serializable disassemble(Object arg0) {
        return (Serializable) deepCopy(arg0);
    }

    /**
     * @param arg0 object whose hashcode is to be determined 
     * @return returns 0
     */
    public int hashCode(Object arg0) {
        return 0;
    }

    /**
     * @param arg0 argument 0
     * @param arg1 argument 1
     * @param arg2 argument 2
     * @return returns arg0
     */
    public Object replace(Object arg0, Object arg1, Object arg2) {
        return arg0;
    }
}
