/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl;

import java.util.*;
import javax.wsdl.extensions.*;
import javax.xml.namespace.*;

/**
 * This interface represents a WSDL definition.
 *
 * @author Paul Fremantle
 * @author Nirmal Mukhi
 * @author Matthew J. Duftler
 */
public interface Definition extends WSDLElement
{
  /**
   * Set the document base URI of this definition. Can be used to
   * represent the origin of the Definition, and can be exploited
   * when resolving relative URIs (e.g. in &lt;import&gt;s).
   *
   * @param documentBaseURI the document base URI of this definition
   */
  void setDocumentBaseURI(String documentBaseURI);

  /**
   * Get the document base URI of this definition.
   *
   * @return the document base URI
   */
  String getDocumentBaseURI();

  /**
   * Set the name of this definition.
   *
   * @param name the desired name
   */
  void setQName(QName name);

  /**
   * Get the name of this definition.
   *
   * @return the definition name
   */
  QName getQName();

  /**
   * Set the target namespace in which WSDL elements are defined.
   *
   * @param targetNamespace the target namespace
   */
  void setTargetNamespace(String targetNamespace);

  /**
   * Get the target namespace in which the WSDL elements
   * are defined.
   *
   * @return the target namespace
   */
  String getTargetNamespace();

  /**
   * This is a way to add a namespace association to a definition.
   * It is similar to adding a namespace prefix declaration to the
   * top of a &lt;wsdl:definition&gt; element. This has nothing to do
   * with the &lt;wsdl:import&gt; element; there are separate methods for
   * dealing with information described by &lt;wsdl:import&gt; elements.
   *
   * @param prefix the prefix to use for this namespace (when
   * rendering this information as XML). Use null or an empty string
   * to describe the default namespace (i.e. xmlns="...").
   * @param namespaceURI the namespace URI to associate the prefix
   * with. If you use null, the namespace association will be removed.
   */
  void addNamespace(String prefix, String namespaceURI);

   /**
    * Get the namespace URI associated with this prefix. Or null if
    * there is no namespace URI associated with this prefix. This is
    * unrelated to the &lt;wsdl:import&gt; element.
    *
    * @see #addNamespace(String, String)
    * @see #getPrefix(String)
    */
   String getNamespace(String prefix);
   
   /**
    * Remove the namespace URI associated with this prefix.
    * 
    * @param prefix the prefix of the namespace to be removed.
    * @return the namespace URI which was removed.
    */
   String removeNamespace(String prefix);

   /**
    * Get a prefix associated with this namespace URI. Or null if
    * there are no prefixes associated with this namespace URI. This is
    * unrelated to the &lt;wsdl:import&gt; element.
    *
    * @see #addNamespace(String, String)
    * @see #getNamespace(String)
    */
   String getPrefix(String namespaceURI);

   /**
    * Get all namespace associations in this definition. The keys are
    * the prefixes, and the namespace URIs are the values. This is
    * unrelated to the &lt;wsdl:import&gt; element.
    *
    * @see #addNamespace(String, String)
    */
   Map getNamespaces();

  /**
   * Set the types section.
   */
  void setTypes(Types types);

  /**
   * Get the types section.
   *
   * @return the types section
   */
  Types getTypes();

  /**
   * Add an import to this WSDL description.
   *
   * @param importDef the import to be added
   */
  void addImport(Import importDef);

  /**
   * Remove an import from this WSDL description.
   *
   * @param importDef the import to be removed
   * @return the removed Import
   */
  Import removeImport(Import importDef);
  
  /**
   * Get the list of imports for the specified namespaceURI.
   *
   * @param namespaceURI the namespaceURI associated with the
   * desired imports.
   * @return a list of the corresponding imports, or null if
   * there weren't any matching imports
   */
  List getImports(String namespaceURI);

  /**
   * Get a map of lists containing all the imports defined here.
   * The map's keys are the namespaceURIs, and the map's values
   * are lists. There is one list for each namespaceURI for which
   * imports have been defined.
   */
  Map getImports();

  /**
   * Add a message to this WSDL description.
   *
   * @param message the message to be added
   */
  void addMessage(Message message);

  /**
   * Get the specified message. Also checks imported documents.
   *
   * @param name the name of the desired message.
   * @return the corresponding message, or null if there wasn't
   * any matching message
   */
  Message getMessage(QName name);

  /**
   * Remove the specified message from this definition.
   *
   * @param name the name of the message to remove
   * @return the message previously associated with this qname, if there
   * was one; may return null
   */
  Message removeMessage(QName name);

  /**
   * Get all the messages defined here.
   */
  Map getMessages();

  /**
   * Add a binding to this WSDL description.
   *
   * @param binding the binding to be added
   */
  void addBinding(Binding binding);

  /**
   * Get the specified binding. Also checks imported documents.
   *
   * @param name the name of the desired binding.
   * @return the corresponding binding, or null if there wasn't
   * any matching binding
   */
  Binding getBinding(QName name);

  /**
   * Remove the specified binding from this definition.
   *
   * @param name the name of the binding to remove
   * @return the binding previously associated with this qname, if there
   * was one; may return null
   */
  Binding removeBinding(QName name);

  /**
   * Get all the bindings defined in this Definition.
   */
  Map getBindings();
  
  /**
   * Get all the bindings defined in this Definition and
   * those in any imported Definitions down the WSDL tree.
   */
  Map getAllBindings();

  /**
   * Add a portType to this WSDL description.
   *
   * @param portType the portType to be added
   */
  void addPortType(PortType portType);

  /**
   * Get the specified portType. Also checks imported documents.
   *
   * @param name the name of the desired portType.
   * @return the corresponding portType, or null if there wasn't
   * any matching portType
   */
  PortType getPortType(QName name);

  /**
   * Remove the specified portType from this definition.
   *
   * @param name the name of the portType to remove
   * @return the portType previously associated with this qname, if there
   * was one; may return null
   */
  PortType removePortType(QName name);

  /**
   * Get all the portTypes defined in this Definition.
   */
  Map getPortTypes();

  /**
   * Get all the portTypes defined in this Definition and
   * those in any imported Definitions down the WSDL tree.
   */
  Map getAllPortTypes();
  
  /**
   * Add a service to this WSDL description.
   *
   * @param service the service to be added
   */
  void addService(Service service);

  /**
   * Get the specified service. Also checks imported documents.
   *
   * @param name the name of the desired service.
   * @return the corresponding service, or null if there wasn't
   * any matching service
   */
  Service getService(QName name);

  /**
   * Remove the specified service from this definition.
   *
   * @param name the name of the service to remove
   * @return the service previously associated with this qname, if there
   * was one; may return null
   */
  Service removeService(QName name);

  /**
   * Get all the services defined in this Definition.
   */
  Map getServices();

  /**
   * Get all the services defined in this Definition and
   * those in any imported Definitions down the WSDL tree.
   */
  Map getAllServices();
  
  /**
   * Create a new binding.
   *
   * @return the newly created binding
   */
  Binding createBinding();

  /**
   * Create a new binding fault.
   *
   * @return the newly created binding fault
   */
  BindingFault createBindingFault();

  /**
   * Create a new binding input.
   *
   * @return the newly created binding input
   */
  BindingInput createBindingInput();

  /**
   * Create a new binding operation.
   *
   * @return the newly created binding operation
   */
  BindingOperation createBindingOperation();

  /**
   * Create a new binding output.
   *
   * @return the newly created binding output
   */
  BindingOutput createBindingOutput();

  /**
   * Create a new fault.
   *
   * @return the newly created fault
   */
  Fault createFault();

  /**
   * Create a new import.
   *
   * @return the newly created import
   */
  Import createImport();

  /**
   * Create a new input.
   *
   * @return the newly created input
   */
  Input createInput();

  /**
   * Create a new message.
   *
   * @return the newly created message
   */
  Message createMessage();

  /**
   * Create a new operation.
   *
   * @return the newly created operation
   */
  Operation createOperation();

  /**
   * Create a new output.
   *
   * @return the newly created output
   */
  Output createOutput();

  /**
   * Create a new part.
   *
   * @return the newly created part
   */
  Part createPart();

  /**
   * Create a new port.
   *
   * @return the newly created port
   */
  Port createPort();

  /**
   * Create a new port type.
   *
   * @return the newly created port type
   */
  PortType createPortType();

  /**
   * Create a new service.
   *
   * @return the newly created service
   */
  Service createService();

  /**
   * Create a new types section.
   *
   * @return the newly created types section
   */
  Types createTypes();

  /**
   * Get a reference to the ExtensionRegistry for this Definition.
   */
  ExtensionRegistry getExtensionRegistry();

  /**
   * Set the ExtensionRegistry for this Definition.
   */
  void setExtensionRegistry(ExtensionRegistry extReg);
}