//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090;



/**
 * Represents the iso BL Non Null data type.
 * @author lpower
 */
public final class BlNonNull extends Bl implements Cloneable {

    @Override
    public void setNullFlavor(NullFlavor nf) {
        throw new IllegalArgumentException("BL NON NULL does not support a null flavor.");
    }

    @Override
    public NullFlavor getNullFlavor() {
        return null;
    }

    @SuppressWarnings("PMD.ProperCloneImplementation")
    @Override
    public BlNonNull clone() {
        BlNonNull snapshot = null;
        try {
            snapshot = new BlNonNull();
            snapshot.setValue(this.getValue());
        } catch (Exception e) {
            throw new IsoCloneException(e);
        }

        return snapshot;
    }
}
