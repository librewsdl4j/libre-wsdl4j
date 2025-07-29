/*
 * (c) Copyright IBM Corp 2001, 2005 
 */

package com.ibm.wsdl.extensions.http;

import com.ibm.wsdl.Constants;
import com.ibm.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.http.HTTPBinding;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class HTTPBindingSerializer implements ExtensionSerializer, ExtensionDeserializer, Serializable {
  public static final long serialVersionUID = 1;

  public void marshall(Class<?> parentType, QName elementType, ExtensibilityElement extension, PrintWriter pw, Definition def, ExtensionRegistry extReg) throws WSDLException {
    HTTPBinding httpBinding = (HTTPBinding) extension;

    if (httpBinding != null) {
      String tagName = DOMUtils.getQualifiedValue(HTTPConstants.NS_URI_HTTP, "binding", def);

      pw.print("    <" + tagName);

      DOMUtils.printAttribute(HTTPConstants.ATTR_VERB, httpBinding.getVerb(), pw);

      Boolean required = httpBinding.getRequired();

      if (required != null) {
        DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED, required.toString(), def, pw);
      }

      pw.println("/>");
    }
  }

  public ExtensibilityElement unmarshall(Class<?> parentType, QName elementType, Element el, Definition def, ExtensionRegistry extReg) throws WSDLException {
    HTTPBinding httpBinding = (HTTPBinding) extReg.createExtension(parentType, elementType);
    String verb = DOMUtils.getAttribute(el, HTTPConstants.ATTR_VERB);
    String requiredStr = DOMUtils.getAttributeNS(el, Constants.NS_URI_WSDL, Constants.ATTR_REQUIRED);

    if (verb != null) {
      httpBinding.setVerb(verb);
    }

    if (requiredStr != null) {
      httpBinding.setRequired(Boolean.valueOf(requiredStr));
    }

    return httpBinding;
  }
}