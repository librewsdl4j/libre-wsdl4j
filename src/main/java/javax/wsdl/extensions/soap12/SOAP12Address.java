/*
 * (c) Copyright IBM Corp 2006 
 */

package javax.wsdl.extensions.soap12;

import javax.wsdl.extensions.ExtensibilityElement;

/**
 * Copied from javax.wsdl.extensions.soap.SOAPAddress.
 */
public interface SOAP12Address extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the location URI for this SOAP address.
   *
   * @param locationURI the desired location URI
   */
  void setLocationURI(String locationURI);

  /**
   * Get the location URI for this SOAP address.
   */
  String getLocationURI();
}