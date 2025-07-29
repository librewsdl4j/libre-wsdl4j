/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl;

import javax.wsdl.Part;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents a message part and contains the part's
 * name, elementName, typeName, and any extensibility attributes.
 *
 * @author Paul Fremantle
 * @author Nirmal Mukhi
 * @author Matthew J. Duftler
 */
public class PartImpl extends AbstractWSDLElement implements Part
{
  protected String name = null;
  protected QName elementName = null;
  protected QName typeName = null;
  protected List nativeAttributeNames =
    Arrays.asList(Constants.PART_ATTR_NAMES);

  public static final long serialVersionUID = 1;

  /**
   * Set the name of this part.
   *
   * @param name the desired name
   */
  @Override
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Get the name of this part.
   *
   * @return the part name
   */
  public String getName()
  {
    return name;
  }

  @Override
  public void setElementName(QName elementName)
  {
    this.elementName = elementName;
  }

  public QName getElementName()
  {
    return elementName;
  }

  @Override
  public void setTypeName(QName typeName)
  {
    this.typeName = typeName;
  }

  public QName getTypeName()
  {
    return typeName;
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

    strBuf.append("Part: name=" + name);

    if (elementName != null)
    {
      strBuf.append("\nelementName=" + elementName);
    }

    if (typeName != null)
    {
      strBuf.append("\ntypeName=" + typeName);
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
