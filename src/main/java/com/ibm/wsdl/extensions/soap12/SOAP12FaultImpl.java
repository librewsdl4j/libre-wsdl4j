/*
 * (c) Copyright IBM Corp 2006 
 */

package com.ibm.wsdl.extensions.soap12;

import javax.wsdl.extensions.soap12.SOAP12Fault;
import javax.xml.namespace.QName;

/**
 * Based on com.ibm.wsdl.extensions.soap.SOAPFaultImpl
 */
public class SOAP12FaultImpl implements SOAP12Fault
{
  protected QName elementType = SOAP12Constants.Q_ELEM_SOAP_FAULT;
  protected Boolean required = null;
  protected String name = null;
  protected String use = null;
  protected String encodingStyle = null;
  protected String namespaceURI = null;

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
   * Set the name for this SOAP fault.
   *
   * @param name the desired name
   */
  @Override
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the name for this SOAP fault.
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the use for this SOAP fault.
   *
   * @param use the desired use
   */
  @Override
  public void setUse(String use)
  {
    this.use = use;
  }

  /**
   * Get the use for this SOAP fault.
   */
  public String getUse()
  {
    return use;
  }

  /**
   * Set the encodingStyle for this SOAP fault.
   *
   * @param encodingStyle the desired encodingStyle
   */
  @Override
  public void setEncodingStyle(String encodingStyle)
  {
    this.encodingStyle = encodingStyle;
  }

  /**
   * Get the encodingStyle for this SOAP fault.
   */
  public String getEncodingStyle()
  {
    return encodingStyle;
  }

  /**
   * Set the namespace URI for this SOAP fault.
   *
   * @param namespaceURI the desired namespace URI
   */
  @Override
  public void setNamespaceURI(String namespaceURI)
  {
    this.namespaceURI = namespaceURI;
  }

  /**
   * Get the namespace URI for this SOAP fault.
   */
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  @Override
  public String toString()
  {
    StringBuffer strBuf = new StringBuffer();

    strBuf.append("SOAPFault (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (name != null)
    {
      strBuf.append("\nname=" + name);
    }

    if (use != null)
    {
      strBuf.append("\nuse=" + use);
    }

    if (encodingStyle != null)
    {
      strBuf.append("\nencodingStyle=" + encodingStyle);
    }

    if (namespaceURI != null)
    {
      strBuf.append("\nnamespaceURI=" + namespaceURI);
    }

    return strBuf.toString();
  }
}