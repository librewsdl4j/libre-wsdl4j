/*
 * (c) Copyright IBM Corp 2006 
 */

package com.ibm.wsdl.extensions.soap12;

import com.ibm.wsdl.Constants;
import com.ibm.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Element;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionDeserializer;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Copied from com.ibm.wsdl.extensions.soap.SOAPBindingSerializer
 */
public class SOAP12BindingSerializer implements ExtensionSerializer,
                                              ExtensionDeserializer,
                                              Serializable
{
  public static final long serialVersionUID = 1;

  public void marshall(Class parentType,
                       QName elementType,
                       ExtensibilityElement extension,
                       PrintWriter pw,
                       Definition def,
                       ExtensionRegistry extReg)
                         throws WSDLException
  {
    SOAP12Binding soapBinding = (SOAP12Binding)extension;

    if (soapBinding != null)
    {
      String tagName =
        DOMUtils.getQualifiedValue(SOAP12Constants.NS_URI_SOAP12,
                                   "binding",
                                   def);

      pw.print("    <" + tagName);

      DOMUtils.printAttribute(SOAP12Constants.ATTR_STYLE,
                              soapBinding.getStyle(),
                              pw);
      DOMUtils.printAttribute(SOAP12Constants.ATTR_TRANSPORT,
                              soapBinding.getTransportURI(),
                              pw);

      Boolean required = soapBinding.getRequired();

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

  public ExtensibilityElement unmarshall(Class parentType,
                                         QName elementType,
                                         Element el,
                                         Definition def,
                                         ExtensionRegistry extReg)
                                           throws WSDLException
  {
    SOAP12Binding soapBinding = (SOAP12Binding)extReg.createExtension(parentType,
                                                                  elementType);
    String transportURI = DOMUtils.getAttribute(el,
                                                SOAP12Constants.ATTR_TRANSPORT);
    String style = DOMUtils.getAttribute(el, SOAP12Constants.ATTR_STYLE);
    String requiredStr = DOMUtils.getAttributeNS(el,
                                                 Constants.NS_URI_WSDL,
                                                 Constants.ATTR_REQUIRED);

    if (transportURI != null)
    {
      soapBinding.setTransportURI(transportURI);
    }

    if (style != null)
    {
      soapBinding.setStyle(style);
    }

    if (requiredStr != null)
    {
      soapBinding.setRequired(Boolean.valueOf(requiredStr));
    }

    return soapBinding;
  }
}