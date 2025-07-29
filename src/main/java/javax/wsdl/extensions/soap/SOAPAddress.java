/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.soap;

import javax.wsdl.extensions.ExtensibilityElement;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface SOAPAddress extends ExtensibilityElement, java.io.Serializable
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