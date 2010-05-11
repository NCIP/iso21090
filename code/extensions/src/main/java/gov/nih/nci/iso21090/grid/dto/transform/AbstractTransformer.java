package gov.nih.nci.iso21090.grid.dto.transform;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for transforming from XML representations to DTO representations, and vice versa.
 *
 * @param <XML> XML class to transform to/from.
 * @param <DTO> DTO class to transform to/from.
 */
public abstract class AbstractTransformer<XML, DTO> implements Transformer<XML, DTO> {

    /**
     * {@inheritDoc}
     */
    public XML[] convert(List<DTO> dtosList) throws DtoTransformException {
        if (dtosList == null) {
            return null;
        }
        XML[] result = createXmlArray(dtosList.size());
        int i = 0;
        for (DTO dto : dtosList) {
            result[i] = toXml(dto);
            i++;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    public List<DTO> convert(XML[] arrayList) throws DtoTransformException {
        if (arrayList == null) {
            return null;
        }

        List<DTO> result = null;
        result = new ArrayList<DTO>();
        for (XML xml : arrayList) {
            result.add(toDto(xml));
        }
        return result;
    }
}
