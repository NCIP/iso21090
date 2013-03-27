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

package gov.nih.nci.iso21090.reference.client;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.iso._21090.II;

/**
 * Utility class for service *Client.java classes.
 * @author aevansel 
 */
public final class ClientUtils {

    private ClientUtils() {
        //no-op
    }

    /**
     * @param identifier the ISO II information
     */
    public static void print(II... identifier) {
        System.out.println(ToStringBuilder.reflectionToString(identifier));
    }

    /**
     * Displays a retrieved result.
     * @param result to inspect and display
     */
    public static void handleResult(Object result) {
        System.out.println(ToStringBuilder.reflectionToString(result, ToStringStyle.MULTI_LINE_STYLE));
    }
  
}
