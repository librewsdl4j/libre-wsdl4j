/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl.extensions.mime;

import javax.wsdl.extensions.ElementExtensible;
import javax.wsdl.extensions.ExtensibilityElement;

/**
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public interface MIMEPart extends ElementExtensible,
                                  ExtensibilityElement,
                                  java.io.Serializable
{
}