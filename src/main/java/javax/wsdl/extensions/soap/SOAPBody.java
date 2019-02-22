/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package javax.wsdl.extensions.soap;

import java.util.*;
import javax.wsdl.extensions.*;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface SOAPBody extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the parts for this SOAP body.
   *
   * @param parts the desired parts
   */
  void setParts(List parts);

  /**
   * Get the parts for this SOAP body.
   */
  List getParts();

  /**
   * Set the use for this SOAP body.
   *
   * @param use the desired use
   */
  void setUse(String use);

  /**
   * Get the use for this SOAP body.
   */
  String getUse();

  /**
   * Set the encodingStyles for this SOAP body.
   *
   * @param encodingStyles the desired encodingStyles
   */
  void setEncodingStyles(List encodingStyles);

  /**
   * Get the encodingStyles for this SOAP body.
   */
  List getEncodingStyles();

  /**
   * Set the namespace URI for this SOAP body.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI for this SOAP body.
   */
  String getNamespaceURI();
}