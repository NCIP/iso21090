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

package gov.nih.nci.iso21090.hibernate.node;


/**
 * Node in the graph.
 * 
 * @author patelsat
 *
 */
@SuppressWarnings("PMD.AbstractNaming")
public abstract class Node {
    /**
     * Name of the node.
     */
    private String name;
    
    /**
     * Type of the node.
     */
    private String nodeType;

    /**
     * Name of the corresponding ISO class.
     */
    private String isoClassName;
    
    /**
     * @return the isoClassName for the node.
     */
    public String getIsoClassName()    {
        return isoClassName;
    }

    /**
     * @param isoClassName the isoClassName for the node.
     */
    public void setIsoClassName(String isoClassName) {
        this.isoClassName = isoClassName;
    }
    
    /**
     * @return the name of the node.
     */
    public String getName()    {
        return name;
    }

    /**
     * @return the type of the node.
     */
    public String getNodeType()    {
        return nodeType;
    }

    /**
     * @param name Name of the node.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param nodeType type of the node.
     */
    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }
}