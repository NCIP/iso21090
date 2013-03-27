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
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.Pqr;
import gov.nih.nci.iso21090.St;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.util.HashSet;

import org.apache.commons.lang.math.NumberUtils;
import org.iso._21090.NullFlavor;
import org.iso._21090.PQ;
import org.iso._21090.PQR;
import org.iso._21090.ST;
import org.junit.Test;

/**
 *
 * @author max
 */public class PQTransformerTest extends AbstractTransformerTestBase<PQTransformer, org.iso._21090.PQ, Pq>{

        public Double VALUE_DOUBLE = new Double(12345);
        private static final Integer PRECISION = NumberUtils.INTEGER_ONE;
        private static final String UNIT = "test unit";
        private static final String CODE = "code";
        private static final String CODE_SYSTEM = "code system";
        private static final String VALUE = "value";

        public org.iso._21090.UncertaintyType xmlUncertaintyType = org.iso._21090.UncertaintyType.F;
        public gov.nih.nci.iso21090.UncertaintyType dtoUncertaintyType = gov.nih.nci.iso21090.UncertaintyType.F;    

        @Override
        public PQ makeXmlSimple() {
            PQ x = new PQ();
            x.setValue(VALUE_DOUBLE);
            x.setPrecision(PRECISION);
            x.setUnit(UNIT);

            PQ uncertainty = new PQ();
            uncertainty.setValue(VALUE_DOUBLE);
            uncertainty.setPrecision(PRECISION);
            uncertainty.setUnit(UNIT); 
            x.setUncertainty(uncertainty);

            x.setUncertaintyType(xmlUncertaintyType);

            PQR pqr = new PQR();
            pqr.setCode(CODE);
            pqr.setCodeSystem(CODE_SYSTEM);
            ST name = new ST();
            name.setValue(VALUE);
            pqr.setDisplayName(name);
            x.getTranslations().add(pqr);
            
            return x;
        }

        @Override
        public Pq makeDtoSimple() {
            Pq x = new Pq();
            x.setValue(VALUE_DOUBLE);
            x.setPrecision(PRECISION);
            x.setUnit(UNIT);

            Pq uncertainty = new Pq();
            uncertainty.setValue(VALUE_DOUBLE);
            uncertainty.setPrecision(PRECISION);
            uncertainty.setUnit(UNIT); 
            x.setUncertainty(uncertainty);

            x.setUncertaintyType(dtoUncertaintyType);
            
            x.setTranslations(new HashSet<Pqr>());
            Pqr pqr = new Pqr();
            pqr.setCode(CODE);
            pqr.setCodeSystem(CODE_SYSTEM);
            St name = new St();
            name.setValue(VALUE);
            pqr.setDisplayName(name);
            x.getTranslations().add(pqr);
            
            return x;
        }

        @Override
        public void verifyXmlSimple(PQ x) {
            assertEquals(VALUE_DOUBLE, x.getValue());
            assertEquals(UNIT, x.getUnit());
            assertEquals(PRECISION.intValue(), x.getPrecision());

            PQ uncertainty = (PQ)x.getUncertainty();
            assertEquals(VALUE_DOUBLE, uncertainty.getValue());
            assertEquals(UNIT, uncertainty.getUnit());
            assertEquals(PRECISION.intValue(), x.getPrecision());

            PQR pQr = x.getTranslations().get(0);
            assertEquals(CODE, pQr.getCode());
            assertEquals(CODE_SYSTEM, pQr.getCodeSystem());
            assertEquals(VALUE, pQr.getDisplayName().getValue()); 

            assertEquals(x.getUncertaintyType(), xmlUncertaintyType);             
        }

        @Override
        public void verifyDtoSimple(Pq x) {
            assertEquals(VALUE_DOUBLE, x.getValue());
            assertEquals(UNIT, x.getUnit());
            assertEquals(PRECISION, x.getPrecision());

            Pq uncertainty = (Pq)x.getUncertainty();
            assertEquals(VALUE_DOUBLE, uncertainty.getValue());
            assertEquals(UNIT, uncertainty.getUnit());
            assertEquals(PRECISION, uncertainty.getPrecision());

            assertEquals(x.getUncertaintyType(), dtoUncertaintyType); 

            for ( Pqr translation : x.getTranslations() ) {
                assertEquals(CODE, translation.getCode());
                assertEquals(CODE_SYSTEM, translation.getCodeSystem());
                assertEquals(VALUE, translation.getDisplayName().getValue()); 
            }

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
