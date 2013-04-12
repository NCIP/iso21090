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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.apache.commons.lang.math.NumberUtils;
import org.iso._21090.NullFlavor;
import org.iso._21090.PQ;
import org.junit.Test;

/**
 *
 * @author max
 */public class PQTransformerTest extends AbstractTransformerTestBase<PQTransformer, org.iso._21090.PQ, Pq>{

        public Double VALUE_DOUBLE = new Double(12345);
        private static final String UNIT = "test unit";
        private static final Integer PRECISION = NumberUtils.INTEGER_ONE;

        @Override
        public PQ makeXmlSimple() {
            PQ x = new PQ();
            x.setValue(VALUE_DOUBLE);
            x.setPrecision(PRECISION);
            x.setUnit(UNIT);
            return x;
        }

        @Override
        public Pq makeDtoSimple() {
            Pq x = new Pq();
            x.setValue(VALUE_DOUBLE);
            x.setPrecision(PRECISION);
            x.setUnit(UNIT);
            return x;
        }

        @Override
        public void verifyXmlSimple(PQ x) {
            assertEquals(VALUE_DOUBLE, x.getValue());
            assertEquals(UNIT, x.getUnit());
            assertEquals(PRECISION.intValue(), x.getPrecision());
        }

        @Override
        public void verifyDtoSimple(Pq x) {
            assertEquals(VALUE_DOUBLE, x.getValue());
            assertEquals(UNIT, x.getUnit());
            assertEquals(PRECISION, x.getPrecision());
        }

        public PQ makeXmlNullFlavored() {
            PQ x = new PQ();
            x.setNullFlavor(NullFlavor.NI);
            return x;
        }

        public void verifyDtoNullFlavored(Pq dto) {
            assertNull(dto.getValue());
            assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
        }

        @Test
        public void testPqNull() throws Exception {
            Pq ts = new Pq();
            ts.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
            PQ result = PQTransformer.INSTANCE.toXml(ts);
            assertNotNull(result);
            assertEquals(NullFlavor.ASKU, result.getNullFlavor());
            assertNull(result.getValue());
        }
    }
