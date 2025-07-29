/*
 * (c) Copyright IBM Corp 2006 
 */

package javax.wsdl;

import org.w3c.dom.Element;

import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ElementExtensible;

/**
 * This interface represents all WSDL Elements
 */
public interface WSDLElement extends java.io.Serializable,
                                     AttributeExtensible,
                                     ElementExtensible
{
  /**
   * Set the documentation element for this document.
   *
   * @param docEl the documentation element
   */
  void setDocumentationElement(Element docEl);

  /**
   * Get the documentation element.
   *
   * @return the documentation element
   */
  Element getDocumentationElement();
}