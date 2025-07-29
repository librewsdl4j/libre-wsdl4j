/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl.extensions.mime;

import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.mime.MIMEPart;
import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class MIMEPartImpl implements MIMEPart {
  protected QName elementType = MIMEConstants.Q_ELEM_MIME_PART;
  // Uses the wrapper type so we can tell if it was set or not.
  protected Boolean required = null;
  protected List<ExtensibilityElement> extElements = new ArrayList<>();

  public static final long serialVersionUID = 1;

  /**
   * Set the type of this extensibility element.
   *
   * @param elementType
   *          the type
   */
  @Override
  public void setElementType(QName elementType) {
    this.elementType = elementType;
  }

  /**
   * Get the type of this extensibility element.
   *
   * @return the extensibility element's type
   */
  public QName getElementType() {
    return elementType;
  }

  /**
   * Set whether or not the semantics of this extension are required. Relates to the wsdl:required attribute.
   */
  @Override
  public void setRequired(Boolean required) {
    this.required = required;
  }

  /**
   * Get whether or not the semantics of this extension are required. Relates to the wsdl:required attribute.
   */
  public Boolean getRequired() {
    return required;
  }

  /**
   * Add an extensibility element. This is where the MIME elements go.
   *
   * @param extElement
   *          the extensibility element to be added
   */
  public void addExtensibilityElement(ExtensibilityElement extElement) {
    extElements.add(extElement);
  }

  /**
   * Remove an extensibility element.
   *
   * @param extElement
   *          the extensibility element to be removed
   * @return the extensibility element which was removed
   */
  @Override
  public ExtensibilityElement removeExtensibilityElement(ExtensibilityElement extElement) {
    if (extElements.remove(extElement))
      return extElement;
    else
      return null;
  }

  /**
   * Get all the extensibility elements defined here.
   */
  public List<ExtensibilityElement> getExtensibilityElements() {
    return extElements;
  }

  @Override
  public String toString() {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("MIMEPart (" + elementType + "):");
    strBuf.append("\nrequired=" + required);

    if (extElements != null) {
      Iterator<ExtensibilityElement> extIterator = extElements.iterator();

      while (extIterator.hasNext()) {
        strBuf.append("\n" + extIterator.next());
      }
    }

    return strBuf.toString();
  }
}