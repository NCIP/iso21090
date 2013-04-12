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
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

public class AddressPartTypeTransformerTest 
    extends AbstractTransformerTestBase<AddressPartTypeTransformer,org.iso._21090.AddressPartType, AddressPartType> {

	@Override
	public AddressPartType makeDtoSimple() {
		AddressPartType dto = AddressPartType.ZIP;
		return dto;
	}

	@Override
	public org.iso._21090.AddressPartType makeXmlSimple() {
		org.iso._21090.AddressPartType xml = org.iso._21090.AddressPartType.ZIP;
		return xml;
	}

	@Override
	public void verifyDtoSimple(AddressPartType x) {
		assertEquals(AddressPartType.ZIP, AddressPartType.ZIP);

	}

	@Override
	public void verifyXmlSimple(org.iso._21090.AddressPartType x) {
		assertEquals(org.iso._21090.AddressPartType.ZIP, org.iso._21090.AddressPartType.ZIP);

	}

}
