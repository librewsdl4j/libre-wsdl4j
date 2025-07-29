/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.http;

import javax.wsdl.extensions.ExtensibilityElement;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface HTTPOperation extends ExtensibilityElement,
                                       java.io.Serializable
{
  /**
   * Set the location URI for this HTTP operation.
   *
   * @param locationURI the desired location URI
   */
  void setLocationURI(String locationURI);

  /**
   * Get the location URI for this HTTP operation.
   */
  String getLocationURI();
}