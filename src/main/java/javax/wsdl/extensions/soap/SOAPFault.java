/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.soap;

import java.util.*;
import javax.wsdl.extensions.*;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface SOAPFault extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the name for this SOAP fault.
   *
   * @param name the desired name
   */
  void setName(String name);

  /**
   * Get the name for this SOAP fault.
   */
  String getName();

  /**
   * Set the use for this SOAP fault.
   *
   * @param use the desired use
   */
  void setUse(String use);

  /**
   * Get the use for this SOAP fault.
   */
  String getUse();

  /**
   * Set the encodingStyles for this SOAP fault.
   *
   * @param encodingStyles the desired encodingStyles
   */
  void setEncodingStyles(List encodingStyles);

  /**
   * Get the encodingStyles for this SOAP fault.
   */
  List getEncodingStyles();

  /**
   * Set the namespace URI for this SOAP fault.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI for this SOAP fault.
   */
  String getNamespaceURI();
}