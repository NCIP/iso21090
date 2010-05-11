package gov.nih.nci.iso21090.grid.dto.transform.iso;

import gov.nih.nci.iso21090.Ts;
import gov.nih.nci.iso21090.grid.dto.transform.DtoTransformException;
import gov.nih.nci.iso21090.grid.dto.transform.Transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.iso._21090.TS;

/**
 * Transforms strings.
 *
 * @author mshestopalov
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
        SimpleDateFormat sDf = new SimpleDateFormat(FORMAT_STRING, Locale.getDefault());
        sDf.setLenient(false);
        TS x = transformBaseXml(input);

        Date v = input.getValue();
        if (v != null) {
            x.setValue(sDf.format(v));
        } else {
            x.setNullFlavor(NullFlavorTransformer.INSTANCE.toXml(input.getNullFlavor()));
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
        SimpleDateFormat sDf = new SimpleDateFormat(FORMAT_STRING, Locale.getDefault());
        sDf.setLenient(false);
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

        return d;
    }

    /**
     * {@inheritDoc}
     */
    public TS[] createXmlArray(int size) throws DtoTransformException {
        return new TS[size];
    }
}
