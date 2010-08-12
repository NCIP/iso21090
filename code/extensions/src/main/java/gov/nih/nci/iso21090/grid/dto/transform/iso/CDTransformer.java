package gov.nih.nci.iso21090.grid.dto.transform.iso;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.CD;
import org.iso._21090.XReference;
/**
 * Transforms coded data elements.
 */
public final class CDTransformer extends AbstractTransformer<CD, Cd>
    implements Transformer<CD, Cd> {

    /**
     * Singleton for client consumption.
     */
    public static final CDTransformer INSTANCE = new CDTransformer();

    private CDTransformer() {
    }


    /**
     * {@inheritDoc}
     */
    public Cd toDto(CD input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Cd res = new Cd();

        res.setCode(input.getCode());
        res.setCodeSystem(input.getCodeSystem());
        res.setCodeSystemName(input.getCodeSystemName());
        res.setCodeSystemVersion(input.getCodeSystemVersion());
        res.setDisplayName(STTransformer.INSTANCE.toDto(input.getDisplayName()));
        res.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        res.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        res.setValueSet(input.getValueSet());
        res.setValueSetVersion(input.getValueSetVersion());

        List<org.iso._21090.CodingRationale> inputCodingRationales = input.getCodingRationales();
        Set<gov.nih.nci.iso21090.CodingRationale> targetCodingRationales = new HashSet<gov.nih.nci.iso21090.CodingRationale>(inputCodingRationales.size());
        for (org.iso._21090.CodingRationale codingRationale : inputCodingRationales) {
            res.getCodingRationale().add(gov.nih.nci.iso21090.CodingRationale.valueOf(codingRationale.name()));
        }
        //TODO verify correctness of CDTransformer/XRef of CD
        if(((CD)input).getSource()!=null){
            res.setSource(CDTransformer.INSTANCE.toDto(   (CD)(     ((CD)input).getSource().getXref()) ));
        }

        if(input.getTranslations()!=null){
            for (org.iso._21090.CD cD: input.getTranslations()) {
                Cd cd = CDTransformer.INSTANCE.toDto(   (CD)(     ((CD)input).getSource().getXref()) );
                res.getTranslations().add(cd);
            }
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    public CD toXml(Cd input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        CD res = new CD();

        res.setCode(input.getCode());
        res.setCodeSystem(input.getCodeSystem());
        res.setCodeSystemName(input.getCodeSystemName());
        res.setCodeSystemVersion(input.getCodeSystemVersion());
        res.setDisplayName(STTransformer.INSTANCE.toXml(input.getDisplayName()));
        res.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));
        res.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        res.setValueSet(input.getValueSet());
        res.setValueSetVersion(input.getValueSetVersion());

        Set<gov.nih.nci.iso21090.CodingRationale> sourceUse = input.getCodingRationale();
        if (sourceUse != null) {
            for (gov.nih.nci.iso21090.CodingRationale codingRationale : sourceUse){
                res.getCodingRationales().add(org.iso._21090.CodingRationale.valueOf(codingRationale.name()));
            }
        }
        if(((Cd)input).getSource()!=null){
            XReference xreference = new XReference();
            xreference.setXref(CDTransformer.INSTANCE.toXml(((Cd)input).getSource()));
            res.setSource(xreference);
        }
        Set<gov.nih.nci.iso21090.Cd> translations = input.getTranslations();
        if(translations!=null){
            List aList = new ArrayList();
            for(gov.nih.nci.iso21090.Cd cd: translations){
                XReference xreference = new XReference();
                xreference.setXref(CDTransformer.INSTANCE.toXml(cd));
                aList.add(xreference);
            }
            if(aList.size()>0) res.getTranslations().addAll(aList);
        }

        return res;
    }

    /**
     * {@inheritDoc}
     */
    public CD[] createXmlArray(int size) throws DtoTransformException {
        return new CD[size];
    }
}



