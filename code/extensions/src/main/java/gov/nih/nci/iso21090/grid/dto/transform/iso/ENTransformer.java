
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

import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.List;

import org.iso._21090.EN;
import org.iso._21090.ENON;
import org.iso._21090.ENPN;
import org.iso._21090.ENXP;

/**
 * Transformer for EN and subtypes.
 *
 * @param <ENXX> exact EN subtype.
 * @param <EnXx> associated DTO type.
 */
@SuppressWarnings({"PMD.AbstractNaming", "PMD.TooManyMethods" })
public abstract class ENTransformer<ENXX extends EN, EnXx extends En> extends AbstractTransformer<ENXX, EnXx>
    implements Transformer<ENXX, EnXx> {

    /**
     * Public singleton.
     */
    public static final ENONTransformer ENON_INSTANCE = ENONTransformer.INSTANCE;
    /**
     * Public singleton.
     */
    public static final ENPNTransformer ENPN_INSTANCE = ENPNTransformer.INSTANCE;

    private ENTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    public ENXX toXml(EnXx input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        ENXX d = newXml();
        copyToXml(input, d);
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public EnXx toDto(ENXX input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        EnXx d = newDto();
        copyToDto(input, d);
        return d;
    }

    /**
     * Helper for copy to dto.
     * @param source source
     * @param target target
     * @throws DtoTransformException transform exception.
     */
    protected void copyToDto(ENXX source, EnXx target) throws DtoTransformException {
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(source.getNullFlavor()));
        List<ENXP> sPart = source.getPart();
        if (sPart == null || sPart.isEmpty()) {
            return;
        }
        copyTypesToDto(source, target);
    }

    /**
     * Helper for copy part types to dto.
     * @param source source
     * @param target target
     * @throws DtoTransformException transform exception.
     */
    protected void copyTypesToDto(ENXX source, EnXx target) throws DtoTransformException {
        List<Enxp> tPart = target.getPart();
        for (ENXP enxp : source.getPart()) {
            tPart.add(ENXPTransformer.INSTANCE.toDto(enxp));
        }
    }

    /**
     * Helper for copy to xml.
     * @param source source
     * @param target target
     * @throws DtoTransformException transform exception.
     */
    protected void copyToXml(EnXx source, ENXX target) throws DtoTransformException {
        target.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(source.getNullFlavor()));
        List<Enxp> sPart = source.getPart();
        if (sPart == null || sPart.isEmpty()) {
            return;
        }
        copyTypesToXml(source, target);
    }

    /**
     * Helper for copy part types to xml.
     * @param source source
     * @param target target
     * @throws DtoTransformException transform exception.
     */
    protected void copyTypesToXml(EnXx source, ENXX target) throws DtoTransformException {
        List<ENXP> tPart = target.getPart();
        for (Enxp enxp : source.getPart()) {
            tPart.add(ENXPTransformer.INSTANCE.toXml(enxp));
        }
    }

    /**
     * @return newly constructed xml object.
     */
    protected abstract ENXX newXml();

    /**
     * @return newly constructed dto object.
     */
    protected abstract EnXx newDto();

    /**
     * Org name transformer.
     */
    public static final class ENONTransformer extends ENTransformer<ENON, EnOn> {

        /**
         * Public singleton.
         */
        public static final ENONTransformer INSTANCE = new ENONTransformer();

        private  ENONTransformer() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected ENON newXml() {
            return new ENON();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EnOn newDto() {
            return new EnOn();
        }

        /**
         * {@inheritDoc}
         */
        public ENON[] createXmlArray(int size) throws DtoTransformException {
            return new ENON[size];
        }
    }

    /**
     * Person name transformer.
     */
    public static final class ENPNTransformer extends ENTransformer<ENPN, EnPn> {

        /**
         * Public singleton.
         */
        public static final ENPNTransformer INSTANCE = new ENPNTransformer();

        private ENPNTransformer() {
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected ENPN newXml() {
            return new ENPN();
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected EnPn newDto() {
            return new EnPn();
        }

        /**
         * {@inheritDoc}
         */
        public ENPN[] createXmlArray(int size) throws DtoTransformException {
            return new ENPN[size];
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void copyTypesToDto(ENPN source, EnPn target) throws DtoTransformException {
            if (xmlAllTypesSet(source.getPart())) {
                super.copyTypesToDto(source, target);
            } else {
                List<Enxp> tPart = target.getPart();
                handleSomeXmlNullTypes(source.getPart(), tPart);
            }
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void copyTypesToXml(EnPn source, ENPN target) throws DtoTransformException {
            if (dtoAllTypesSet(source.getPart())) {
                super.copyTypesToXml(source, target);
            } else {
                List<ENXP> tPart = target.getPart();
                handleSomeDtoNullTypes(source.getPart(), tPart);
            }
        }

        private boolean xmlAllTypesSet(List<ENXP> source) {
            for (ENXP enxp : source) {
                if (enxp != null && enxp.getType() == null) {
                    return false;
                }
            }

            return true;
        }

        private boolean dtoAllTypesSet(List<Enxp> source) {
            for (Enxp enxp : source) {
                if (enxp != null && enxp.getType() == null) {
                    return false;
                }
            }

            return true;
        }

        private void handleSetType(Enxp result, boolean last, EntityNamePartType lastType) {
             if (last) {
                 result.setType(EntityNamePartType.FAM);
             } else {
                 result.setType(lastType);
             }
        }

        private void handleSetType(ENXP result, boolean last,
                org.iso._21090.EntityNamePartType lastType) {
            if (last) {
                result.setType(org.iso._21090.EntityNamePartType.FAM);
            } else {
                result.setType(lastType);
            }
       }

        private void handleSomeXmlNullTypes(List<ENXP> sPart, List<Enxp> tPart)
            throws DtoTransformException {
            EntityNamePartType lastType = EntityNamePartType.GIV;
            for (int count = 0; count < sPart.size(); count++) {
                Enxp result = ENXPTransformer.INSTANCE.toDto(sPart.get(count));
                if (result.getType() == null) {
                    handleSetType(result, count == sPart.size() - 1, lastType);
                } else {
                    lastType = result.getType();
                }

                tPart.add(result);
            }
        }

        private void handleSomeDtoNullTypes(List<Enxp> sPart, List<ENXP> tPart)
        throws DtoTransformException {
            org.iso._21090.EntityNamePartType lastType = org.iso._21090.EntityNamePartType.GIV;
        for (int count = 0; count < sPart.size(); count++) {
            ENXP result = ENXPTransformer.INSTANCE.toXml(sPart.get(count));
            if (result.getType() == null) {
                handleSetType(result, count == sPart.size() - 1, lastType);
            } else {
                lastType = result.getType();
            }

            tPart.add(result);
        }
    }
    };
}
