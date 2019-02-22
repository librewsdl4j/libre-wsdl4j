/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl;

/**
 * This interface represents an import, and may contain a reference
 * to the imported definition.
 *
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface Import extends WSDLElement
{
  /**
   * Set the namespace URI of this import.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI of this import.
   */
  String getNamespaceURI();

  /**
   * Set the location URI of this import.
   *
   * @param locationURI the desired location URI
   */
  void setLocationURI(String locationURI);

  /**
   * Get the location URI of this import.
   */
  String getLocationURI();

  /**
   * This property can be used to hang a referenced Definition,
   * and the top-level Definition (i.e. the one with the &lt;import&gt;)
   * will use this Definition when resolving referenced WSDL parts.
   * This would need to be made into a generic reference to handle
   * other types of referenced documents.
   */
  void setDefinition(Definition definition);

  /**
   * This property can be used to hang a referenced Definition,
   * and the top-level Definition (i.e. the one with the &lt;import&gt;)
   * will use this Definition when resolving referenced WSDL parts.
   * This would need to be made into a generic reference to handle
   * other types of referenced documents.
   */
  Definition getDefinition();
}