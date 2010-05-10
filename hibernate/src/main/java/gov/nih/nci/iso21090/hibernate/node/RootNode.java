package gov.nih.nci.iso21090.hibernate.node;

/**
 * Root of the graph.
 * 
 * @author patelsat
 *
 */
public class RootNode extends ComplexNode {
    private String parentClassName;
    
    private String selfTableName;
    private String selfTableForeignKey;
    
    private String targetTableName;
    private String targetTablePrimaryKey;
    private String targetTableForeignKey;
    
    private String joinTableName;
    private String joinTableForeignKey;
    private String joinTableInverseKey;
    
    /**
     * Default constructor. 
     * 
     * @param name Name of the node.
     */
    public RootNode(String name) {
        super(name);
    }

    /**
     * @return name of the parent class.
     */
    public String getParentClassName() {
        return parentClassName;
    }

    /**
     * @param parentClassName name of the parent class.
     */
    public void setParentClassName(String parentClassName) {
        this.parentClassName = parentClassName;
    }

    /**
     * @return name of the table in which the node is mapped.
     */
    public String getSelfTableName() {
        return selfTableName;
    }

    /**
     * @param selfTableName name of the table in which the node is mapped.
     */
    public void setSelfTableName(String selfTableName) {
        this.selfTableName = selfTableName;
    }

    /**
     * @return foreign key of the table in which the node is mapped. 
     */
    public String getSelfTableForeignKey() {
        return selfTableForeignKey;
    }

    /**
     * @param selfTableForeignKey foreign key of the table in which the node is mapped.
     */
    public void setSelfTableForeignKey(String selfTableForeignKey)    {
        this.selfTableForeignKey = selfTableForeignKey;
    }

    /**
     * @return name of the table in which the datatype is mapped.
     */
    public String getTargetTableName() {
        return targetTableName;
    }

    /**
     * @param targetTableName name of the table in which the datatype is mapped.
     */
    public void setTargetTableName(String targetTableName) {
        this.targetTableName = targetTableName;
    }

    /**
     * @return primary key of the target table.
     */
    public String getTargetTablePrimaryKey() {
        return targetTablePrimaryKey;
    }

    /**
     * @param targetTablePrimaryKey primary key of the target table.
     */
    public void setTargetTablePrimaryKey(String targetTablePrimaryKey) {
        this.targetTablePrimaryKey = targetTablePrimaryKey;
    }

    /**
     * @return foreign key in the target table.
     */
    public String getTargetTableForeignKey() {
        return targetTableForeignKey;
    }

    /**
     * @param targetTableForeignKey foreign key in the target table.
     */
    public void setTargetTableForeignKey(String targetTableForeignKey) {
        this.targetTableForeignKey = targetTableForeignKey;
    }

    /**
     * @return name of the join table if any.
     */
    public String getJoinTableName() {
        return joinTableName;
    }

    /**
     * @param joinTableName name of the join table.
     */
    public void setJoinTableName(String joinTableName) {
        this.joinTableName = joinTableName;
    }

    /**
     * @return name of the foreign key in the join table.
     */
    public String getJoinTableForeignKey() {
        return joinTableForeignKey;
    }

    /**
     * @param joinTableForeignKey name of the foreign key in the join table.
     */
    public void setJoinTableForeignKey(String joinTableForeignKey) {
        this.joinTableForeignKey = joinTableForeignKey;
    }

    /**
     * @return name of the inverse key in the join table.
     */
    public String getJoinTableInverseKey() {
        return joinTableInverseKey;
    }

    /**
     * @param joinTableInverseKey name of the inverse key in the join table.
     */
    public void setJoinTableInverseKey(String joinTableInverseKey)    {
        this.joinTableInverseKey = joinTableInverseKey;
    }
}