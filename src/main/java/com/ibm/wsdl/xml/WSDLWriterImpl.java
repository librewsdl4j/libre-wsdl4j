/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package com.ibm.wsdl.xml;

import com.ibm.wsdl.Constants;
import com.ibm.wsdl.util.StringUtils;
import com.ibm.wsdl.util.xml.DOM2Writer;
import com.ibm.wsdl.util.xml.DOMUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import javax.wsdl.Binding;
import javax.wsdl.BindingFault;
import javax.wsdl.BindingInput;
import javax.wsdl.BindingOperation;
import javax.wsdl.BindingOutput;
import javax.wsdl.Definition;
import javax.wsdl.Fault;
import javax.wsdl.Import;
import javax.wsdl.Input;
import javax.wsdl.Message;
import javax.wsdl.Operation;
import javax.wsdl.OperationType;
import javax.wsdl.Output;
import javax.wsdl.Part;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.Types;
import javax.wsdl.WSDLException;
import javax.wsdl.extensions.AttributeExtensible;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.ExtensionRegistry;
import javax.wsdl.extensions.ExtensionSerializer;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class describes a collection of methods that allow a WSDL model to be written to a writer in an XML format that follows the WSDL schema.
 *
 * @author Matthew J. Duftler
 * @author Nirmal Mukhi
 */
public class WSDLWriterImpl implements WSDLWriter {
  /**
   * Sets the specified feature to the specified value.
   * <p>
   * There are no minimum features that must be supported.
   * <p>
   * All feature names must be fully-qualified, Java package style. All names starting with javax.wsdl. are reserved for features defined by the JWSDL specification. It is recommended that implementation- specific features be fully-qualified to match the package name of that implementation. For
   * example: com.abc.featureName
   *
   * @param name
   *          the name of the feature to be set.
   * @param value
   *          the value to set the feature to.
   * @throws IllegalArgumentException
   *           if the feature name is not recognized.
   * @see #getFeature(String)
   */
  @Override
  public void setFeature(String name, boolean value) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Feature name must not be null.");
    } else {
      throw new IllegalArgumentException("Feature name '" + name + "' not recognized.");
    }
  }

  /**
   * Gets the value of the specified feature.
   *
   * @param name
   *          the name of the feature to get the value of.
   * @return the value of the feature.
   * @throws IllegalArgumentException
   *           if the feature name is not recognized.
   * @see #setFeature(String, boolean)
   */
  public boolean getFeature(String name) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Feature name must not be null.");
    } else {
      throw new IllegalArgumentException("Feature name '" + name + "' not recognized.");
    }
  }

  protected void printDefinition(Definition def, PrintWriter pw) throws WSDLException {
    if (def == null) {
      return;
    }

    if (def.getPrefix(Constants.NS_URI_WSDL) == null) {
      String prefix = "wsdl";
      int subscript = 0;

      while (def.getNamespace(prefix) != null) {
        prefix = "wsdl" + subscript++;
      }

      def.addNamespace(prefix, Constants.NS_URI_WSDL);
    }

    String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_DEFINITIONS, def);

    pw.print('<' + tagName);

    QName name = def.getQName();
    String targetNamespace = def.getTargetNamespace();
    Map<String, String> namespaces = def.getNamespaces();

    if (name != null) {
      DOMUtils.printAttribute(Constants.ATTR_NAME, name.getLocalPart(), pw);
    }

    DOMUtils.printAttribute(Constants.ATTR_TARGET_NAMESPACE, targetNamespace, pw);

    printExtensibilityAttributes(def, def, pw);

    printNamespaceDeclarations(namespaces, pw);

    pw.println('>');

    printDocumentation(def.getDocumentationElement(), def, pw);
    printImports(def.getImports(), def, pw);
    printTypes(def.getTypes(), def, pw);
    printMessages(def.getMessages(), def, pw);
    printPortTypes(def.getPortTypes(), def, pw);
    printBindings(def.getBindings(), def, pw);
    printServices(def.getServices(), def, pw);

    List<ExtensibilityElement> extElements = def.getExtensibilityElements();

    printExtensibilityElements(Definition.class, extElements, def, pw);

    pw.println("</" + tagName + '>');

    pw.flush();
  }

  protected void printServices(Map<QName, Service> services, Definition def, PrintWriter pw) throws WSDLException {
    if (services != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_SERVICE, def);
      Iterator<Service> serviceIterator = services.values().iterator();

      while (serviceIterator.hasNext()) {
        Service service = serviceIterator.next();

        pw.print("  <" + tagName);

        QName name = service.getQName();

        if (name != null) {
          DOMUtils.printAttribute(Constants.ATTR_NAME, name.getLocalPart(), pw);
        }

        printExtensibilityAttributes(service, def, pw);

        pw.println('>');

        printDocumentation(service.getDocumentationElement(), def, pw);
        printPorts(service.getPorts(), def, pw);

        List<ExtensibilityElement> extElements = service.getExtensibilityElements();

        printExtensibilityElements(Service.class, extElements, def, pw);

        pw.println("  </" + tagName + '>');
      }
    }
  }

  protected void printPorts(Map<String, Port> ports, Definition def, PrintWriter pw) throws WSDLException {
    if (ports != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_PORT, def);
      Iterator<Port> portIterator = ports.values().iterator();

      while (portIterator.hasNext()) {
        Port port = portIterator.next();

        pw.print("    <" + tagName);

        DOMUtils.printAttribute(Constants.ATTR_NAME, port.getName(), pw);

        Binding binding = port.getBinding();

        if (binding != null) {
          DOMUtils.printQualifiedAttribute(Constants.ATTR_BINDING, binding.getQName(), def, pw);
        }

        printExtensibilityAttributes(port, def, pw);

        pw.println('>');

        printDocumentation(port.getDocumentationElement(), def, pw);

        List<ExtensibilityElement> extElements = port.getExtensibilityElements();

        printExtensibilityElements(Port.class, extElements, def, pw);

        pw.println("    </" + tagName + '>');
      }
    }
  }

  protected void printBindings(Map<QName, Binding> bindings, Definition def, PrintWriter pw) throws WSDLException {
    if (bindings != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_BINDING, def);
      Iterator<Binding> bindingIterator = bindings.values().iterator();

      while (bindingIterator.hasNext()) {
        Binding binding = bindingIterator.next();

        if (!binding.isUndefined()) {
          pw.print("  <" + tagName);

          QName name = binding.getQName();

          if (name != null) {
            DOMUtils.printAttribute(Constants.ATTR_NAME, name.getLocalPart(), pw);
          }

          PortType portType = binding.getPortType();

          if (portType != null) {
            DOMUtils.printQualifiedAttribute(Constants.ATTR_TYPE, portType.getQName(), def, pw);
          }

          pw.println('>');

          printDocumentation(binding.getDocumentationElement(), def, pw);

          List<ExtensibilityElement> extElements = binding.getExtensibilityElements();

          printExtensibilityElements(Binding.class, extElements, def, pw);

          printBindingOperations(binding.getBindingOperations(), def, pw);

          pw.println("  </" + tagName + '>');
        }
      }
    }
  }

  protected void printBindingOperations(List<BindingOperation> bindingOperations, Definition def, PrintWriter pw) throws WSDLException {
    if (bindingOperations != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_OPERATION, def);
      Iterator<BindingOperation> bindingOperationIterator = bindingOperations.iterator();

      while (bindingOperationIterator.hasNext()) {
        BindingOperation bindingOperation = bindingOperationIterator.next();

        pw.print("    <" + tagName);

        DOMUtils.printAttribute(Constants.ATTR_NAME, bindingOperation.getName(), pw);

        printExtensibilityAttributes(bindingOperation, def, pw);

        pw.println('>');

        printDocumentation(bindingOperation.getDocumentationElement(), def, pw);

        List<ExtensibilityElement> extElements = bindingOperation.getExtensibilityElements();

        printExtensibilityElements(BindingOperation.class, extElements, def, pw);

        printBindingInput(bindingOperation.getBindingInput(), def, pw);
        printBindingOutput(bindingOperation.getBindingOutput(), def, pw);
        printBindingFaults(bindingOperation.getBindingFaults(), def, pw);

        pw.println("    </" + tagName + '>');
      }
    }
  }

  protected void printBindingInput(BindingInput bindingInput, Definition def, PrintWriter pw) throws WSDLException {
    if (bindingInput != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_INPUT, def);

      pw.print("      <" + tagName);

      DOMUtils.printAttribute(Constants.ATTR_NAME, bindingInput.getName(), pw);

      printExtensibilityAttributes(bindingInput, def, pw);

      pw.println('>');

      printDocumentation(bindingInput.getDocumentationElement(), def, pw);

      List<ExtensibilityElement> extElements = bindingInput.getExtensibilityElements();

      printExtensibilityElements(BindingInput.class, extElements, def, pw);

      pw.println("      </" + tagName + '>');
    }
  }

  protected void printBindingOutput(BindingOutput bindingOutput, Definition def, PrintWriter pw) throws WSDLException {
    if (bindingOutput != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_OUTPUT, def);

      pw.print("      <" + tagName);

      DOMUtils.printAttribute(Constants.ATTR_NAME, bindingOutput.getName(), pw);

      pw.println('>');

      printDocumentation(bindingOutput.getDocumentationElement(), def, pw);

      List<ExtensibilityElement> extElements = bindingOutput.getExtensibilityElements();

      printExtensibilityElements(BindingOutput.class, extElements, def, pw);

      pw.println("      </" + tagName + '>');
    }
  }

  protected void printBindingFaults(Map<String, BindingFault> bindingFaults, Definition def, PrintWriter pw) throws WSDLException {
    if (bindingFaults != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_FAULT, def);
      Iterator<BindingFault> bindingFaultIterator = bindingFaults.values().iterator();

      while (bindingFaultIterator.hasNext()) {
        BindingFault bindingFault = bindingFaultIterator.next();

        pw.print("      <" + tagName);

        DOMUtils.printAttribute(Constants.ATTR_NAME, bindingFault.getName(), pw);

        printExtensibilityAttributes(bindingFault, def, pw);

        pw.println('>');

        printDocumentation(bindingFault.getDocumentationElement(), def, pw);

        List<ExtensibilityElement> extElements = bindingFault.getExtensibilityElements();

        printExtensibilityElements(BindingFault.class, extElements, def, pw);

        pw.println("      </" + tagName + '>');
      }
    }
  }

  protected void printPortTypes(Map<QName, PortType> portTypes, Definition def, PrintWriter pw) throws WSDLException {
    if (portTypes != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_PORT_TYPE, def);
      Iterator<PortType> portTypeIterator = portTypes.values().iterator();

      while (portTypeIterator.hasNext()) {
        PortType portType = portTypeIterator.next();

        if (!portType.isUndefined()) {
          pw.print("  <" + tagName);

          QName name = portType.getQName();

          if (name != null) {
            DOMUtils.printAttribute(Constants.ATTR_NAME, name.getLocalPart(), pw);
          }

          printExtensibilityAttributes(portType, def, pw);

          pw.println('>');

          printDocumentation(portType.getDocumentationElement(), def, pw);
          printOperations(portType.getOperations(), def, pw);

          List<ExtensibilityElement> extElements = portType.getExtensibilityElements();
          printExtensibilityElements(PortType.class, extElements, def, pw);

          pw.println("  </" + tagName + '>');
        }
      }
    }
  }

  protected void printOperations(List<Operation> operations, Definition def, PrintWriter pw) throws WSDLException {
    if (operations != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_OPERATION, def);
      Iterator<Operation> operationIterator = operations.iterator();

      while (operationIterator.hasNext()) {
        Operation operation = operationIterator.next();

        if (!operation.isUndefined()) {
          pw.print("    <" + tagName);

          DOMUtils.printAttribute(Constants.ATTR_NAME, operation.getName(), pw);
          DOMUtils.printAttribute(Constants.ATTR_PARAMETER_ORDER, StringUtils.getNMTokens(operation.getParameterOrdering()), pw);

          printExtensibilityAttributes(operation, def, pw);

          pw.println('>');

          printDocumentation(operation.getDocumentationElement(), def, pw);

          OperationType operationType = operation.getStyle();

          if (operationType == OperationType.ONE_WAY) {
            printInput(operation.getInput(), def, pw);
          } else if (operationType == OperationType.SOLICIT_RESPONSE) {
            printOutput(operation.getOutput(), def, pw);
            printInput(operation.getInput(), def, pw);
          } else if (operationType == OperationType.NOTIFICATION) {
            printOutput(operation.getOutput(), def, pw);
          } else {
            // Must be OperationType.REQUEST_RESPONSE.
            printInput(operation.getInput(), def, pw);
            printOutput(operation.getOutput(), def, pw);
          }

          printFaults(operation.getFaults(), def, pw);

          List<ExtensibilityElement> extElements = operation.getExtensibilityElements();

          printExtensibilityElements(Operation.class, extElements, def, pw);

          pw.println("    </" + tagName + '>');
        }
      }
    }
  }

  protected void printInput(Input input, Definition def, PrintWriter pw) throws WSDLException {
    if (input != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_INPUT, def);

      pw.print("      <" + tagName);

      DOMUtils.printAttribute(Constants.ATTR_NAME, input.getName(), pw);

      Message message = input.getMessage();

      if (message != null) {
        DOMUtils.printQualifiedAttribute(Constants.ATTR_MESSAGE, message.getQName(), def, pw);
      }

      printExtensibilityAttributes(input, def, pw);

      pw.println('>');

      printDocumentation(input.getDocumentationElement(), def, pw);

      List<ExtensibilityElement> extElements = input.getExtensibilityElements();

      printExtensibilityElements(Input.class, extElements, def, pw);

      pw.println("    </" + tagName + '>');
    }
  }

  protected void printOutput(Output output, Definition def, PrintWriter pw) throws WSDLException {
    if (output != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_OUTPUT, def);

      pw.print("      <" + tagName);

      DOMUtils.printAttribute(Constants.ATTR_NAME, output.getName(), pw);

      Message message = output.getMessage();

      if (message != null) {
        DOMUtils.printQualifiedAttribute(Constants.ATTR_MESSAGE, message.getQName(), def, pw);
      }

      printExtensibilityAttributes(output, def, pw);

      pw.println('>');

      printDocumentation(output.getDocumentationElement(), def, pw);

      List<ExtensibilityElement> extElements = output.getExtensibilityElements();

      printExtensibilityElements(Output.class, extElements, def, pw);

      pw.println("    </" + tagName + '>');
    }
  }

  protected void printFaults(Map<String, Fault> faults, Definition def, PrintWriter pw) throws WSDLException {
    if (faults != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_FAULT, def);
      Iterator<Fault> faultIterator = faults.values().iterator();

      while (faultIterator.hasNext()) {
        Fault fault = faultIterator.next();

        pw.print("      <" + tagName);

        DOMUtils.printAttribute(Constants.ATTR_NAME, fault.getName(), pw);

        Message message = fault.getMessage();

        if (message != null) {
          DOMUtils.printQualifiedAttribute(Constants.ATTR_MESSAGE, message.getQName(), def, pw);
        }

        printExtensibilityAttributes(fault, def, pw);

        pw.println('>');

        printDocumentation(fault.getDocumentationElement(), def, pw);

        List<ExtensibilityElement> extElements = fault.getExtensibilityElements();

        printExtensibilityElements(Fault.class, extElements, def, pw);

        pw.println("    </" + tagName + '>');
      }
    }
  }

  protected void printMessages(Map<QName, Message> messages, Definition def, PrintWriter pw) throws WSDLException {
    if (messages != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_MESSAGE, def);
      Iterator<Message> messageIterator = messages.values().iterator();

      while (messageIterator.hasNext()) {
        Message message = messageIterator.next();

        if (!message.isUndefined()) {
          pw.print("  <" + tagName);

          QName name = message.getQName();

          if (name != null) {
            DOMUtils.printAttribute(Constants.ATTR_NAME, name.getLocalPart(), pw);
          }

          printExtensibilityAttributes(message, def, pw);

          pw.println('>');

          printDocumentation(message.getDocumentationElement(), def, pw);
          printParts(message.getOrderedParts(null), def, pw);

          List<ExtensibilityElement> extElements = message.getExtensibilityElements();

          printExtensibilityElements(Message.class, extElements, def, pw);

          pw.println("  </" + tagName + '>');
        }
      }
    }
  }

  protected void printParts(List<Part> parts, Definition def, PrintWriter pw) throws WSDLException {
    if (parts != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_PART, def);
      Iterator<Part> partIterator = parts.iterator();

      while (partIterator.hasNext()) {
        Part part = partIterator.next();

        pw.print("    <" + tagName);

        DOMUtils.printAttribute(Constants.ATTR_NAME, part.getName(), pw);
        DOMUtils.printQualifiedAttribute(Constants.ATTR_ELEMENT, part.getElementName(), def, pw);
        DOMUtils.printQualifiedAttribute(Constants.ATTR_TYPE, part.getTypeName(), def, pw);

        printExtensibilityAttributes(part, def, pw);

        pw.println('>');

        printDocumentation(part.getDocumentationElement(), def, pw);

        List<ExtensibilityElement> extElements = part.getExtensibilityElements();

        printExtensibilityElements(Part.class, extElements, def, pw);

        pw.println("    </" + tagName + '>');
      }
    }
  }

  protected void printExtensibilityAttributes(AttributeExtensible attrExt, Definition def, PrintWriter pw) throws WSDLException {
    Map<QName, Object> extensionAttributes = attrExt.getExtensionAttributes();
    for (Map.Entry<QName, Object> attribute : extensionAttributes.entrySet()) {
      QName attrName = attribute.getKey();
      Object attrValue = attribute.getValue();
      String attrStrValue = null;
      QName attrQNameValue = null;

      if (attrValue instanceof String) {
        attrStrValue = (String) attrValue;
      } else if (attrValue instanceof QName) {
        attrQNameValue = (QName) attrValue;
      } else if (attrValue instanceof List) {
        List<?> attrValueList = (List<?>) attrValue;
        int size = attrValueList.size();

        if (size > 0) {
          Object tempAttrVal = attrValueList.get(0);

          if (tempAttrVal instanceof String) {
            attrStrValue = StringUtils.getNMTokens((List<String>) attrValueList);
          } else if (tempAttrVal instanceof QName) {
            StringBuilder strBuf = new StringBuilder();

            for (int i = 0; i < size; i++) {
              QName tempQName = (QName) attrValueList.get(i);

              strBuf.append((i > 0 ? " " : "") + DOMUtils.getQualifiedValue(tempQName.getNamespaceURI(), tempQName.getLocalPart(), def));
            }

            attrStrValue = strBuf.toString();
          } else {
            throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Unknown type of extension attribute '" + attrName + "': " + tempAttrVal.getClass().getName());
          }
        } else {
          attrStrValue = "";
        }
      } else {
        throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Unknown type of extension attribute '" + attrName + "': " + attrValue.getClass().getName());
      }

      if (attrQNameValue != null) {
        DOMUtils.printQualifiedAttribute(attrName, attrQNameValue, def, pw);
      } else {
        DOMUtils.printQualifiedAttribute(attrName, attrStrValue, def, pw);
      }
    }
  }

  protected void printDocumentation(Element docElement, Definition def, PrintWriter pw) {
    if (docElement != null) {
      DOM2Writer.serializeAsXML(docElement, def.getNamespaces(), pw);
      pw.println();
    }
  }

  protected void printTypes(Types types, Definition def, PrintWriter pw) throws WSDLException {
    if (types != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_TYPES, def);
      pw.print("  <" + tagName);

      printExtensibilityAttributes(types, def, pw);

      pw.println('>');

      printDocumentation(types.getDocumentationElement(), def, pw);

      List<ExtensibilityElement> extElements = types.getExtensibilityElements();

      printExtensibilityElements(Types.class, extElements, def, pw);

      pw.println("  </" + tagName + '>');
    }
  }

  protected void printImports(Map<String, List<Import>> imports, Definition def, PrintWriter pw) throws WSDLException {
    if (imports != null) {
      String tagName = DOMUtils.getQualifiedValue(Constants.NS_URI_WSDL, Constants.ELEM_IMPORT, def);
      Iterator<List<Import>> importListIterator = imports.values().iterator();

      while (importListIterator.hasNext()) {
        List<Import> importList = importListIterator.next();
        Iterator<Import> importIterator = importList.iterator();

        while (importIterator.hasNext()) {
          Import importDef = importIterator.next();

          pw.print("  <" + tagName);

          DOMUtils.printAttribute(Constants.ATTR_NAMESPACE, importDef.getNamespaceURI(), pw);
          DOMUtils.printAttribute(Constants.ATTR_LOCATION, importDef.getLocationURI(), pw);

          printExtensibilityAttributes(importDef, def, pw);

          pw.println('>');

          printDocumentation(importDef.getDocumentationElement(), def, pw);

          List<ExtensibilityElement> extElements = importDef.getExtensibilityElements();

          printExtensibilityElements(Import.class, extElements, def, pw);

          pw.println("    </" + tagName + '>');
        }
      }
    }
  }

  protected void printNamespaceDeclarations(Map<String, String> namespaces, PrintWriter pw) {
    if (namespaces != null) {
      Set<String> keys = namespaces.keySet();
      Iterator<String> keyIterator = keys.iterator();

      while (keyIterator.hasNext()) {
        String prefix = keyIterator.next();

        if (prefix == null) {
          prefix = "";
        }

        DOMUtils.printAttribute(Constants.ATTR_XMLNS + (!prefix.equals("") ? ":" + prefix : ""), namespaces.get(prefix), pw);
      }
    }
  }

  protected void printExtensibilityElements(Class<?> parentType, List<ExtensibilityElement> extensibilityElements, Definition def, PrintWriter pw) throws WSDLException {
    if (extensibilityElements != null) {
      Iterator<ExtensibilityElement> extensibilityElementIterator = extensibilityElements.iterator();

      while (extensibilityElementIterator.hasNext()) {
        ExtensibilityElement ext = extensibilityElementIterator.next();
        QName elementType = ext.getElementType();
        ExtensionRegistry extReg = def.getExtensionRegistry();

        if (extReg == null) {
          throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "No ExtensionRegistry set for this " + "Definition, so unable to serialize a '" + elementType + "' element in the context of a '" + parentType.getName() + "'.");
        }

        // If the wsdl was parsed using the parseSchema feature set to false
        // then the extensibility will be an UnknownExtensibilityElement rather
        // than a schema. Serialize this using the default serializer.
        ExtensionSerializer extSer;
        if (ext instanceof UnknownExtensibilityElement) {
          extSer = extReg.getDefaultSerializer();
        } else {
          extSer = extReg.querySerializer(parentType, elementType);
        }
        extSer.marshall(parentType, elementType, ext, pw, def, extReg);
      }
    }
  }

  private static Document getDocument(InputSource inputSource, String desc) throws WSDLException {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    factory.setNamespaceAware(true);
    factory.setValidating(false);

    try {
      DocumentBuilder builder = factory.newDocumentBuilder();
      return builder.parse(inputSource);
    } catch (RuntimeException e) {
      throw e;
    } catch (Exception e) {
      throw new WSDLException(WSDLException.PARSER_ERROR, "Problem parsing '" + desc + "'.", e);
    }
  }

  /**
   * Return a document generated from the specified WSDL model.
   */
  public Document getDocument(Definition wsdlDef) throws WSDLException {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);

    writeWSDL(wsdlDef, pw);

    StringReader sr = new StringReader(sw.toString());
    InputSource is = new InputSource(sr);

    return getDocument(is, "- WSDL Document -");
  }

  /**
   * Write the specified WSDL definition to the specified Writer.
   *
   * @param wsdlDef
   *          the WSDL definition to be written.
   * @param sink
   *          the Writer to write the xml to.
   */
  public void writeWSDL(Definition wsdlDef, Writer sink) throws WSDLException {
    PrintWriter pw = new PrintWriter(sink);
    String javaEncoding = (sink instanceof OutputStreamWriter) ? ((OutputStreamWriter) sink).getEncoding() : null;

    String xmlEncoding = DOM2Writer.java2XMLEncoding(javaEncoding);

    if (xmlEncoding == null) {
      throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Unsupported Java encoding for writing " + "wsdl file: '" + javaEncoding + "'.");
    }

    pw.println(Constants.XML_DECL_START + xmlEncoding + Constants.XML_DECL_END);

    printDefinition(wsdlDef, pw);
  }

  /**
   * Write the specified WSDL definition to the specified OutputStream.
   *
   * @param wsdlDef
   *          the WSDL definition to be written.
   * @param sink
   *          the OutputStream to write the xml to.
   */
  public void writeWSDL(Definition wsdlDef, OutputStream sink) throws WSDLException {
    Writer writer = null;

    writer = new OutputStreamWriter(sink, StandardCharsets.UTF_8);

    writeWSDL(wsdlDef, writer);
  }

  /**
   * A test driver. <code>
   * 
   * <pre>
   * Usage:
   * </pre>
   * <p>
   * 
   * <pre>
   *   java com.ibm.wsdl.xml.WSDLWriterImpl filename|URL
   * </pre>
   * <p>
   * 
   * <pre>
   *     This test driver simply reads a WSDL document into a model
   *    (using a WSDLReader), and then serializes it back to
   *    standard out. In effect, it performs a round-trip test on
   *    the specified WSDL document.
   * </pre>
   */
  public static void main(String[] argv) throws WSDLException {
    if (argv.length == 1) {
      WSDLFactory wsdlFactory = WSDLFactory.newInstance();
      WSDLReader wsdlReader = wsdlFactory.newWSDLReader();
      WSDLWriter wsdlWriter = wsdlFactory.newWSDLWriter();

      wsdlWriter.writeWSDL(wsdlReader.readWSDL(null, argv[0]), System.out);
    } else {
      System.err.println("Usage:");
      System.err.println();
      System.err.println("  java " + WSDLWriterImpl.class.getName() + " filename|URL");
      System.err.println();
      System.err.println("This test driver simply reads a WSDL document into a model (using a WSDLReader), and then serializes it back to standard out. In effect, it performs a round-trip test on the specified " + "WSDL document.");
    }
  }
}
