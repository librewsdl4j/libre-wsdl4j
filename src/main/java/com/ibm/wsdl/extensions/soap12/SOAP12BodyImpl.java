/*
 * (c) Copyright IBM Corp 2006 
 */

package com.ibm.wsdl.extensions.soap12;

import javax.wsdl.extensions.soap12.SOAP12Body;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * Based on com.ibm.wsdl.extensions.soap.SOAPBodyImpl
 */
public class SOAP12BodyImpl implements SOAP12Body
{
  protected QName elementType = SOAP12Constants.Q_ELEM_SOAP_BODY;
  protected Boolean required = null;
  protected List parts = null;
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
   * Set the parts for this SOAP body.
   *
   * @param parts the desired parts
   */
  @Override
  public void setParts(List parts)
  {
    this.parts = parts;
  }

  /**
   * Get the parts for this SOAP body.
   */
  public List getParts()
  {
    return parts;
  }

  /**
   * Set the use for this SOAP body.
   *
   * @param use the desired use
   */
  @Override
  public void setUse(String use)
  {
    this.use = use;
  }

  /**
   * Get the use for this SOAP body.
   */
  public String getUse()
  {
    return use;
  }

  /**
   * Set the encodingStyle for this SOAP body.
   *
   * @param encodingStyle the desired encodingStyle
   */
  @Override
  public void setEncodingStyle(String encodingStyle)
  {
    this.encodingStyle = encodingStyle;
  }

  /**
   * Get the encodingStyle for this SOAP body.
   */
  public String getEncodingStyle()
  {
    return encodingStyle;
  }

  /**
   * Set the namespace URI for this SOAP body.
   *
   * @param namespaceURI the desired namespace URI
   */
  @Override
  public void setNamespaceURI(String namespaceURI)
  {
    this.namespaceURI = namespaceURI;
  }

  /**
   * Get the namespace URI for this SOAP body.
   */
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  @Override
  public String toString()
  {
    StringBuffer strBuf = new StringBuffer();

    strBuf.append("SOAPBody (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (parts != null)
    {
      strBuf.append("\nparts=" + parts);
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