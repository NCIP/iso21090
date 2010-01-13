package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.En;
import gov.nih.nci.iso21090.EnOn;
import gov.nih.nci.iso21090.EnPn;
import gov.nih.nci.iso21090.EntityNamePartType;
import gov.nih.nci.iso21090.Enxp;
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
