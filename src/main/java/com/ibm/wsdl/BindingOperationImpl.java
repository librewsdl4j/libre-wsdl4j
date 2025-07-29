/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl;

import javax.wsdl.BindingFault;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Operation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * This class represents a WSDL operation binding. That is, it holds the information that would be specified in the operation element contained within a binding element.
 *
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public class BindingOperationImpl extends AbstractWSDLElement implements BindingOperation {
  protected String name = null;
  protected Operation operation = null;
  protected BindingInput bindingInput = null;
  protected BindingOutput bindingOutput = null;
  private final Map<String, BindingFault> bindingFaults = new HashMap<>();
  private final List<String> nativeAttributeNames = Arrays.asList(Constants.BINDING_OPERATION_ATTR_NAMES);

  public static final long serialVersionUID = 1;

  /**
   * Set the name of this operation binding.
   *
   * @param name
   *          the desired name
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Get the name of this operation binding.
   *
   * @return the operation binding name
   */
  public String getName() {
    return name;
  }

  /**
   * Set the operation that this operation binding binds.
   *
   * @param operation
   *          the operation this operation binding binds
   */
  @Override
  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  /**
   * Get the operation that this operation binding binds.
   *
   * @return the operation that this operation binding binds
   */
  public Operation getOperation() {
    return operation;
  }

  /**
   * Set the input binding for this operation binding.
   *
   * @param bindingInput
   *          the new input binding
   */
  @Override
  public void setBindingInput(BindingInput bindingInput) {
    this.bindingInput = bindingInput;
  }

  /**
   * Get the input binding for this operation binding.
   *
   * @return the input binding
   */
  public BindingInput getBindingInput() {
    return bindingInput;
  }

  /**
   * Set the output binding for this operation binding.
   *
   * @param bindingOutput
   *          the new output binding
   */
  @Override
  public void setBindingOutput(BindingOutput bindingOutput) {
    this.bindingOutput = bindingOutput;
  }

  /**
   * Get the output binding for this operation binding.
   *
   * @return the output binding for the operation binding
   */
  public BindingOutput getBindingOutput() {
    return bindingOutput;
  }

  /**
   * Add a fault binding.
   *
   * @param bindingFault
   *          the new fault binding
   */
  public void addBindingFault(BindingFault bindingFault) {
    bindingFaults.put(bindingFault.getName(), bindingFault);
  }

  /**
   * Get the specified fault binding.
   *
   * @param name
   *          the name of the desired fault binding.
   * @return the corresponding fault binding, or null if there wasn't any matching fault binding
   */
  public BindingFault getBindingFault(String name) {
    return bindingFaults.get(name);
  }

  /**
   * Remove the specified fault binding.
   *
   * @param name
   *          the name of the fault binding to be removed.
   * @return the fault binding which was removed
   */
  @Override
  public BindingFault removeBindingFault(String name) {
    return bindingFaults.remove(name);
  }

  /**
   * Get all the fault bindings associated with this operation binding.
   *
   * @return names of fault bindings
   */
  public Map<String, BindingFault> getBindingFaults() {
    return bindingFaults;
  }

  @Override
  public String toString() {
    StringBuilder strBuf = new StringBuilder();

    strBuf.append("BindingOperation: name=" + name);

    if (bindingInput != null) {
      strBuf.append("\n" + bindingInput);
    }

    if (bindingOutput != null) {
      strBuf.append("\n" + bindingOutput);
    }

    if (bindingFaults != null) {
      Iterator<BindingFault> faultIterator = bindingFaults.values().iterator();

      while (faultIterator.hasNext()) {
        strBuf.append("\n" + faultIterator.next());
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
