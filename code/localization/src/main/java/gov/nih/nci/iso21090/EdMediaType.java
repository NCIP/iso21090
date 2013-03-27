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
 * Represents the iso NullFlavor type.
 * @author lpower
 */
public enum EdMediaType {
    
    /** text plain. */
    TEXT_PLAIN ("text/plain"),
    /** image jpeg. */
    IMAGE_JPEG ("image/jpeg"),
    /** image gif. */
    IMAGE_GIF ("image/gif"),
    /** audio basic. */
    AUDIO_BASIC ("audio/basic"),
    /** video mpeg. */
    VIDEO_MPEG ("video/mpeg"),
    /** application octet-stream. */
    APPLICATION_OCTET_STREAM("application/octet-stream"),
    /** application PostScript. */
    APPLICATION_POSTSCRIPT("application/PostScript"),
    /** multipart mixed. */
    MULTIPART_MIXED("multipart/mixed"),
    /** multipart alternative. */
    MULTIPART_ALTERNATIVE("multipart/alternative"),
    /** application PostScript. */
    MULTIPART_PARALLEL("multipart/parallel"),
    /** multipart digest. */
    MULTIPART_DIGEST("multipart/digest"),
    /** message rfc822. */
    MESSAGE_RFC822("message/rfc822"),
    /** message partial. */
    MESSAGE_PARTIAL("message/partial"),
    /** message external-body. */
    MESSAGE_EXTERNAL_BODY("message/external-body");

   private String description;
   
   /**
    * @param description description to set
    */
   private EdMediaType(String description) {
       this.description = description;
   }
   
   /**
    * @return the description
    */
   public String getDescription() {
       return description;
   }



}


