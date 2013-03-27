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

import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.list.PredicatedList;

/**
 * Represents the En ISO datatype.
 * @author lpower
 */
public class En extends Any implements Cloneable {

    private static final long serialVersionUID = 2L;

    private List<Enxp> part;
    private final List<Enxp> partsInternal;    
    private Predicate partRestriction;

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

        return super.equals(obj) && this.getPart().equals(x.getPart());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (!this.getPart().isEmpty()) {
            return this.getPart().hashCode();
        }
        return super.hashCode();
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
