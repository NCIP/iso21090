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

package gov.nih.nci.iso21090.hibernate.tuple;

import java.util.Iterator;

import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;


/**
 * Tuplizer for AD and EN family where the part attribute is mapped into 
 * the columns even though it is a collection.
 * 
 * @author patelsat
 *
 */
public class PartCollectionEntityTuplizer extends PojoEntityTuplizer {
    
    private final String[] propertyNames;
    
    /**
     * Default constructor. 
     * @param entityModel entity model class
     * @param persistentClass persistent class
     */
    public PartCollectionEntityTuplizer(EntityMetamodel entityModel, PersistentClass persistentClass) {
        super(entityModel, persistentClass);
        propertyNames = new String[propertySpan];
        
        Iterator propertiesIterator = persistentClass.getPropertyIterator();
        
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
        String entityName = this.getEntityName();
        String roleName = helper.convertEntityName(entityName) + "." + helper.convertPropertyName(entityName, null);
        
        for (int i = 0; i < propertySpan; i++) {
            helper.setConstantPartValues(parent, values[i], roleName, propertyNames[i]);
        }
        
        super.setPropertyValues(parent, values);
    }
}
