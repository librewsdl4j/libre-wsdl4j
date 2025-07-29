/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.http;

import javax.wsdl.extensions.ExtensibilityElement;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface HTTPBinding extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the verb for this HTTP binding.
   *
   * @param verb the desired verb
   */
  void setVerb(String verb);

  /**
   * Get the verb for this HTTP binding.
   */
  String getVerb();
}