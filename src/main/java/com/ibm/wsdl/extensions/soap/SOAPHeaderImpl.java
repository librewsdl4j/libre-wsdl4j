/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl.extensions.soap;

import javax.wsdl.extensions.soap.SOAPHeader;
import javax.wsdl.extensions.soap.SOAPHeaderFault;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class SOAPHeaderImpl implements SOAPHeader
{
  protected QName elementType = SOAPConstants.Q_ELEM_SOAP_HEADER;
  protected Boolean required = null;
  protected QName message = null;
  protected String part = null;
  protected String use = null;
  protected List<String> encodingStyles = null;
  protected String namespaceURI = null;
  protected List<SOAPHeaderFault> soapHeaderFaults = new ArrayList<>();

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
   * Set the message for this SOAP header.
   *
   * @param message the desired message
   */
  @Override
  public void setMessage(QName message)
  {
    this.message = message;
  }

  /**
   * Get the message for this SOAP header.
   */
  public QName getMessage()
  {
    return message;
  }

  /**
   * Set the part for this SOAP header.
   *
   * @param part the desired part
   */
  @Override
  public void setPart(String part)
  {
    this.part = part;
  }

  /**
   * Get the part for this SOAP header.
   */
  public String getPart()
  {
    return part;
  }

  /**
   * Set the use for this SOAP header.
   *
   * @param use the desired use
   */
  @Override
  public void setUse(String use)
  {
    this.use = use;
  }

  /**
   * Get the use for this SOAP header.
   */
  public String getUse()
  {
    return use;
  }

  /**
   * Set the encodingStyles for this SOAP header.
   *
   * @param encodingStyles the desired encodingStyles
   */
  @Override
  public void setEncodingStyles(List<String> encodingStyles)
  {
    this.encodingStyles = encodingStyles;
  }

  /**
   * Get the encodingStyles for this SOAP header.
   */
  public List<String> getEncodingStyles()
  {
    return encodingStyles;
  }

  /**
   * Set the namespace URI for this SOAP header.
   *
   * @param namespaceURI the desired namespace URI
   */
  @Override
  public void setNamespaceURI(String namespaceURI)
  {
    this.namespaceURI = namespaceURI;
  }

  /**
   * Get the namespace URI for this SOAP header.
   */
  public String getNamespaceURI()
  {
    return namespaceURI;
  }

  public void addSOAPHeaderFault(SOAPHeaderFault soapHeaderFault)
  {
    soapHeaderFaults.add(soapHeaderFault);
  }
  
  @Override
  public SOAPHeaderFault removeSOAPHeaderFault(SOAPHeaderFault soapHeaderFault)
  {
    if(soapHeaderFaults.remove(soapHeaderFault))
      return soapHeaderFault;
    else
      return null;
  }

  public List<SOAPHeaderFault> getSOAPHeaderFaults()
  {
    return soapHeaderFaults;
  }

  @Override
  public String toString()
  {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("SOAPHeader (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (message != null)
    {
      strBuf.append("\nmessage=" + message);
    }

    if (part != null)
    {
      strBuf.append("\npart=" + part);
    }

    if (use != null)
    {
      strBuf.append("\nuse=" + use);
    }

    if (encodingStyles != null)
    {
      strBuf.append("\nencodingStyles=" + encodingStyles);
    }

    if (namespaceURI != null)
    {
      strBuf.append("\nnamespaceURI=" + namespaceURI);
    }

    if (soapHeaderFaults != null)
    {
      strBuf.append("\nsoapHeaderFaults=" + soapHeaderFaults);
    }

    return strBuf.toString();
  }
}