/*
 * (c) Copyright IBM Corp 2006 
 */

package javax.wsdl.extensions.soap12;

import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;

/**
 * Based on javax.wsdl.extensions.SOAPHeaderFault.
 */
public interface SOAP12HeaderFault extends ExtensibilityElement,
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
   * Set the encodingStyle for this SOAP header fault.
   *
   * @param encodingStyle the desired encodingStyle
   */
  void setEncodingStyle(String encodingStyle);

  /**
   * Get the encodingStyle for this SOAP header fault.
   */
  String getEncodingStyle();

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