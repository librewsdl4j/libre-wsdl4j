/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl;

import javax.xml.namespace.*;

/**
 * This interface represents a message part and contains the part's
 * name, elementName, typeName, and any extensibility attributes.
 *
 * @author Paul Fremantle
 * @author Nirmal Mukhi
 * @author Matthew J. Duftler
 */
public interface Part extends WSDLElement
{
  /**
   * Set the name of this part.
   *
   * @param name the desired name
   */
  void setName(String name);

  /**
   * Get the name of this part.
   *
   * @return the part name
   */
  String getName();

  void setElementName(QName elementName);

  QName getElementName();

  void setTypeName(QName typeName);

  QName getTypeName();
}
