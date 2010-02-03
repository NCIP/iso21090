/**
 * The software subject to this notice and license includes both human readable
 * source code form and machine readable, binary, object code form. The ISO21090
 * Software was developed in conjunction with the National Cancer Institute
 * (NCI) by NCI employees and 5AM Solutions, Inc. (5AM). To the extent
 * government employees are authors, any rights in such works shall be subject
 * to Title 17 of the United States Code, section 105.
 *
 * This ISO21090 Software License (the License) is between NCI and You. You (or
 * Your) shall mean a person or an entity, and all other entities that control,
 * are controlled by, or are under common control with the entity. Control for
 * purposes of this definition means (i) the direct or indirect power to cause
 * the direction or management of such entity, whether by contract or otherwise,
 * or (ii) ownership of fifty percent (50%) or more of the outstanding shares,
 * or (iii) beneficial ownership of such entity.
 *
 * This License is granted provided that You agree to the conditions described
 * below. NCI grants You a non-exclusive, worldwide, perpetual, fully-paid-up,
 * no-charge, irrevocable, transferable and royalty-free right and license in
 * its rights in the ISO21090 Software to (i) use, install, access, operate,
 * execute, copy, modify, translate, market, publicly display, publicly perform,
 * and prepare derivative works of the ISO21090 Software; (ii) distribute and
 * have distributed to and by third parties the ISO21090 Software and any
 * modifications and derivative works thereof; and (iii) sublicense the
 * foregoing rights set out in (i) and (ii) to third parties, including the
 * right to license such rights to further third parties. For sake of clarity,
 * and not by way of limitation, NCI shall have no right of accounting or right
 * of payment from You or Your sub-licensees for the rights granted under this
 * License. This License is granted at no charge to You.
 *
 * Your redistributions of the source code for the Software must retain the
 * above copyright notice, this list of conditions and the disclaimer and
 * limitation of liability of Article 6, below. Your redistributions in object
 * code form must reproduce the above copyright notice, this list of conditions
 * and the disclaimer of Article 6 in the documentation and/or other materials
 * provided with the distribution, if any.
 *
 * Your end-user documentation included with the redistribution, if any, must
 * include the following acknowledgment: This product includes software
 * developed by 5AM and the National Cancer Institute. If You do not include
 * such end-user documentation, You shall include this acknowledgment in the
 * Software itself, wherever such third-party acknowledgments normally appear.
 *
 * You may not use the names "The National Cancer Institute", "NCI", or "5AM"
 * to endorse or promote products derived from this Software. This License does
 * not authorize You to use any trademarks, service marks, trade names, logos or
 * product names of either NCI or 5AM, except as required to comply with the
 * terms of this License.
 *
 * For sake of clarity, and not by way of limitation, You may incorporate this
 * Software into Your proprietary programs and into any third party proprietary
 * programs. However, if You incorporate the Software into third party
 * proprietary programs, You agree that You are solely responsible for obtaining
 * any permission from such third parties required to incorporate the Software
 * into such third party proprietary programs and for informing Your
 * sub-licensees, including without limitation Your end-users, of their
 * obligation to secure any required permissions from such third parties before
 * incorporating the Software into such third party proprietary software
 * programs. In the event that You fail to obtain such permissions, You agree
 * to indemnify NCI for any claims against NCI by such third parties, except to
 * the extent prohibited by law, resulting from Your failure to obtain such
 * permissions.
 *
 * For sake of clarity, and not by way of limitation, You may add Your own
 * copyright statement to Your modifications and to the derivative works, and
 * You may provide additional or different license terms and conditions in Your
 * sublicenses of modifications of the Software, or any derivative works of the
 * Software as a whole, provided Your use, reproduction, and distribution of the
 * Work otherwise complies with the conditions stated in this License.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES,
 * (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY,
 * NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. IN NO
 * EVENT SHALL THE NATIONAL CANCER INSTITUTE, 5AM SOLUTIONS, INC. OR THEIR
 * AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package gov.nih.nci.iso21090.grid.dto.transform.iso;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import java.math.BigDecimal;

import org.iso._21090.IVLPQ;
import org.iso._21090.PQ;
import org.junit.Test;

/**
 * Tests for PQ interval transformer.
 */
public class IVLPQTransformerTest extends AbstractTransformerTestBase<IVLPQTransformer, IVLPQ, Ivl<Pq>> {

    @Override
    public Ivl<Pq> makeDtoSimple() {
        Ivl<Pq> result = new Ivl<Pq>();
        result.setAny(getPq(1.1));
        result.setHigh(getPq(2.2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getPq(-1.1));
        result.setLowClosed(Boolean.FALSE);
        result.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
        result.setWidth(getPq(3.3));

        return result;
    }

    @Override
    public IVLPQ makeXmlSimple() {
        IVLPQ result = new IVLPQ();
        result.setHigh(getPQ(2.2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getPQ(-1.1));
        result.setLowClosed(Boolean.FALSE);
        result.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
        result.setWidth(getPQ(3.3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Pq> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Pq> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
        assertEquals(expected.getHigh(), x.getHigh());
        assertEquals(expected.getHighClosed(), x.getHighClosed());
        assertEquals(expected.getLow(), x.getLow());
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertEquals(expected.getOriginalText(), x.getOriginalText());
        assertEquals(expected.getWidth(), x.getWidth());
    }

    @Override
    public void verifyXmlSimple(IVLPQ x) {
        IVLPQ expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(expected.getOriginalText().getValue(), x.getOriginalText().getValue());
        assertEquals(((PQ) expected.getWidth()).getValue(), ((PQ) x.getWidth()).getValue());

    }

    private Pq getPq(double value) {
        Pq result = new PQTransformerTest().makeDtoSimple();
        result.setValue(new BigDecimal(value));
        return result;
    }

    private PQ getPQ(double value) {
        PQ result = new PQTransformerTest().makeXmlSimple();
        result.setValue(value);
        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
        // set Any with equal high and low
        IVLPQ input = new IVLPQ();
        input.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
        input.setWidth(getPQ(3.3));
        input.setHigh(getPQ(4.4));
        input.setLow(getPQ(4.4));
        Ivl<Pq> output = IVLPQTransformer.INSTANCE.toDto(input);
        assertEquals(output.getAny(), getPq(4.4));
        // set Any with some high and no low
        IVLPQ input2 = new IVLPQ();
        input2.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
        input2.setWidth(getPQ(3.3));
        input2.setHigh(getPQ(5.5));
        Ivl<Pq> output2 = IVLPQTransformer.INSTANCE.toDto(input2);
        assertEquals(output2.getAny(), getPq(5.5));
        // set Any with no high and some low
        IVLPQ input3 = new IVLPQ();
        input3.setOriginalText(new EDTextTransformerTest().makeXmlSimple());
        input3.setWidth(getPQ(3.3));
        input3.setHigh(getPQ(1.1));
        Ivl<Pq> output3 = IVLPQTransformer.INSTANCE.toDto(input3);
        assertEquals(output3.getAny(), getPq(1.1));
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
        // set Any with equal high and low
        Ivl<Pq> input = new Ivl<Pq>();
        input.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
        input.setAny(getPq(3.3));
        IVLPQ output = IVLPQTransformer.INSTANCE.toXml(input);
        assertEquals(output.getHigh().getValue(), getPQ(3.3).getValue());
        assertEquals(output.getLow().getValue(), getPQ(3.3).getValue());
        assertTrue(output.isHighClosed());
        assertTrue(output.isLowClosed());
        // set Any with some high and low not being equal
        Ivl<Pq> input2 = new Ivl<Pq>();
        input2.setOriginalText(new EDTextTransformerTest().makeDtoSimple());
        input2.setWidth(getPq(3.3));
        input2.setHigh(getPq(5.5));
        input2.setLow(getPq(1.1));
        input2.setAny(getPq(3.3));
        IVLPQ output2 = IVLPQTransformer.INSTANCE.toXml(input2);
        assertEquals(output2.getHigh().getValue(), getPQ(5.5).getValue());
        assertEquals(output2.getLow().getValue(), getPQ(1.1).getValue());
    }

}
