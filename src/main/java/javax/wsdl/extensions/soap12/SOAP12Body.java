/*
 * (c) Copyright IBM Corp 2006 
 */

package javax.wsdl.extensions.soap12;

import javax.wsdl.extensions.ExtensibilityElement;
import java.util.List;

/**
 * Based on javax.wsdl.extensions.SOAPBody.
 */
public interface SOAP12Body extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the parts for this SOAP body.
   *
   * @param parts the desired parts
   */
  void setParts(List parts);

  /**
   * Get the parts for this SOAP body.
   */
  List getParts();

  /**
   * Set the use for this SOAP body.
   *
   * @param use the desired use
   */
  void setUse(String use);

  /**
   * Get the use for this SOAP body.
   */
  String getUse();

  /**
   * Set the encodingStyle for this SOAP body.
   *
   * @param encodingStyle the desired encodingStyle
   */
  void setEncodingStyle(String encodingStyle);

  /**
   * Get the encodingStyle for this SOAP body.
   */
  String getEncodingStyle();

  /**
   * Set the namespace URI for this SOAP body.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI for this SOAP body.
   */
  String getNamespaceURI();
}