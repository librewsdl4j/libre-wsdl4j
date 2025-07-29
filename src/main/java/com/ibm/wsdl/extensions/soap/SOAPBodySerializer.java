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
import javax.wsdl.extensions.mime.MIMEPart;
import javax.wsdl.extensions.soap.SOAPBody;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class SOAPBodySerializer implements ExtensionSerializer,
                                           ExtensionDeserializer,
                                           Serializable
{
  public static final long serialVersionUID = 1;

  @SuppressWarnings("unchecked")
  public void marshall(Class<?> parentType,
                       QName elementType,
                       ExtensibilityElement extension,
                       PrintWriter pw,
                       Definition def,
                       ExtensionRegistry extReg)
                         throws WSDLException
  {
    SOAPBody soapBody = (SOAPBody)extension;

    if (soapBody != null)
    {
      String tagName =
        DOMUtils.getQualifiedValue(SOAPConstants.NS_URI_SOAP,
                                   "body",
                                   def);

      if (parentType != null
          && MIMEPart.class.isAssignableFrom(parentType))
      {
        pw.print("    ");
      }

      pw.print("        <" + tagName);

      DOMUtils.printAttribute(SOAPConstants.ATTR_PARTS,
                              StringUtils.getNMTokens(soapBody.getParts()),
                              pw);
      DOMUtils.printAttribute(SOAPConstants.ATTR_USE, soapBody.getUse(), pw);
      DOMUtils.printAttribute(SOAPConstants.ATTR_ENCODING_STYLE,
                       StringUtils.getNMTokens(soapBody.getEncodingStyles()),
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
  public ExtensibilityElement unmarshall(Class<?> parentType,
                                         QName elementType,
                                         Element el,
                                         Definition def,
                                         ExtensionRegistry extReg)
                                           throws WSDLException
  {
    SOAPBody soapBody = (SOAPBody)extReg.createExtension(parentType,
                                                         elementType);
    String partsStr = DOMUtils.getAttribute(el, SOAPConstants.ATTR_PARTS);
    String use = DOMUtils.getAttribute(el, SOAPConstants.ATTR_USE);
    String encStyleStr = DOMUtils.getAttribute(el,
                                          SOAPConstants.ATTR_ENCODING_STYLE);
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
      soapBody.setEncodingStyles(StringUtils.parseNMTokens(encStyleStr));
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