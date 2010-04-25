package gov.nih.nci.iso21090.hibernate.node;

import java.util.ArrayList;
import java.util.List;

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
    }
    
    /**
     * @param node Node to be added to innerNodes 
     */
    public void addInnerNode(Node node) {
        innerNodes.add(node);
    }
}
