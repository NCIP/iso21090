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

package gov.nih.nci.iso21090.reference.data;

import java.io.Serializable;


/**
 * Person
 * @author aevansel
 */
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String suffix;
    private String prefix;
    private String favoriteColor;
    

    /**
     * @return database id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id database id
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * @return first (given) name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return this.middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return last (family) name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return name prefix
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * @param prefix prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * @return name suffix
     */
    public String getSuffix() {
        return suffix;
    }

    /**
     * @param suffix suffix
     */
    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
    
    /**
     * @return favorite color
     */
    public String getFavoriteColor() {
        return this.favoriteColor;
    }

    /**
     * @param color the color to set
     */
    public void setFavoriteColor(String color) {
        this.favoriteColor = color;
    }
}
