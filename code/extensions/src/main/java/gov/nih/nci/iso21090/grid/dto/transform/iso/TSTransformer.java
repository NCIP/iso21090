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

import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.iso._21090.TS;

/**
 * Transforms strings.
 *
 * @author mshestopalov, Dan Dumitru
 */
public final class TSTransformer extends QTYTransformer<TS, Ts> implements Transformer<TS, Ts> {

    /**
     * Public singleton.
     */
    public static final TSTransformer INSTANCE = new TSTransformer();
    /**
     * Format of iso data type value.
     */
    public static final String FORMAT_STRING = "yyyyMMddHHmmss.SSSSZ";

    private TSTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected TS newXml() {
        return new TS();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Ts newDto() {
        return new Ts();
    }

    /**
     * {@inheritDoc}
     */
    public TS toXml(Ts input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        SimpleDateFormat sDf = new SimpleDateFormat(FORMAT_STRING);
        sDf.setLenient(false);
        TS x = transformBaseXml(input);

        Date v = input.getValue();
        if (v != null) {
            x.setValue(sDf.format(v));
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }

        x.setOriginalText(EDTextTransformer.INSTANCE.toXml(input.getOriginalText()));

        Ts inputUncertainty = (Ts)(input.getUncertainty());
        if (inputUncertainty != null) {
        	org.iso._21090.TS xUncertainty = newXml();
            Date y = input.getValue();
            if (y != null) {
            	xUncertainty.setValue(sDf.format(y));
            } else {
            	xUncertainty.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
            }        	    
            x.setUncertainty(xUncertainty);
            if (input.getUncertaintyType() != null) {
                x.setUncertaintyType(org.iso._21090.UncertaintyType.valueOf(input.getUncertaintyType().name()));
            }
        }         
                
        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Ts toDto(TS input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Ts d = transformBaseDto(input);
        SimpleDateFormat sDf = new SimpleDateFormat(FORMAT_STRING);
        //sDf.setLenient(false);
        String v = input.getValue();
        if (v != null) {
            try {
                d.setValue(sDf.parse(v));
            } catch (ParseException pe) {
                throw new DtoTransformException(pe);
            }
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }

        d.setOriginalText(EDTextTransformer.INSTANCE.toDto(input.getOriginalText()));
        
        org.iso._21090.TS inputUncertainty = (org.iso._21090.TS)(input.getUncertainty());
        if (inputUncertainty != null) {
            Ts xUncertainty = newDto();
            String y = input.getValue();            
            if (y != null) {
                try {
                	xUncertainty.setValue(sDf.parse(y));
                } catch (ParseException pe) {
                    throw new DtoTransformException(pe);
                }
            } else {
            	xUncertainty.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
            }
            if (input.getUncertaintyType() != null) {
                d.setUncertaintyType(gov.nih.nci.iso21090.UncertaintyType.valueOf(input.getUncertaintyType().name()));            
            }
        } 
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public TS[] createXmlArray(int size) throws DtoTransformException {
        return new TS[size];
    }
}
