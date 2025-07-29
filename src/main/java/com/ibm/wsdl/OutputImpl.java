/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl;

import javax.wsdl.Message;
import javax.wsdl.Output;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents an output message, and contains the name
 * of the output and the message itself.
 *
 * @author Matthew J. Duftler
 */
public class OutputImpl extends AbstractWSDLElement implements Output
{
  protected String name = null;
  protected Message message = null;
  protected List nativeAttributeNames =
    Arrays.asList(Constants.OUTPUT_ATTR_NAMES);

  public static final long serialVersionUID = 1;

  /**
   * Set the name of this output message.
   *
   * @param name the desired name
   */
  @Override
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the name of this output message.
   *
   * @return the output message name
   */
  public String getName()
  {
    return name;
  }

  @Override
  public void setMessage(Message message)
  {
    this.message = message;
  }

  public Message getMessage()
  {
    return message;
  }

  /**
   * Get the list of local attribute names defined for this element in
   * the WSDL specification.
   *
   * @return a List of Strings, one for each local attribute name
   */
  public List getNativeAttributeNames()
  {
    return nativeAttributeNames;
  }

  @Override
  public String toString()
  {
    StringBuffer strBuf = new StringBuffer();

    strBuf.append("Output: name=" + name);

    if (message != null)
    {
      strBuf.append("\n" + message);
    }

    String superString = super.toString();
    if(!superString.equals(""))
    {
      strBuf.append("\n");
      strBuf.append(superString);
    }

    return strBuf.toString();
  }
}
