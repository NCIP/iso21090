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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import gov.nih.nci.iso21090.Cd;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.CD;

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
        
        Set<gov.nih.nci.iso21090.Cd> translations = input.getTranslations();
        if(translations!=null){
            List<CD> aList = new ArrayList<CD>();
            for(gov.nih.nci.iso21090.Cd cd: translations){
                aList.add(toXml(cd));
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



