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

package gov.nih.nci.iso21090.hibernate.tuple;

import gov.nih.nci.iso21090.Any;

import org.hibernate.HibernateException;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.tuple.entity.EntityMetamodel;
import org.hibernate.tuple.entity.PojoEntityTuplizer;

/**
 * Tuplizer responsible for setting nullFlavor and other constants at the class/entity level.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class IsoCustomEntityTuplizer extends PojoEntityTuplizer {

    /**
     * Default constructor.
     * @param entityModel entityMetaModel
     * @param persistentClass persistentClass
     */
    public IsoCustomEntityTuplizer(EntityMetamodel entityModel, PersistentClass persistentClass) {
        super(entityModel, persistentClass);
    }

    /**
     * Sets the values of the properties. In our case the nullFlavor.
     * 
     * @param entity Name of the entity.
     * @param values values to be set.
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public void setPropertyValues(final Object entity, final Object[] values) {
        
        super.setPropertyValues(entity, values);
        
        try {
            String[] propertyNames = this.getEntityMetamodel().getPropertyNames();
            String entityName = this.getEntityMetamodel().getName();
            String identifierName = this.getEntityMetamodel().getIdentifierProperty().getName();

            IsoConstantTuplizerHelper helper = new IsoConstantTuplizerHelper();

            //Identifier can be null if the Tuplizer is for entity
            if (identifierName != null) {
                
                Object identifier = this.getIdentifier(entity);
            
                if (Any.class.isAssignableFrom(identifier.getClass())) {
                    helper.setConstantValues(entity, identifier, entityName, identifierName, false);
                }
            }
            for (int i = 0; i < values.length; i++) {

                Class propertyTypeClass = helper.findFieldInClass(this.getMappedClass(), 
                        propertyNames[i]).getType();
    
                if (Any.class.isAssignableFrom(propertyTypeClass)) {
                    helper.setConstantValues(entity, values[i], entityName, propertyNames[i], false);
                }
            }
            
        } catch (Exception e) {
            throw new HibernateException(e);
        }
    }
    

}
