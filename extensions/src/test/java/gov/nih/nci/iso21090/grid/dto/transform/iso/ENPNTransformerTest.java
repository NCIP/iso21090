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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformerTestBase;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.iso.ENTransformer.ENPNTransformer;

import java.util.Iterator;

import org.iso._21090.ENPN;
import org.iso._21090.ENXP;
import org.junit.Test;

public class ENPNTransformerTest extends AbstractTransformerTestBase<ENPNTransformer, ENPN, EnPn> {

    @Override
    public EnPn makeDtoSimple() {
        EnPn dto = new EnPn();
        Enxp o = new Enxp(EntityNamePartType.FAM);
        dto.getPart().add(o);
        return dto;
    }

    @Override
    public ENPN makeXmlSimple() {
        ENPN xml = new ENPN();
        ENXP o = new ENXP();
        o.setCode("value");
        o.setType(org.iso._21090.EntityNamePartType.FAM);
        xml.getPart().add(o);
        return xml;
    }

    @Override
    public void verifyDtoSimple(EnPn x) {
        assertEquals(x.getPart().size(), 1);
        assertEquals(x.getPart().get(0).getType(), EntityNamePartType.FAM);

    }

    @Override
    public void verifyXmlSimple(ENPN x) {
        assertEquals(x.getPart().size(), 1);
        assertEquals(x.getPart().get(0).getType(), org.iso._21090.EntityNamePartType.FAM);
    }

    @Test
    public void testAllNullPartToDto() throws DtoTransformException {
        ENPN xml = new ENPN();
        ENXP o1 = new ENXP();
        o1.setCode("value1");
        xml.getPart().add(o1);
        ENXP o2 = new ENXP();
        o2.setCode("value2");
        xml.getPart().add(o2);
        ENXP o3 = new ENXP();
        o3.setCode("value3");
        xml.getPart().add(o3);
        ENXP o4 = new ENXP();
        o4.setCode("value4");
        xml.getPart().add(o4);
        ENXP o5 = new ENXP();
        o5.setCode("value5");
        xml.getPart().add(o5);
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);

        Iterator<Enxp> it = result.getPart().iterator();
        while(it.hasNext()) {
            Enxp item = it.next();
            if (it.hasNext()) {
                assertEquals(EntityNamePartType.GIV, item.getType());
            } else {
                assertEquals(EntityNamePartType.FAM, item.getType());
            }
        }
    }

    @Test
    public void testSomeNullPartToDto() throws DtoTransformException {
        ENPN xml = new ENPN();
        ENXP o1 = new ENXP();
        o1.setCode("value1");
        o1.setType(org.iso._21090.EntityNamePartType.PFX);
        xml.getPart().add(o1);
        ENXP o2 = new ENXP();
        o2.setCode("value2");
        o2.setType(org.iso._21090.EntityNamePartType.GIV);
        xml.getPart().add(o2);
        ENXP o3 = new ENXP();
        o3.setCode("value3");
        xml.getPart().add(o3);
        ENXP o4 = new ENXP();
        o4.setCode("value4");
        xml.getPart().add(o4);
        ENXP o5 = new ENXP();
        o5.setCode("value5");
        xml.getPart().add(o5);
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);

        assertEquals(EntityNamePartType.PFX, result.getPart().get(0).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(1).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(2).getType());
        assertEquals(EntityNamePartType.GIV, result.getPart().get(3).getType());
        assertEquals(EntityNamePartType.FAM, result.getPart().get(4).getType());
    }

    @Test
    public void testAllNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        Enxp o1 = new Enxp();
        o1.setCode("value1");
        dto.getPart().add(o1);
        Enxp o2 = new Enxp();
        o2.setCode("value2");
        dto.getPart().add(o2);
        Enxp o3 = new Enxp();
        o3.setCode("value3");
        dto.getPart().add(o3);
        Enxp o4 = new Enxp();
        o4.setCode("value4");
        dto.getPart().add(o4);
        Enxp o5 = new Enxp();
        o5.setCode("value5");
        dto.getPart().add(o5);
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);

        Iterator<ENXP> it = result.getPart().iterator();
        while(it.hasNext()) {
            ENXP item = it.next();
            if (it.hasNext()) {
                assertEquals(org.iso._21090.EntityNamePartType.GIV, item.getType());
            } else {
                assertEquals(org.iso._21090.EntityNamePartType.FAM, item.getType());
            }
        }
    }

    @Test
    public void testSomeNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        Enxp o1 = new Enxp();
        o1.setCode("value1");
        o1.setType(EntityNamePartType.PFX);
        dto.getPart().add(o1);
        Enxp o2 = new Enxp();
        o2.setCode("value2");
        o2.setType(EntityNamePartType.GIV);
        dto.getPart().add(o2);
        Enxp o3 = new Enxp();
        o3.setCode("value3");
        dto.getPart().add(o3);
        Enxp o4 = new Enxp();
        o4.setCode("value4");
        dto.getPart().add(o4);
        Enxp o5 = new Enxp();
        o5.setCode("value5");
        dto.getPart().add(o5);
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);

        assertEquals(org.iso._21090.EntityNamePartType.PFX, result.getPart().get(0).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(1).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(2).getType());
        assertEquals(org.iso._21090.EntityNamePartType.GIV, result.getPart().get(3).getType());
        assertEquals(org.iso._21090.EntityNamePartType.FAM, result.getPart().get(4).getType());
    }

    @Test
    public void testNullPartToXml() throws DtoTransformException {
        EnPn dto = new EnPn();
        ENPN result = ENPNTransformer.INSTANCE.toXml(dto);
        assertTrue(result.getPart().isEmpty());
    }

    @Test
    public void testNullPartToDto() throws DtoTransformException {
        ENPN xml = new ENPN();
        EnPn result = ENPNTransformer.INSTANCE.toDto(xml);
        assertTrue(result.getPart().isEmpty());
    }

}
