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

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.IdentifierReliability;
import gov.nih.nci.iso21090.IdentifierScope;
import gov.nih.nci.iso21090.Ii;
//import gov.nih.nci.iso21090.extensions.Id;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.LinkedHashSet;
import java.util.Set;

import org.iso._21090.DSetII;

public class IdTransformerTest extends AbstractTransformerTestBase<IdTransformer, org.iso._21090.Ii, Ii> {

    /**
     * The identifier name for for Identified org.
     */
    public static final String IDENTIFIED_ORG_IDENTIFIER_NAME = "Identified org identifier";

    /**
     * The ii root value for Identified org.
     */
    public static final String IDENTIFIED_ORG_ROOT = "2.16.840.1.113883.3.26.4.4.6";

    @Override
    public Ii makeDtoSimple() {
        Ii id = new Ii();
        id.setRoot(IDENTIFIED_ORG_ROOT);
        id.setIdentifierName(IDENTIFIED_ORG_IDENTIFIER_NAME);
        id.setExtension("123");
        id.setReliability(IdentifierReliability.ISS);
        id.setScope(IdentifierScope.OBJ);

        return id;
    }

    @Override
    public org.iso._21090.Ii makeXmlSimple() {
    	org.iso._21090.Ii id = new org.iso._21090.Ii();
        id.setRoot(IDENTIFIED_ORG_ROOT);
        id.setIdentifierName(IDENTIFIED_ORG_IDENTIFIER_NAME);
        id.setExtension("123");
        id.setReliability(org.iso._21090.IdentifierReliability.ISS);
        id.setScope(org.iso._21090.IdentifierScope.OBJ);
        return id;
    }

    @Override
    public void verifyDtoSimple(Ii x) {
        assertEquals(x.getRoot(), IDENTIFIED_ORG_ROOT);
        assertEquals(x.getExtension(), "123");
        assertEquals(x.getIdentifierName(), IDENTIFIED_ORG_IDENTIFIER_NAME);
        assertEquals(IdentifierReliability.ISS, x.getReliability());
        assertEquals(IdentifierScope.OBJ, x.getScope());
    }

    @Override
    public void verifyXmlSimple(org.iso._21090.Ii x) {
        assertEquals(x.getRoot(), IDENTIFIED_ORG_ROOT);
        assertEquals(x.getExtension(), "123");
        assertEquals(x.getIdentifierName(), IDENTIFIED_ORG_IDENTIFIER_NAME);
        assertEquals(org.iso._21090.IdentifierReliability.ISS, x.getReliability());
        assertEquals(org.iso._21090.IdentifierScope.OBJ, x.getScope());
    }

    /**
     * Converts a single Ii into a DSet<Ii> containing just that Ii.
     *
     * @param id Ii to add to set
     * @return DSet<Ii> containing the id
     */
    public static DSet<Ii> convertIdToDSetIi(Ii id) {
        Set<Ii> identifiers = new LinkedHashSet<Ii>();
        identifiers.add(id);
        DSet<Ii> identifier = new DSet<Ii>();
        identifier.setItem(identifiers);
        return identifier;
    }

    public static DSetII convertIIToDSETII(org.iso._21090.Ii ii) {
    	DSetII identifier = new DSetII();
        identifier.getItems().add(ii);
        return identifier;
    }
}
