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

import gov.nih.nci.iso21090.Compression;
import gov.nih.nci.iso21090.Ed;
import gov.nih.nci.iso21090.EdMediaType;
import gov.nih.nci.iso21090.grid.dto.transform.AbstractTransformer;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import org.iso._21090.ED;

/**
 * Transforms strings.
 * @author mshestopalov
 */
public final class EDTransformer extends AbstractTransformer<ED, Ed>
    implements Transformer<ED, Ed> {

    /**
     * Public singleton.
     */
    public static final EDTransformer INSTANCE = new EDTransformer();

    private EDTransformer() {
    }

    /**
     * {@inheritDoc}
     */
    public ED toXml(Ed input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        ED x = new ED();
        String v = input.getValue();
        if (v != null) {
            x.setValue(v);

            // If 'value' is used, the mediatype is fixed to text/plain and the charset
            // must be consistent with the String Character Set
            x.setMediaType(EdMediaType.TEXT_PLAIN.getDescription());
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
            x.setMediaType(input.getMediaType());
        }
        if (input.getCompression() != null) {
            x.setCompression(org.iso._21090.Compression.valueOf(input.getCompression().name()));
        }
        x.setData(input.getData());
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Ed toDto(ED input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ed d = new Ed();
        String v = input.getValue();
        if (v != null) {
        	if (input.getMediaType() != null && !input.getMediaType().equalsIgnoreCase(EdMediaType.TEXT_PLAIN.getDescription())) {
                throw new DtoTransformException("If value is set, mediaType must equal to " 
                        + EdMediaType.TEXT_PLAIN.getDescription());
        	}
            d.setValue(v);
            d.setMediaType(EdMediaType.TEXT_PLAIN.getDescription());
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
            d.setMediaType(input.getMediaType());
        }

        if (input.getCompression() != null) {
            d.setCompression(Compression.valueOf(input.getCompression().name()));
        }
        d.setData(input.getData());
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public ED[] createXmlArray(int size) throws DtoTransformException {
        return new ED[size];
    }
}
