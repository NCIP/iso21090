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

package gov.nih.nci.iso21090;

/**
 * Represents collection of ANY.
 * @author Dan Dumitru
 * 
 * @param <T> Collection element type
 */
@SuppressWarnings({ "PMD.AbstractNaming", "PMD.AbstractClassWithoutAnyMethod" })
public abstract class Coll<T extends Any> extends Any implements Cloneable {

    private static final long serialVersionUID = 2L;

}
