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


import gov.nih.nci.iso21090.Pq;
import gov.nih.nci.iso21090.Pqr;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;



import org.iso._21090.PQ;
import org.iso._21090.PQR;

/**
 * Transforms physical quantities.
 * @author mshestopalov
 */
public final class PQTransformer extends QTYTransformer<PQ, Pq>
    implements Transformer<PQ, Pq> {

    /**
     * Public singleton.
     */
    public static final PQTransformer INSTANCE = new PQTransformer();

    private PQTransformer() {

    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected PQ newXml() {
        return new PQ();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Pq newDto() {
        return new Pq();
    }

    /**
     * {@inheritDoc}
     */
    public PQ toXml(Pq input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        PQ x = transformBaseXml(input);
        Double v = input.getValue();
        if (v != null) {
            x.setValue(v);
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
        }
        
        if(input.getPrecision() != null)
        	x.setPrecision(input.getPrecision());
        else
        	x.setPrecision(0);
        
        x.setUnit(input.getUnit());
        //if (input.getValue() != null) {
        //    x.setValue(input.getValue().doubleValue());
        //}

        return x;
    }

    /**
     * {@inheritDoc}
     */
    public Pq toDto(PQ input) throws DtoTransformException {
        if (input == null) {
            return null;
        }
        Pq d = transformBaseDto(input);
        Double v = input.getValue();
        if (v != null) {
            d.setValue(v);
        } else {
            d.setNullFlavor(NullFlavorTransformer.INSTANCE.toDto(input.getNullFlavor()));
        }
        
       	d.setPrecision(input.getPrecision());
        
        d.setUnit(input.getUnit());
        //if (input.getValue() != null) {
        //    BigDecimal bd = new BigDecimal(input.getValue());
        //    d.setValue(bd);
        //}

        if(input.getTranslations()!=null){
       	 for (PQR pQR: input.getTranslations()) {
       		 Pqr pqr = PQRTransformer.INSTANCE.toDto(pQR);
       		 d.getTranslation().add(pqr);
       	 }
        }
        
        return d;
    }

    /**
     * {@inheritDoc}
     */
    public PQ[] createXmlArray(int size) throws DtoTransformException {
        return new PQ[size];
    }
}
