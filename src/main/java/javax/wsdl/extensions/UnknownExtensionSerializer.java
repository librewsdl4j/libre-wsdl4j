/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl.extensions;

import com.ibm.wsdl.util.xml.DOM2Writer;

import javax.wsdl.Definition;
import javax.wsdl.WSDLException;
import javax.xml.namespace.QName;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * This class is used to serialize UnknownExtensibilityElement instances
 * into the PrintWriter.
 *
 * @see UnknownExtensibilityElement
 * @see UnknownExtensionDeserializer
 *
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class UnknownExtensionSerializer implements ExtensionSerializer,
                                                   Serializable
{
  public static final long serialVersionUID = 1;

  public void marshall(Class<?> parentType,
                       QName elementType,
                       ExtensibilityElement extension,
                       PrintWriter pw,
                       Definition def,
                       ExtensionRegistry extReg)
                         throws WSDLException
  {
    UnknownExtensibilityElement unknownExt =
      (UnknownExtensibilityElement)extension;

    pw.print("    ");

    DOM2Writer.serializeAsXML(unknownExt.getElement(), def.getNamespaces(), pw);

    pw.println();
  }
}