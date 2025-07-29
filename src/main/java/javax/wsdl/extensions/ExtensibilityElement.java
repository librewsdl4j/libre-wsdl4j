/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions;

import javax.xml.namespace.QName;

/**
 * This interface should be implemented by classes intending to represent
 * extensions.
 *
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface ExtensibilityElement
{
  /**
   * Set the type of this extensibility element.
   *
   * @param elementType the type
   */
  void setElementType(QName elementType);

  /**
   * Get the type of this extensibility element.
   *
   * @return the extensibility element's type
   */
  QName getElementType();

  /**
   * Set whether or not the semantics of this extension
   * are required. Relates to the wsdl:required attribute.
   */
  void setRequired(Boolean required);

  /**
   * Get whether or not the semantics of this extension
   * are required. Relates to the wsdl:required attribute.
   */
  Boolean getRequired();
}