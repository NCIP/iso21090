//============================================================================
// Copyright 5AM Solutions, Inc
// Copyright Ekagra Software Technologies Ltd.
// Copyright Guidewire Architecture
// Copyright The Ohio State University Research Foundation
// Copyright SAIC
//
// Distributed under the OSI-approved BSD 3-Clause License.
// See http://ncip.github.com/iso21090/LICENSE.txt for details.
//============================================================================

package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.DSet;
import gov.nih.nci.iso21090.Tel;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iso._21090.DSetTel;
import org.iso._21090.NullFlavor;
import org.iso._21090.TEL;

/**
 * Transforms sets of tels. Note that there is a small amount of asymmetry here w.r.t. empty or null sets:
 * <ol>
 * <li><b>DTO -> XML</b>: According to the ISO XSD, the XML can never be null. It must either have a NullFlavor set or
 * its items list must be non-empty. So we convert both null DTOs and empty-set DTOs to a non-null result.
 * <li><b>XML -> DTO</b>: Null or empty XML is converted to null.
 * <li>
 * </ol>
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public final class DSETTELTransformer extends AbstractTransformer<DSetTel, DSet<Tel>>
    implements Transformer<DSetTel, DSet<Tel>> {

    /**
     * Public singleton.
     */
    public static final DSETTELTransformer INSTANCE = new DSETTELTransformer();

    private DSETTELTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public DSetTel toXml(DSet<Tel> input) throws DtoTransformException {
    	DSetTel x = new DSetTel();
        if (input != null && input.getItem() != null) {
            Set<Tel> sItem = input.getItem();
            List<TEL> tItem = x.getItems();
            for (Tel element : sItem) {
                TEL cur = TELTransformer.INSTANCE.toXml(element);
                // XSD rule: all elements of set must be non-null
                if (!(cur == null || cur.getNullFlavor() != null)) {
                    tItem.add(cur);
                }
            }
        }

        if (x.getItems().isEmpty()) {
            x.setNullFlavor(NullFlavor.NI);
        }
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public DSet<Tel> toDto(DSetTel input) throws DtoTransformException {
        if (input == null || input.getNullFlavor() != null) {
            return null;
        }
        DSet<Tel> d = new DSet<Tel>();
        d.setItem(new HashSet<Tel>());
        List<TEL> sItem = input.getItems();
        Set<Tel> tItem = d.getItem();
        for (TEL tel : sItem) {
            tItem.add(TELTransformer.INSTANCE.toDto(tel));
        }
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public DSetTel[] createXmlArray(int size) throws DtoTransformException {
        return new DSetTel[size];
    }
}
