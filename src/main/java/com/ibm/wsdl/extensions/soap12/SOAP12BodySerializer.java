/*
 * (c) Copyright IBM Corp 2006 
 */

package com.ibm.wsdl.extensions.soap12;

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
import javax.wsdl.extensions.mime.MIMEPart;
import javax.wsdl.extensions.soap12.SOAP12Body;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Based on com.ibm.wsdl.extensions.soap.SOAPBodySerializer
 */
public class SOAP12BodySerializer implements ExtensionSerializer,
                                           ExtensionDeserializer,
                                           Serializable
{
  public static final long serialVersionUID = 1;

  @SuppressWarnings("unchecked")
  public void marshall(Class parentType,
                       QName elementType,
                       ExtensibilityElement extension,
                       PrintWriter pw,
                       Definition def,
                       ExtensionRegistry extReg)
                         throws WSDLException
  {
    SOAP12Body soapBody = (SOAP12Body)extension;

    if (soapBody != null)
    {
      String tagName =
        DOMUtils.getQualifiedValue(SOAP12Constants.NS_URI_SOAP12,
                                   "body",
                                   def);

      if (parentType != null
          && MIMEPart.class.isAssignableFrom(parentType))
      {
        pw.print("    ");
      }

      pw.print("        <" + tagName);

      DOMUtils.printAttribute(SOAP12Constants.ATTR_PARTS,
                              StringUtils.getNMTokens(soapBody.getParts()),
                              pw);
      DOMUtils.printAttribute(SOAP12Constants.ATTR_USE, soapBody.getUse(), pw);
      DOMUtils.printAttribute(SOAP12Constants.ATTR_ENCODING_STYLE,
                              soapBody.getEncodingStyle(),
                       pw);
      DOMUtils.printAttribute(Constants.ATTR_NAMESPACE,
                              soapBody.getNamespaceURI(),
                              pw);

      Boolean required = soapBody.getRequired();

      if (required != null)
      {
        DOMUtils.printQualifiedAttribute(Constants.Q_ATTR_REQUIRED,
                                         required.toString(),
                                         def,
                                         pw);
      }

      pw.println("/>");
    }
  }

  @SuppressWarnings("unchecked")
  public ExtensibilityElement unmarshall(Class parentType,
                                         QName elementType,
                                         Element el,
                                         Definition def,
                                         ExtensionRegistry extReg)
                                           throws WSDLException
  {
    SOAP12Body soapBody = (SOAP12Body)extReg.createExtension(parentType,
                                                         elementType);
    String partsStr = DOMUtils.getAttribute(el, SOAP12Constants.ATTR_PARTS);
    String use = DOMUtils.getAttribute(el, SOAP12Constants.ATTR_USE);
    String encStyleStr = DOMUtils.getAttribute(el,
                                          SOAP12Constants.ATTR_ENCODING_STYLE);
    String namespaceURI = DOMUtils.getAttribute(el, Constants.ATTR_NAMESPACE);
    String requiredStr = DOMUtils.getAttributeNS(el,
                                                 Constants.NS_URI_WSDL,
                                                 Constants.ATTR_REQUIRED);

    if (partsStr != null)
    {
      soapBody.setParts(StringUtils.parseNMTokens(partsStr));
    }

    if (use != null)
    {
      soapBody.setUse(use);
    }

    if (encStyleStr != null)
    {
      soapBody.setEncodingStyle(encStyleStr);
    }

    if (namespaceURI != null)
    {
      soapBody.setNamespaceURI(namespaceURI);
    }

    if (requiredStr != null)
    {
      soapBody.setRequired(Boolean.valueOf(requiredStr));
    }

    return soapBody;
  }
}