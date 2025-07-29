/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl;

import javax.wsdl.Port;
import javax.wsdl.Service;
import javax.xml.namespace.QName;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class represents a service, which groups related ports to provide some functionality.
 *
 * @author Paul Fremantle
 * @author Nirmal Mukhi
 * @author Matthew J. Duftler
 */
public class ServiceImpl extends AbstractWSDLElement implements Service {
  private QName name = null;
  private final Map<String, Port> ports = new HashMap<>();
  private final List<String> nativeAttributeNames = Arrays.asList(Constants.SERVICE_ATTR_NAMES);

  public static final long serialVersionUID = 1;

  /**
   * Set the name of this service.
   *
   * @param name
   *          the desired name
   */
  @Override
  public void setQName(QName name) {
    this.name = name;
  }

  /**
   * Get the name of this service.
   *
   * @return the service name
   */
  public QName getQName() {
    return name;
  }

  /**
   * Add a port to this service.
   *
   * @param port
   *          the port to be added
   */
  public void addPort(Port port) {
    ports.put(port.getName(), port);
  }

  /**
   * Get the specified port.
   *
   * @param name
   *          the name of the desired port.
   * @return the corresponding port, or null if there wasn't any matching port
   */
  public Port getPort(String name) {
    return ports.get(name);
  }

  /**
   * Remove the specified port.
   *
   * @param name
   *          the name of the port to be removed.
   * @return the port which was removed
   */
  @Override
  public Port removePort(String name) {
    return ports.remove(name);
  }

  /**
   * Get all the ports defined here.
   */
  public Map<String, Port> getPorts() {
    return ports;
  }

  @Override
  public String toString() {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("Service: name=" + name);

    if (ports != null) {
      Iterator<Port> portIterator = ports.values().iterator();

      while (portIterator.hasNext()) {
        strBuf.append("\n" + portIterator.next());
      }
    }

    String superString = super.toString();
    if (!superString.equals("")) {
      strBuf.append("\n");
      strBuf.append(superString);
    }

    return strBuf.toString();
  }

  /**
   * Get the list of local attribute names defined for this element in the WSDL specification.
   *
   * @return a List of Strings, one for each local attribute name
   */
  public List<String> getNativeAttributeNames() {
    return nativeAttributeNames;
  }
}
