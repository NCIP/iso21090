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
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.reference.data.Person;

import java.util.List;

/**
 * @author aevansel
 */
public class EnPnConverter {

    /**
     * converts the given enpn to the data members on the given person.
     * @param value the source iso person name.
     * @param person the destination person.
     */
    public static void convertToPersonName(EnPn value, Person person) {
        if (value == null) {
            return;
        }

        // set all name values to null prior to parsing
        person.setLastName(null);
        person.setFirstName(null);
        person.setMiddleName(null);
        person.setPrefix(null);
        person.setSuffix(null);

        if (value.getNullFlavor() == null) {
            processParts(value.getPart(), person);
        }
    }

    private static void processParts(List<Enxp> parts, Person person) {
        // for handling del we need to know the previous part in the list
        Enxp previousPart = null;
        // for handling del we need to know the type of the previous non-del part
        EntityNamePartType previousType = null;

        for (Enxp part : parts) {
            String delimiter = extractDelimiter(previousPart, previousType, part);
            processPart(part, person, delimiter);
            if (previousPart != null) {
                previousType = previousPart.getType();
            }
            previousPart = part;
        }
    }

    private static String extractDelimiter(Enxp previousPart, EntityNamePartType previousType, Enxp part) {
        String delimiter = " ";
        if (previousPart != null && EntityNamePartType.DEL.equals(previousPart.getType())) {
            delimiter = previousPart.getValue();
        }
        return delimiter;
    }

    private static void processPart(Enxp part, Person person, String delimiter) {
        if (EntityNamePartType.FAM == part.getType()) {
            person.setLastName(produceNewValue(person.getLastName(), part.getValue(), delimiter));
        } else if (EntityNamePartType.GIV == part.getType()) {
            if (person.getFirstName() == null) {
                person.setFirstName(part.getValue());
            } else {
                person.setMiddleName(produceNewValue(person.getMiddleName(), part.getValue(), delimiter));
            }
        } else if (EntityNamePartType.PFX == part.getType()) {
            person.setPrefix(produceNewValue(person.getPrefix(), part.getValue(), delimiter));
        } else if (EntityNamePartType.SFX == part.getType()) {
            person.setSuffix(produceNewValue(person.getSuffix(), part.getValue(), delimiter));
        }
    }

    private static String produceNewValue(String oldValue, String addition, String del) {
        if (oldValue == null) {
            return addition;
        }
        return oldValue + del + addition;
    }
}
