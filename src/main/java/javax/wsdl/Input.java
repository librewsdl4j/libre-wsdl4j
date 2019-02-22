/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl;

/**
 * This interface represents an input message, and contains the name
 * of the input and the message itself.
 *
 * @author Matthew J. Duftler
 */
public interface Input extends WSDLElement
{
  /**
   * Set the name of this input message.
   *
   * @param name the desired name
   */
  void setName(String name);

  /**
   * Get the name of this input message.
   *
   * @return the input message name
   */
  String getName();

  void setMessage(Message message);

  Message getMessage();
}