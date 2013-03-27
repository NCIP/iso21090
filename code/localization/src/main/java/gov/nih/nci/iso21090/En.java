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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.list.PredicatedList;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Represents the En ISO datatype.
 * @author lpower
 */
public class En extends Any implements Cloneable {

    private static final long serialVersionUID = 2L;

    private List<Enxp> part;
    private final List<Enxp> partsInternal;    
    private Predicate partRestriction;
    private Set<EntityNameUse> uses;

    /**
     * @return the uses
     */
    public Set<EntityNameUse> getUses() {
        if (this.uses == null) {
            setUses(new HashSet<EntityNameUse>());
        }
        return uses;
    }

    /**
     * @param uses the uses to set
     */
    public void setUses(Set<EntityNameUse> uses) {
        this.uses = uses;
    }

    /**
     * Default constructor.
     */
    public En() {
        partsInternal = new ArrayList<Enxp>();
    }

    /**
     * @param partRestriction a filter for disallowed values
     */
    protected En(Predicate partRestriction) {
        this();
        this.partRestriction = partRestriction;
    }

    /**
     * @return the part
     */
    @SuppressWarnings("unchecked")
    public List<Enxp> getPart() {
        if (part == null) {
            if (partRestriction != null) {
                part = PredicatedList.decorate(partsInternal, partRestriction);
            } else {
                part = partsInternal;
            }
        }
        return part;
    }
    
    /**
     * @param part the part list to set
     */
    public void setPart(List<Enxp> part) {
        this.part = part;
    }


    /**
     * @param partInstance ENXP part to be added to the part collection
     */
    public void addPart(Enxp partInstance) {
        if (part == null) {
            part = new ArrayList<Enxp>();
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

        if (!(obj instanceof En)) {
            return false;
        }

        En x = (En) obj;

        return super.equals(obj) && this.getPart().equals(x.getPart())
        && compareEntityNameUseSets(this.getUses(), x.getUses());
    }

    private boolean compareEntityNameUseSets(Set<EntityNameUse> currentUse, Set<EntityNameUse> compareUse) {
        if (currentUse == null && compareUse == null) {
            return true;
        } else if (currentUse != null && compareUse != null) {
            return currentUse.equals(compareUse);
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
        .append(super.hashCode())
        .toHashCode();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("PMD.CloneThrowsCloneNotSupportedException")
    @Override
    public En clone() {
        return (En) super.clone();
    }
}
