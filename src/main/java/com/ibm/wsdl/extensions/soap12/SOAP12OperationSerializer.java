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
import javax.wsdl.extensions.soap12.SOAP12Operation;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * Based on com.ibm.wsdl.extensions.soap.SOAPOperationSerializer
 */
public class SOAP12OperationSerializer implements ExtensionSerializer,
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
    SOAP12Operation soapOperation = (SOAP12Operation)extension;

    if (soapOperation != null)
    {
      String tagName =
        DOMUtils.getQualifiedValue(SOAP12Constants.NS_URI_SOAP12,
                                   "operation",
                                   def);

      pw.print("      <" + tagName);

      Boolean soapActionRequired = soapOperation.getSoapActionRequired();
      String soapActionRequiredString =
        soapActionRequired == null ? null : soapActionRequired.toString();      
      
      DOMUtils.printAttribute(SOAP12Constants.ATTR_SOAP_ACTION,
                              soapOperation.getSoapActionURI(),
                              pw);
      DOMUtils.printAttribute(SOAP12Constants.ATTR_SOAP_ACTION_REQUIRED,
                              soapActionRequiredString,
                              pw);
      DOMUtils.printAttribute(SOAP12Constants.ATTR_STYLE,
                              soapOperation.getStyle(),
                              pw);

      Boolean required = soapOperation.getRequired();

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
    SOAP12Operation soapOperation =
      (SOAP12Operation)extReg.createExtension(parentType, elementType);
    String soapActionURI = DOMUtils.getAttribute(el,
                                             SOAP12Constants.ATTR_SOAP_ACTION);
    String soapActionRequiredString = DOMUtils.getAttribute(el,
                                             SOAP12Constants.ATTR_SOAP_ACTION_REQUIRED);
    String style = DOMUtils.getAttribute(el, SOAP12Constants.ATTR_STYLE);
    String requiredStr = DOMUtils.getAttributeNS(el,
                                                 Constants.NS_URI_WSDL,
                                                 Constants.ATTR_REQUIRED);
    if (soapActionURI != null)
    {
      soapOperation.setSoapActionURI(soapActionURI);
    }
    
    if(soapActionRequiredString != null)
    {
      Boolean soapActionRequired = Boolean.valueOf(soapActionRequiredString);
      soapOperation.setSoapActionRequired(soapActionRequired);
    }
    
    if (style != null)
    {
      soapOperation.setStyle(style);
    }

    if (requiredStr != null)
    {
      soapOperation.setRequired(Boolean.valueOf(requiredStr));
    }

    return soapOperation;
  }
}