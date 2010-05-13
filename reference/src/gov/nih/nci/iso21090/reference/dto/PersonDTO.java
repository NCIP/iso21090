package gov.nih.nci.iso21090.reference.dto;

import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.Ii;
import gov.nih.nci.iso21090.St;

import java.io.Serializable;

/**
 * DTO for the Person object.
 * @author aevansel
 */
public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Ii id;
    private EnPn name;
    private St favoriteColor;
    
    /**
     * @return the id
     */
    public Ii getId() {
        return id;
    }
    
    /**
     * @param id the Id to set
     */
    public void setId(Ii id) {
        this.id = id;
    }
    
    /**
     * @return the person's name
     */
    public EnPn getName() {
        return name;
    }
    
    /**
     * @param name the name to set
     */
    public void setName(EnPn name) {
        this.name = name;
    }
    
    /**
     * @return the person's favorite color
     */
    public St getFavoriteColor() {
        return favoriteColor;
    }
    
    /**
     * @param favoriteColor The favorite color to set
     */
    public void setFavoriteColor(St favoriteColor) {
        this.favoriteColor = favoriteColor;
    }
}
