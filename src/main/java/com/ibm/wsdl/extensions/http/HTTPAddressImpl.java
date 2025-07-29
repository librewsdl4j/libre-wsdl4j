/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package com.ibm.wsdl.extensions.http;

import javax.wsdl.extensions.http.HTTPAddress;
import javax.xml.namespace.QName;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class HTTPAddressImpl implements HTTPAddress
{
  protected QName elementType = HTTPConstants.Q_ELEM_HTTP_ADDRESS;
  // Uses the wrapper type so we can tell if it was set or not.
  protected Boolean required = null;
  protected String locationURI = null;

  public static final long serialVersionUID = 1;

  /**
   * Set the type of this extensibility element.
   *
   * @param elementType the type
   */
  @Override
  public void setElementType(QName elementType)
  {
    this.elementType = elementType;
  }

  /**
   * Get the type of this extensibility element.
   *
   * @return the extensibility element's type
   */
  public QName getElementType()
  {
    return elementType;
  }

  /**
   * Set whether or not the semantics of this extension
   * are required. Relates to the wsdl:required attribute.
   */
  @Override
  public void setRequired(Boolean required)
  {
    this.required = required;
  }

  /**
   * Get whether or not the semantics of this extension
   * are required. Relates to the wsdl:required attribute.
   */
  public Boolean getRequired()
  {
    return required;
  }

  /**
   * Set the location URI for this HTTP address.
   *
   * @param locationURI the desired location URI
   */
  @Override
  public void setLocationURI(String locationURI)
  {
    this.locationURI = locationURI;
  }

  /**
   * Get the location URI for this HTTP address.
   */
  public String getLocationURI()
  {
    return locationURI;
  }

  @Override
  public String toString()
  {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("HTTPAddress (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (locationURI != null)
    {
      strBuf.append("\nlocationURI=" + locationURI);
    }

    return strBuf.toString();
  }
}