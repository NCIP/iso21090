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
 * TS.DATETIME.FULL constrains TS.DATETIME so that it must contain reference to a
 * particular second with a timezone.
 * 
 * TODO Invariants 
. A timezone is required
·  A full time, including seconds and timezone, is required
 * @author Vijay Parmar
 */
public final class TsDatetimeFull extends TsDatetime implements Cloneable {

}
