/*
 * (c) Copyright IBM Corp 2001, 2006 
 */

package javax.wsdl.factory;

import java.io.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;
import javax.wsdl.*;
import javax.wsdl.extensions.*;
import javax.wsdl.xml.*;

/**
 * This abstract class defines a factory API that enables applications to obtain a WSDLFactory capable of producing new Definitions, new WSDLReaders, and new WSDLWriters.
 * 
 * Some ideas used here have been shamelessly copied from the wonderful JAXP and Xerces work.
 *
 * @author Matthew J. Duftler (duftler@us.ibm.com)
 */
public abstract class WSDLFactory {
  private static final String PROPERTY_NAME = "javax.wsdl.factory.WSDLFactory";
  private static final String PROPERTY_FILE_NAME = "wsdl.properties";
  private static final String META_INF_SERVICES_PROPERTY_FILE_NAME = "javax.wsdl.factory.WSDLFactory";
  private static final String DEFAULT_FACTORY_IMPL_NAME = "com.ibm.wsdl.factory.WSDLFactoryImpl";

  private static String fullPropertyFileName = null;
  private static String metaInfServicesFullPropertyFileName = null;

  /**
   * Get a new instance of a WSDLFactory. This method follows (almost) the same basic sequence of steps that JAXP follows to determine the fully-qualified class name of the class which implements WSDLFactory.
   * <p>
   * The steps in order are:
   * <ol>
   * <li>Check the property file META-INF/services/javax.wsdl.factory.WSDLFactory.</li>
   * <li>Check the javax.wsdl.factory.WSDLFactory system property.</li>
   * <li>Check the lib/wsdl.properties file in the JRE directory. The key will have the same name as the above system property.</li>
   * <li>Use the default class name provided by the implementation.</li>
   * </ol>
   * <p>
   * Once an instance of a WSDLFactory is obtained, invoke newDefinition(), newWSDLReader(), or newWSDLWriter(), to create the desired instances.
   */
  public static WSDLFactory newInstance() throws WSDLException {
    String factoryImplName = findFactoryImplName();

    return newInstance(factoryImplName);
  }

  /**
   * Get a new instance of a WSDLFactory. This method returns an instance of the class factoryImplName. Once an instance of a WSDLFactory is obtained, invoke newDefinition(), newWSDLReader(), or newWSDLWriter(), to create the desired instances.
   *
   * @param factoryImplName
   *          the fully-qualified class name of the class which provides a concrete implementation of the abstract class WSDLFactory.
   */
  public static WSDLFactory newInstance(String factoryImplName) throws WSDLException {
    if (factoryImplName != null) {
      try {
        Class<?> cl = Class.forName(factoryImplName);

        return (WSDLFactory) cl.getDeclaredConstructor().newInstance();
      } catch (Exception e) {
        /*
         * Catches: ClassNotFoundException InstantiationException IllegalAccessException
         */
        throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Problem instantiating factory implementation.", e);
      }
    } else {
      throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Unable to find name of factory implementation.");
    }
  }

  /**
   * Get a new instance of a WSDLFactory. This method returns an instance of the class factoryImplName, using the specified ClassLoader. Once an instance of a WSDLFactory is obtained, invoke newDefinition(), newWSDLReader(), or newWSDLWriter(), to create the desired instances.
   *
   * @param factoryImplName
   *          the fully-qualified class name of the class which provides a concrete implementation of the abstract class WSDLFactory.
   * @param classLoader
   *          the ClassLoader to use to load the WSDLFactory implementation.
   */
  public static WSDLFactory newInstance(String factoryImplName, ClassLoader classLoader) throws WSDLException {
    if (factoryImplName != null) {
      try {
        Class<?> cl = classLoader.loadClass(factoryImplName);

        return (WSDLFactory) cl.getDeclaredConstructor().newInstance();
      } catch (Exception e) {
        /*
         * Catches: ClassNotFoundException InstantiationException IllegalAccessException
         */
        throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Problem instantiating factory " + "implementation.", e);
      }
    } else {
      throw new WSDLException(WSDLException.CONFIGURATION_ERROR, "Unable to find name of factory " + "implementation.");
    }
  }

  /**
   * Create a new instance of a Definition.
   */
  public abstract Definition newDefinition();

  /**
   * Create a new instance of a WSDLReader.
   */
  public abstract WSDLReader newWSDLReader();

  /**
   * Create a new instance of a WSDLWriter.
   */
  public abstract WSDLWriter newWSDLWriter();

  /**
   * Create a new instance of an ExtensionRegistry with pre-registered serializers/deserializers for the SOAP, HTTP and MIME extensions. Java extensionTypes are also mapped for all the SOAP, HTTP and MIME extensions.
   */
  public abstract ExtensionRegistry newPopulatedExtensionRegistry();

  private static PrivilegedAction<InputStream> getMetaInfServicesAsStream(String fileName) {
    return (() -> WSDLFactory.class.getResourceAsStream(fileName));
  }

  private static String findFactoryImplName() {
    String factoryImplName = null;

    // First, check the META-INF/services property file.
    final String metaInfServicesPropFileName = getMetaInfFullPropertyFileName();

    try (InputStream is = AccessController.doPrivileged(getMetaInfServicesAsStream(metaInfServicesPropFileName))) {

      if (is != null) {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        factoryImplName = br.readLine();

        br.close();
        isr.close();
      }

      if (factoryImplName != null) {
        return factoryImplName;
      }
    } catch (IOException e) {
      factoryImplName = null;
    }

    // Second, check the system property.
    try {
      factoryImplName = System.getProperty(PROPERTY_NAME);

      if (factoryImplName != null) {
        return factoryImplName;
      }
    } catch (SecurityException e) {
      factoryImplName = null;
    }

    // Third, check the properties file.
    String propFileName = getFullPropertyFileName();

    if (propFileName != null) {
      try (FileInputStream fis = new FileInputStream(new File(propFileName))) {
        Properties properties = new Properties();
        properties.load(fis);
        factoryImplName = properties.getProperty(PROPERTY_NAME);

        if (factoryImplName != null) {
          return factoryImplName;
        }
      } catch (IOException e) {
        factoryImplName = null;
      }
    }

    // Fourth, return the default.
    return DEFAULT_FACTORY_IMPL_NAME;
  }

  private static String getFullPropertyFileName() {
    if (fullPropertyFileName == null) {
      try {
        String javaHome = System.getProperty("java.home");

        fullPropertyFileName = javaHome + File.separator + "lib" + File.separator + PROPERTY_FILE_NAME;
      } catch (SecurityException e) {
      }
    }

    return fullPropertyFileName;
  }

  private static String getMetaInfFullPropertyFileName() {
    if (metaInfServicesFullPropertyFileName == null) {
      String metaInfServices = "/META-INF/services/";
      metaInfServicesFullPropertyFileName = metaInfServices + META_INF_SERVICES_PROPERTY_FILE_NAME;
    }

    return metaInfServicesFullPropertyFileName;
  }
}