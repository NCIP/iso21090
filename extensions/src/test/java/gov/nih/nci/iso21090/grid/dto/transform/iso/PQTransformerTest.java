package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.UncertaintyType;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import java.math.BigDecimal;

import org.apache.commons.lang.math.NumberUtils;
import org.iso._21090.ED;
import org.iso._21090.EDText;
import org.iso._21090.NullFlavor;
import org.iso._21090.PQ;
import org.junit.Test;

/**
 *
 * @author max
 */public class PQTransformerTest extends AbstractTransformerTestBase<PQTransformer, PQ, Pq>{

        public Double VALUE_DOUBLE = new Double(12345);
        private static final String UNIT = "test unit";
        private static final Integer PRECISION = NumberUtils.INTEGER_ONE;

        @Override
        public PQ makeXmlSimple() {
            PQ x = new PQ();
            x.setValue(VALUE_DOUBLE);
            EDText edText = new EDText();
            ED ed = new EDTransformerTest().makeXmlSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            x.setOriginalText(edText);
            x.setPrecision(PRECISION);
            PQ uncert = new PQ();
            uncert.setValue(VALUE_DOUBLE);
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            uncert.setUnit(UNIT);
            uncert.setPrecision(PRECISION);
            x.setUncertainty(uncert);
            x.setUncertaintyType(org.iso._21090.UncertaintyType.B);
            x.setUnit(UNIT);
            return x;
        }

        @Override
        public Pq makeDtoSimple() {
            Pq x = new Pq();
            x.setValue(BigDecimal.valueOf(VALUE_DOUBLE));
            EdText edText = new EdText();
            Ed ed = new EDTransformerTest().makeDtoSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            x.setOriginalText(edText);
            x.setPrecision(PRECISION);
            Pq uncert = new Pq();
            uncert.setValue(BigDecimal.valueOf(VALUE_DOUBLE));
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            uncert.setUnit(UNIT);
            uncert.setPrecision(PRECISION);
            x.setUncertainty(uncert);
            x.setUncertaintyType(UncertaintyType.B);
            x.setUnit(UNIT);
            return x;
        }

        @Override
        public void verifyXmlSimple(PQ x) {
            assertEquals(VALUE_DOUBLE, x.getValue());
            ED ed = new EDTransformerTest().makeXmlSimple();
            assertEquals(ed.getValue(), x.getOriginalText().getValue());
            assertEquals(ed.getNullFlavor(), x.getOriginalText().getNullFlavor());
            assertEquals(VALUE_DOUBLE, ((PQ) x.getUncertainty()).getValue());
            assertNotNull(x.getUncertainty());
            assertEquals(org.iso._21090.UncertaintyType.B, x.getUncertaintyType());
            assertEquals(UNIT, x.getUnit());
            assertEquals(PRECISION.intValue(), x.getPrecision());
        }

        @Override
        public void verifyDtoSimple(Pq x) {
            assertEquals(new BigDecimal(VALUE_DOUBLE), x.getValue());
            EdText edText = new EdText();
            Ed ed = new EDTransformerTest().makeDtoSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            assertEquals(edText, x.getOriginalText());
            Pq uncert = new Pq();
            uncert.setValue(BigDecimal.valueOf(VALUE_DOUBLE));
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            uncert.setPrecision(PRECISION);
            uncert.setUnit(UNIT);
            assertEquals(uncert, x.getUncertainty());
            assertEquals(UncertaintyType.B, x.getUncertaintyType());
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
