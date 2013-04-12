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

package gov.nih.nci.iso21090.hibernate.node;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Node representing the database column mapping for sub attribute.
 * 
 * @author patelsat
 *
 */
public class SimpleNode extends Node {
    private String columnName;
    
    /**
     * Default constructor.
     * @param name name of the node.
     */
    public SimpleNode(String name) {
        setNodeType("simple");
        setName(name);
    }

    /**
     * @return name of the column.
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName name of the column.
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    
    
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Node)) {
            return false;
        }
        SimpleNode x = (SimpleNode) obj;
        
        return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .append(this.getColumnName(), x.getColumnName())
        .isEquals();   
    }
    
   /* public int compareTo(Object o) {

		SimpleNode x = (SimpleNode)o;
				
		 int comparison = this.columnName.compareTo(x.columnName);
		 if ( comparison != 0) return comparison;
		 

		return 0;
	}*/
}