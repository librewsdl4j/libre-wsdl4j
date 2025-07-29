/*
 * (c) Copyright IBM Corp 2004, 2005 
 */

package javax.wsdl.extensions.schema;


/**
 * Represents an import element within a schema element.
 * Similar to an include or redefine, but includes a namespace.
 * 
 * @author Jeremy Hughes &lt;hughesj@uk.ibm.com&gt;
 */
public interface SchemaImport extends SchemaReference
{
  /**
   * @return Returns the namespace.
   */
  String getNamespaceURI();

  /**
   * @param namespace The namespace to set.
   */
  void setNamespaceURI(String namespace);
}