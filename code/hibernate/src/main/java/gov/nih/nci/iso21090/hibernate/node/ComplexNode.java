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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Represents collection of other nodes in the graph.
 * @author patelsat
 *
 */
public class ComplexNode extends Node {

    /**
     * Internal collection of nodes.
     */
    private List<Node> innerNodes = new ArrayList<Node>();

    /**
     * Default constructor.
     * 
     * @param name Name of the node
     */
    public ComplexNode(String name) {
        setNodeType("complex");
        setName(name);
    }

    /**
     * @return Returns list of inner nodes
     */
    public List<Node> getInnerNodes() {
        return innerNodes;
    }

    /**
     * @param innerNodes List of inner nodes
     */
    public void setInnerNodes(List<Node> innerNodes) {
        this.innerNodes = innerNodes;
        Collections.sort(innerNodes, new NodeNameComparator());
    }
    
    /**
     * @param node Node to be added to innerNodes 
     */
    public void addInnerNode(Node node) {
        innerNodes.add(node);
        Collections.sort(innerNodes, new NodeNameComparator());
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

        ComplexNode x = (ComplexNode) obj;
        
        return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .isEquals();
        
    }

   /* public int compareTo(Node node) {
		
		ComplexNode x = (ComplexNode)o;
		
		return 0;
	}*/
}
