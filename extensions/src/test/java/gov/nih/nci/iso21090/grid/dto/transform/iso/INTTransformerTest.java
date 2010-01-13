package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdText;
import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.UncertaintyType;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.ED;
import org.iso._21090.EDText;
import org.iso._21090.INT;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 *
 * @author max
 */
public class INTTransformerTest extends AbstractTransformerTestBase<INTTransformer, INT, Int>{

        private static final Integer VALUE = 4;
        private static final Integer UNCERT = 5;

        @Override
        public INT makeXmlSimple() {
            INT x = new INT();
            x.setValue(VALUE);
            EDText edText = new EDText();
            ED ed = new EDTransformerTest().makeXmlSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            x.setOriginalText(edText);
            INT uncert = new INT();
            uncert.setValue(UNCERT);
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            x.setUncertainty(uncert);
            x.setUncertaintyType(org.iso._21090.UncertaintyType.B);
            return x;
        }

        @Override
        public Int makeDtoSimple() {
            Int x = new Int();
            x.setValue(VALUE);
            EdText edText = new EdText();
            Ed ed = new EDTransformerTest().makeDtoSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            x.setOriginalText(edText);
            Int uncert = new Int();
            uncert.setValue(UNCERT);
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            x.setUncertainty(uncert);
            x.setUncertaintyType(UncertaintyType.B);
            return x;
        }

        @Override
        public void verifyXmlSimple(INT x) {
            assertEquals(VALUE, x.getValue());
            ED ed = new EDTransformerTest().makeXmlSimple();
            assertEquals(ed.getValue(), x.getOriginalText().getValue());
            assertEquals(ed.getNullFlavor(), x.getOriginalText().getNullFlavor());
            assertEquals(UNCERT, ((INT) x.getUncertainty()).getValue());
            assertNotNull(x.getUncertainty());
            assertEquals(org.iso._21090.UncertaintyType.B, x.getUncertaintyType());
        }

        @Override
        public void verifyDtoSimple(Int x) {
            assertEquals(VALUE, x.getValue());
            EdText edText = new EdText();
            Ed ed = new EDTransformerTest().makeDtoSimple();
            edText.setValue(ed.getValue());
            edText.setNullFlavor(ed.getNullFlavor());
            assertEquals(edText, x.getOriginalText());
            Int uncert = new Int();
            uncert.setValue(UNCERT);
            uncert.setUncertainty(null);
            uncert.setUncertaintyType(null);
            assertEquals(uncert, x.getUncertainty());
            assertEquals(UncertaintyType.B, x.getUncertaintyType());
        }

        public INT makeXmlNullFlavored() {
            INT x = new INT();
            x.setNullFlavor(NullFlavor.NI);
            return x;
        }

        public void verifyDtoNullFlavored(Int dto) {
            assertNull(dto.getValue());
            assertEquals(gov.nih.nci.iso21090.NullFlavor.NI, dto.getNullFlavor());
        }

        @Test
        public void testIntNull() throws Exception {
            Int ts = new Int();
            ts.setNullFlavor(gov.nih.nci.iso21090.NullFlavor.ASKU);
            INT result = INTTransformer.INSTANCE.toXml(ts);
            assertNotNull(result);
            assertEquals(NullFlavor.ASKU, result.getNullFlavor());
            assertNull(result.getValue());
        }
    }
