package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import gov.nih.nci.iso21090.Int;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;

import org.iso._21090.INT;
import org.iso._21090.NullFlavor;
import org.junit.Test;

/**
 *
 * @author max
 */
public class INTTransformerTest extends AbstractTransformerTestBase<INTTransformer, INT, Int>{

        private static final Integer VALUE = 4;

        @Override
        public INT makeXmlSimple() {
            INT x = new INT();
            x.setValue(VALUE);
            return x;
        }

        @Override
        public Int makeDtoSimple() {
            Int x = new Int();
            x.setValue(VALUE);
            return x;
        }

        @Override
        public void verifyXmlSimple(INT x) {
            assertEquals(VALUE, x.getValue());
        }

        @Override
        public void verifyDtoSimple(Int x) {
            assertEquals(VALUE, x.getValue());
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
