/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package com.ibm.wsdl.extensions.soap;

import com.ibm.wsdl.Constants;
import com.ibm.wsdl.util.StringUtils;
import com.ibm.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.soap.SOAPFault;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class SOAPFaultSerializer implements ExtensionSerializer, ExtensionDeserializer, Serializable {
  public static final long serialVersionUID = 1;

  public void marshall(Class<?> parentType, QName elementType, ExtensibilityElement extension, PrintWriter pw, Definition def, ExtensionRegistry extReg) throws WSDLException {
    SOAPFault soapFault = (SOAPFault) extension;

    if (soapFault != null) {
      String tagName = DOMUtils.getQualifiedValue(SOAPConstants.NS_URI_SOAP, "fault", def);

      pw.print("        <" + tagName);

      DOMUtils.printAttribute(Constants.ATTR_NAME, soapFault.getName(), pw);
      DOMUtils.printAttribute(SOAPConstants.ATTR_USE, soapFault.getUse(), pw);
      DOMUtils.printAttribute(SOAPConstants.ATTR_ENCODING_STYLE, StringUtils.getNMTokens(soapFault.getEncodingStyles()), pw);
      DOMUtils.printAttribute(Constants.ATTR_NAMESPACE, soapFault.getNamespaceURI(), pw);

      Boolean required = soapFault.getRequired();

      if (required != null) {
        DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED, required.toString(), def, pw);
      }

      pw.println("/>");
    }
  }

  public ExtensibilityElement unmarshall(Class<?> parentType, QName elementType, Element el, Definition def, ExtensionRegistry extReg) throws WSDLException {
    SOAPFault soapFault = (SOAPFault) extReg.createExtension(parentType, elementType);
    String name = DOMUtils.getAttribute(el, Constants.ATTR_NAME);
    String use = DOMUtils.getAttribute(el, SOAPConstants.ATTR_USE);
    String encStyleStr = DOMUtils.getAttribute(el, SOAPConstants.ATTR_ENCODING_STYLE);
    String namespaceURI = DOMUtils.getAttribute(el, Constants.ATTR_NAMESPACE);
    String requiredStr = DOMUtils.getAttributeNS(el, Constants.NS_URI_WSDL, Constants.ATTR_REQUIRED);

    if (name != null) {
      soapFault.setName(name);
    }

    if (use != null) {
      soapFault.setUse(use);
    }

    if (encStyleStr != null) {
      soapFault.setEncodingStyles(StringUtils.parseNMTokens(encStyleStr));
    }

    if (namespaceURI != null) {
      soapFault.setNamespaceURI(namespaceURI);
    }

    if (requiredStr != null) {
      soapFault.setRequired(Boolean.valueOf(requiredStr));
    }

    return soapFault;
  }
}
