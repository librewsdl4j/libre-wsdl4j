/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package com.ibm.wsdl.extensions.mime;

import javax.wsdl.extensions.mime.MIMEContent;
import javax.xml.namespace.QName;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class MIMEContentImpl implements MIMEContent
{
  protected QName elementType = MIMEConstants.Q_ELEM_MIME_CONTENT;
  // Uses the wrapper type so we can tell if it was set or not.
  protected Boolean required = null;
  protected String part = null;
  protected String type = null;

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
   * Set the part for this MIME content.
   *
   * @param part the desired part
   */
  @Override
  public void setPart(String part)
  {
    this.part = part;
  }

  /**
   * Get the part for this MIME content.
   */
  public String getPart()
  {
    return part;
  }

  /**
   * Set the type for this MIME content.
   *
   * @param type the desired type
   */
  @Override
  public void setType(String type)
  {
    this.type = type;
  }

  /**
   * Get the type for this MIME content.
   */
  public String getType()
  {
    return type;
  }

  @Override
  public String toString()
  {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("MIMEContent (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (part != null)
    {
      strBuf.append("\npart=" + part);
    }

    if (type != null)
    {
      strBuf.append("\ntype=" + type);
    }

    return strBuf.toString();
  }
}