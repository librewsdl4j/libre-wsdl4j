/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl;

import javax.wsdl.Binding;
import javax.wsdl.Port;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a port, an endpoint for the
 * functionality described by a particular port type.
 *
 * @author Paul Fremantle
 * @author Nirmal Mukhi
 * @author Matthew J. Duftler
 */
public class PortImpl extends AbstractWSDLElement implements Port
{
  protected String name = null;
  protected Binding binding = null;
  protected List<String> nativeAttributeNames =
    Arrays.asList(Constants.PORT_ATTR_NAMES);

  public static final long serialVersionUID = 1;

  /**
   * Set the name of this port.
   *
   * @param name the desired name
   */
  @Override
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the name of this port.
   *
   * @return the port name
   */
  public String getName()
  {
    return name;
  }

  /**
   * Set the binding this port should refer to.
   *
   * @param binding the desired binding
   */
  @Override
  public void setBinding(Binding binding)
  {
    this.binding = binding;
  }

  /**
   * Get the binding this port refers to.
   *
   * @return the binding associated with this port
   */
  public Binding getBinding()
  {
    return binding;
  }

  @Override
  public String toString()
  {
    StringBuffer strBuf = new StringBuffer();

    strBuf.append("Port: name=" + name);

    if (binding != null)
    {
      strBuf.append("\n" + binding);
    }

    String superString = super.toString();
    if(!superString.equals(""))
    {
      strBuf.append("\n");
      strBuf.append(superString);
    }

    return strBuf.toString();
  }
  
  /**
   * Get the list of local attribute names defined for this element in
   * the WSDL specification.
   *
   * @return a List of Strings, one for each local attribute name
   */
  public List<String> getNativeAttributeNames()
  {
    return nativeAttributeNames;
  }
}
