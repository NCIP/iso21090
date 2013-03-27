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

package gov.nih.nci.iso21090;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents iso data type AD.
 *
 * TODO invariants checks, equalsbuilder, etc.
 * @author lpower
 */
public final class Ad extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;
    // TODO Invariant must be applied - see COPPA ISO's from EA
    private List<Adxp> part;
    private Set<PostalAddressUse> uses;
    private QSet<Ts> useablePeriod;

    /**
     * @return the useablePeriod
     */
    public QSet<Ts> getUseablePeriod() {
        return useablePeriod;
    }

    /**
     * @param useablePeriod the useablePeriod to set
     */
    public void setUseablePeriod(QSet<Ts> useablePeriod) {
        this.useablePeriod = useablePeriod;
    }



    /**
     * @return the uses
     */
    public Set<PostalAddressUse> getUses() {
        return uses;
    }

    /**
     * @param uses the uses to set
     */
    public void setUses(Set<PostalAddressUse> uses) {
        this.uses = uses;
    }

    /**
     * @return the part
     */
    public List<Adxp> getPart() {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart(List<Adxp> part) {
        this.part = part;
    }


    /**
     * @param partInstance ADXP part to be added to the part collection
     */
    public void addPart(Adxp partInstance) {
        if (part == null) {
            part = new ArrayList<Adxp>();
        }
        if (partInstance != null) {
            part.add(partInstance);
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Ad)) {
            return false;
        }

        Ad x = (Ad) obj;

        return super.equals(obj) && comparePartsLists(this.getPart(), x.getPart())
        && compareUseLists(this.getUses(), x.getUses())
        && compareUseablePeriods(this.getUseablePeriod(), x.getUseablePeriod());

    }

    private boolean comparePartsLists(List<Adxp> currentList, List<Adxp> compareList) {
        if (currentList == null && compareList == null) {
            return true;
        } else if (currentList != null && compareList != null) {
            return currentList.equals(compareList);
        } else {
            return false;
        }
    }
    
    private boolean compareUseLists(Set<PostalAddressUse> currentList, Set<PostalAddressUse> compareList) {
        if (currentList == null && compareList == null) {
            return true;
        } else if (currentList != null && compareList != null) {
            return currentList.equals(compareList);
        } else {
            return false;
        }
    }   
    
    private boolean compareUseablePeriods(QSet<Ts> currentUseablePeriod, QSet<Ts> compareUseablePeriod) {
        if (currentUseablePeriod == null && compareUseablePeriod == null) {
            return true;
        } else if (currentUseablePeriod != null && compareUseablePeriod != null) {
            return currentUseablePeriod.equals(compareUseablePeriod);
        } else {
            return false;
        }
    }      


    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        
        return new HashCodeBuilder(HASH_CODE_SEED_1, HASH_CODE_SEED_2)
        .append(this.getPart())
        .append(this.getUses())
        .append(this.getUseablePeriod())  
        .append(super.hashCode())
        .toHashCode();
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ad clone() {

        Ad returnVal = (Ad) super.clone();
        if (this.getPart() != null) {
            returnVal.setPart(new ArrayList<Adxp>());

            try {
                for (Adxp tem : this.getPart()) {
                    returnVal.getPart().add(tem.clone());
                }
            } catch (Exception e) {
                throw new IsoCloneException(e);
            }
        }
        if (this.getUses() != null) {
            returnVal.setUses(this.getUses());
        } 
        if (this.getUses() != null) {
            returnVal.setUseablePeriod(this.getUseablePeriod());
        }         

        return returnVal;
    }
}
