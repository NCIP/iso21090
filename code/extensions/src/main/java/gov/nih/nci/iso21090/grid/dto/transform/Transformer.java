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

package gov.nih.nci.iso21090.grid.dto.transform;

import java.util.List;

/**
 * Interface for transforming from XML representations to DTO representations, and vice versa.
 *
 * @param <XML> XML class to transform to/from.
 * @param <DTO> DTO class to transform to/from.
 */
public interface Transformer<XML, DTO> {

    /**
     * Convert a DTO into a XML(JAXB) object.
     *
     * General Rules:
     * <ol>
     * <li> Null inputs produce null outputs
     * <li> NullFlavors are preserved across the transform
     * <li> Empty sets produce NullFlavor.NI DSET<?>.
     * <li> ocl constraints are not otherwise enforced.
     * </ol>
     *
     * @param input the DTO object to convert.  may be null.
     * @return the equivalent XML object.
     * @throws DtoTransformException if conversion fails.
     */
    XML toXml(DTO input) throws DtoTransformException;

    /**
     * Convert an XML(JAXB) object into a  DTO object.
     *
     * General Rules:
     * <ol>
     * <li>null inputs produce null outputs.
     * <li>nullfalvored non-sets (ie st, bl, etc.) produce NullFlavor outputs.
     * <li>nullflavored sets -> null output.
     * <li>values are copied.
     * </ol>
     *
     * @param input the XML object to convert.  may be null.
     * @return the equivalent DTO object.
     * @throws DtoTransformException if conversion fails.
     */
    DTO toDto(XML input) throws DtoTransformException;

    /**
     * Convert a list of DTO into an array of XML(JAXB) objects.
     *
     * General Rules for objects in the list:
     * <ol>
     * <li> Null inputs produce null outputs
     * <li> NullFlavors are preserved across the transform
     * <li> Empty sets produce NullFlavor.NI DSET<?>.
     * <li> ocl constraints are not otherwise enforced.
     * </ol>
     *
     * @param input the list of DTO object to convert.  may be null.
     * @return the equivalent XML object.
     * @throws DtoTransformException if conversion fails.
     */
    XML[] convert(List<DTO> input) throws DtoTransformException;

    /**
     * Convert an array of XML(JAXB) objects into a list of DTO object.
     *
     * General Rules for objects in the array:
     * <ol>
     * <li>null inputs produce null outputs.
     * <li>nullfalvored non-sets (ie st, bl, etc.) produce NullFlavor outputs.
     * <li>nullflavored sets -> null output.
     * <li>values are copied.
     * </ol>
     *
     * @param input the array of XML objects to convert.  may be null.
     * @return the equivalent list DTO objects.
     * @throws DtoTransformException if conversion fails.
     */
    List<DTO> convert(XML[] input) throws DtoTransformException;

    /**
     * Creates an array of XML(JAXB) objects with param size.
     *
     * @param size size of array.
     * @return the equivalent list XML objects.
     * @throws DtoTransformException if conversion fails.
     */
    XML[] createXmlArray(int size) throws DtoTransformException;
}
