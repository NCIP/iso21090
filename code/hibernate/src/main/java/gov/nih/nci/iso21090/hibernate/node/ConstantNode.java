package gov.nih.nci.iso21090.hibernate.node;

import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * Constant value node in the graph.
 * 
 * @author patelsat
 *
 */
public class ConstantNode extends Node {
    /**
     * Constant value. 
     */
    private String constantValue;
    
    /**
     * Instance of the constant value. 
     */
    private Object instance;
    
    /**
     * Default constructor.
     * 
     * @param name Name of the node.
     */
    public ConstantNode(String name) {
        setNodeType("constant");
        setName(name);
    }

    /**
     * @return constantValue.
     */
    public String getConstantValue() {
        return constantValue;
    }

    /**
     * @param constantValue constantValue.
     */
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    /**
     * @return instance of constantValue.
     */
    public Object getInstance() {
        return instance;
    }

    /**
     * @param instance instance of constantValue.
     */
    public void setInstance(Object instance) {
        this.instance = instance;
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

        ConstantNode x = (ConstantNode) obj;
        
        return new EqualsBuilder()
        .appendSuper(super.equals(obj))
        .append(this.getConstantValue(), x.getConstantValue())
        .isEquals();
        
    }
    
   /* public int compareTo(Node node) {
    	
		ConstantNode x = (ConstantNode)o;		
		int comparison = this.constantValue.compareTo(x.constantValue);
		if ( comparison != 0) return comparison;
		
		return 0;
	}

    */
}