/*
 * (c) Copyright IBM Corp 2004, 2005
 */

package com.ibm.wsdl.extensions.schema;

import javax.wsdl.extensions.schema.SchemaImport;

/**
 * @author Jeremy Hughes &lt;hughesj@uk.ibm.com&gt;
 */
public class SchemaImportImpl extends SchemaReferenceImpl implements SchemaImport
{
  public static final long serialVersionUID = 1;

  private String namespace = null;

  /**
   * @return Returns the namespace.
   */
  public String getNamespaceURI()
  {
    return this.namespace;
  }

  /**
   * @param namespace The namespace to set.
   */
  @Override
  public void setNamespaceURI(String namespace)
  {
    this.namespace = namespace;
  }
}