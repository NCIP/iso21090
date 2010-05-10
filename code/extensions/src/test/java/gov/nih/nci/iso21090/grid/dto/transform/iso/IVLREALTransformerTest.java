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
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.fail;

import gov.nih.nci.iso21090.Ivl;
import gov.nih.nci.iso21090.Real;
import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.iso._21090.IVLREAL;
import org.iso._21090.IVLTS;
import org.iso._21090.TS;
import org.junit.Test;

/**
 * Tests for timestamp interval transformer.
 */
public class IVLREALTransformerTest extends AbstractTransformerTestBase<IVLREALTransformer, IVLREAL, Ivl<Real>> {

    @Override
    public Ivl<Real> makeDtoSimple() {
        Ivl<Real> result = new Ivl<Real>();
        result.setAny(getReal(1));
        result.setHigh(getReal(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getReal(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getReal(3));

        return result;
    }

    @Override
    public IVLREAL makeXmlSimple() {
        IVLREAL result = new IVLREAL();
        result.setHigh(getREAL(2));
        result.setHighClosed(Boolean.TRUE);
        result.setLow(getREAL(-1));
        result.setLowClosed(Boolean.FALSE);
        result.setWidth(getREAL(3));

        return result;
    }

    @Override
    public void verifyDtoSimple(Ivl<Real> x) {
        // instead of doing expected.equals(x), we need to account for PO-1054
        Ivl<Real> expected = makeDtoSimple();
        assertNull(x.getAny()); // Proves PO-1054 is still around
        assertEquals(expected.getHigh(), x.getHigh());
        assertEquals(expected.getHighClosed(), x.getHighClosed());
        assertEquals(expected.getLow(), x.getLow());
        assertEquals(expected.getLowClosed(), x.getLowClosed());
        assertEquals(expected.getWidth(), x.getWidth());
    }

    @Override
    public void verifyXmlSimple(IVLREAL x) {
        IVLREAL expected = makeXmlSimple();
        assertEquals(expected.getHigh().getValue(), x.getHigh().getValue());
        assertEquals(expected.isHighClosed(), x.isHighClosed());
        assertEquals(expected.getLow().getValue(), x.getLow().getValue());
        assertEquals(expected.isLowClosed(), x.isLowClosed());
        assertEquals(((org.iso._21090.Real) expected.getWidth()).getValue(), ((org.iso._21090.Real) x.getWidth()).getValue());

    }

    private Real getReal(int value) {
        Real result = new REALTransformerTest().makeDtoSimple();
        result.setValue(new Double(value));

        return result;
    }

    private org.iso._21090.Real getREAL(int value) {
        org.iso._21090.Real result = new REALTransformerTest().makeXmlSimple();
        result.setValue(new Double(value));

        return result;
    }

    @Test
    public void testAnyXmlToDto() throws DtoTransformException {
         // set Any with equal high and low
         IVLREAL input = new IVLREAL();
         input.setWidth(getREAL(3));
         input.setHigh(getREAL(4));
         input.setLow(getREAL(4));
         Ivl<Real> output = IVLREALTransformer.INSTANCE.toDto(input);
         assertEquals(output.getAny(), getReal(4));
         // set Any with some high and no low
         IVLREAL input2 = new IVLREAL();
         input2.setWidth(getREAL(3));
         input2.setHigh(getREAL(5));
         Ivl<Real> output2 = IVLREALTransformer.INSTANCE.toDto(input2);
         assertEquals(output2.getAny(), getReal(5));
         // set Any with no high and some low
         IVLREAL input3 = new IVLREAL();
         input3.setWidth(getREAL(3));
         input3.setHigh(getREAL(1));
         Ivl<Real> output3 = IVLREALTransformer.INSTANCE.toDto(input3);
         assertEquals(output3.getAny(), getReal(1));
    }

    @Test
    public void testAnyDtoToXml() throws DtoTransformException {
         // set Any with equal high and low
         Ivl<Real> input = new Ivl<Real>();
         input.setAny(getReal(3));
         IVLREAL output = IVLREALTransformer.INSTANCE.toXml(input);
         assertEquals(output.getHigh().getValue(), getREAL(3).getValue());
         assertEquals(output.getLow().getValue(), getREAL(3).getValue());
         assertTrue(output.isHighClosed());
         assertTrue(output.isLowClosed());
         // set Any with some high and low not being equal
         Ivl<Real> input2 = new Ivl<Real>();
         input2.setWidth(getReal(3));
         input2.setHigh(getReal(5));
         input2.setLow(getReal(1));
         input2.setAny(getReal(3));
         IVLREAL output2 = IVLREALTransformer.INSTANCE.toXml(input2);
         assertEquals(output2.getHigh().getValue(), getREAL(5).getValue());
         assertEquals(output2.getLow().getValue(), getREAL(1).getValue());
    }

}
