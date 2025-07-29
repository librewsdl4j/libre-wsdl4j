/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl.extensions.mime;

import javax.wsdl.extensions.ExtensibilityElement;
import java.util.List;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface MIMEMultipartRelated extends ExtensibilityElement,
                                              java.io.Serializable
{
  /**
   * Add a MIME part to this MIME multipart related.
   *
   * @param mimePart the MIME part to be added
   */
  void addMIMEPart(MIMEPart mimePart);
  
  /**
   * Remove a MIME part to this MIME multipart related.
   *
   * @param mimePart the MIME part to be remove.
   * @return the MIME part which was removed.
   */
  MIMEPart removeMIMEPart(MIMEPart mimePart);

  /**
   * Get all the MIME parts defined here.
   */
  List<MIMEPart> getMIMEParts();
}