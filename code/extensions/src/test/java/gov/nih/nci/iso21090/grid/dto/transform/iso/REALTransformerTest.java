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

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.NullFlavor;
import org.iso._21090.PQ;
import org.junit.Test;

/**
 *
 * @author max
 */
public class REALTransformerTest extends AbstractTransformerTestBase<REALTransformer, org.iso._21090.Real, Real>{

        private static final Double VALUE = new Double(4.0);
        private static final Integer PRECISION = new Integer(1);
        public org.iso._21090.UncertaintyType xmlUncertaintyType = org.iso._21090.UncertaintyType.F;
        public gov.nih.nci.iso21090.UncertaintyType dtoUncertaintyType = gov.nih.nci.iso21090.UncertaintyType.F;         

        @Override
        public org.iso._21090.Real makeXmlSimple() {
        	org.iso._21090.Real x = new org.iso._21090.Real();
            x.setValue(VALUE);
            x.setPrecision(PRECISION);
            
            org.iso._21090.Real uncertainty = new org.iso._21090.Real();
            uncertainty.setValue(VALUE);
            uncertainty.setPrecision(PRECISION);
            x.setUncertainty(uncertainty);

            x.setUncertaintyType(xmlUncertaintyType);

            return x;
        }

        @Override
        public Real makeDtoSimple() {
            Real x = new Real();
            x.setValue(VALUE);
            x.setPrecision(PRECISION);           
            
            Real uncertainty = new Real();
            uncertainty.setValue(VALUE);
            uncertainty.setPrecision(PRECISION);
            x.setUncertainty(uncertainty);

            x.setUncertaintyType(dtoUncertaintyType);             
            
            return x;
        }

        @Override
        public void verifyXmlSimple(org.iso._21090.Real x) {
            assertEquals(null, VALUE, x.getValue(), 0);
            assertEquals(x.getUncertaintyType(), xmlUncertaintyType);
        }

        @Override
        public void verifyDtoSimple(Real x) {
            assertEquals(null, VALUE, x.getValue().doubleValue(), 0);
            assertEquals(x.getUncertaintyType(), dtoUncertaintyType);            
        }

        public org.iso._21090.Real makeXmlNullFlavored() {
        	org.iso._21090.Real x = new org.iso._21090.Real();
            x.setNullFlavor(NullFlavor.NI);
            return x;
        }

        public void verifyDtoNullFlavored(Real dto) {
            assertNull(dto.getValue());
            assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
        }

        @Test
        public void testRealNull() throws Exception {
            Real ts = new Real();
            ts.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
            org.iso._21090.Real result = REALTransformer.INSTANCE.toXml(ts);
            assertNotNull(result);
            assertEquals(NullFlavor.ASKU, result.getNullFlavor());
            assertNull(result.getValue());
        }
    }
