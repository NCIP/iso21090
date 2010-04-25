package gov.nih.nci.iso21090.hibernate.node;

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
}