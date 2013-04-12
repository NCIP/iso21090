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

package gov.nih.nci.iso21090.reference.convert;

import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.NullFlavor;
import gov.nih.nci.iso21090.reference.data.Person;

/**
 * @author Scott Miller
 *
 */
public class PersonNameConverter {

    /**
     * Convert the person's name info to an enpn.
     *
     * @param person the person.
     * @return the enpn
     */
    public static final EnPn convertToEnPn(Person person) {
        if (person == null) {
            EnPn enpn = new EnPn();
            enpn.setNullFlavor(NullFlavor.NI);
            return enpn;
        }
        return PersonNameConverterUtil.convertToEnPn(person.getFirstName(), person.getMiddleName(),
                person.getLastName(), person.getPrefix(), person.getSuffix());
    }
}
