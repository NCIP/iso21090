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

import static org.junit.Assert.assertEquals;
import gov.nih.nci.iso21090.AddressPartType;
import gov.nih.nci.iso21090.AdxpCnt;

import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author lpower
 */
public class AdxpCntTest {

    private AdxpCnt a;

    @Before
    public void init() {
        a = new AdxpCnt();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCodeBad() {
        a.setCode("China");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCodeBad2() {
        a.setCode("US");
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCodeBad3() {
        a.setCode("USa");
    }

    public void testCode() {
        a.setCode("USA");
    }

    @Test
    public void testAddressPartTypeCnt() {
        assertEquals(a.getType(), AddressPartType.CNT);
    }

}