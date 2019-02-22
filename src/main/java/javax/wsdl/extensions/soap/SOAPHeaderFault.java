/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.soap;

import java.util.*;
import javax.wsdl.extensions.*;
import javax.xml.namespace.*;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface SOAPHeaderFault extends ExtensibilityElement,
                                         java.io.Serializable
{
  /**
   * Set the message for this SOAP header fault.
   *
   * @param message the desired message
   */
  void setMessage(QName message);

  /**
   * Get the message for this SOAP header fault.
   */
  QName getMessage();

  /**
   * Set the part for this SOAP header fault.
   *
   * @param part the desired part
   */
  void setPart(String part);

  /**
   * Get the part for this SOAP header fault.
   */
  String getPart();

  /**
   * Set the use for this SOAP header fault.
   *
   * @param use the desired use
   */
  void setUse(String use);

  /**
   * Get the use for this SOAP header fault.
   */
  String getUse();

  /**
   * Set the encodingStyles for this SOAP header fault.
   *
   * @param encodingStyles the desired encodingStyles
   */
  void setEncodingStyles(List encodingStyles);

  /**
   * Get the encodingStyles for this SOAP header fault.
   */
  List getEncodingStyles();

  /**
   * Set the namespace URI for this SOAP header fault.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI for this SOAP header fault.
   */
  String getNamespaceURI();
}