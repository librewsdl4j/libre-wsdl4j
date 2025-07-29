/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl.extensions.soap;

import javax.wsdl.extensions.ExtensibilityElement;
import javax.xml.namespace.QName;
import java.util.List;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface SOAPHeader extends ExtensibilityElement, java.io.Serializable
{
  /**
   * Set the message for this SOAP header.
   *
   * @param message the desired message
   */
  void setMessage(QName message);

  /**
   * Get the message for this SOAP header.
   */
  QName getMessage();

  /**
   * Set the part for this SOAP header.
   *
   * @param part the desired part
   */
  void setPart(String part);

  /**
   * Get the part for this SOAP header.
   */
  String getPart();

  /**
   * Set the use for this SOAP header.
   *
   * @param use the desired use
   */
  void setUse(String use);

  /**
   * Get the use for this SOAP header.
   */
  String getUse();

  /**
   * Set the encodingStyles for this SOAP header.
   *
   * @param encodingStyles the desired encodingStyles
   */
  void setEncodingStyles(List<String> encodingStyles);

  /**
   * Get the encodingStyles for this SOAP header.
   */
  List<String> getEncodingStyles();

  /**
   * Set the namespace URI for this SOAP header.
   *
   * @param namespaceURI the desired namespace URI
   */
  void setNamespaceURI(String namespaceURI);

  /**
   * Get the namespace URI for this SOAP header.
   */
  String getNamespaceURI();

  /**
   * Add a SOAP header fault.
   * 
   * @param soapHeaderFault the SOAP Header fault to be added.
   */
  void addSOAPHeaderFault(SOAPHeaderFault soapHeaderFault);
  
  /**
   * Remove a SOAP header fault.
   * 
   * @param soapHeaderFault the SOAP header fault to be removed.
   * @return the SOAP header fault which was removed.
   */
  SOAPHeaderFault removeSOAPHeaderFault(SOAPHeaderFault soapHeaderFault);

  /**
   * Get a list of all SOAP header faults contained in this SOAP header.
   * 
   * @return a list of all SOAP header faults contained in this SOAP header.
   */
  List<SOAPHeaderFault> getSOAPHeaderFaults();
}