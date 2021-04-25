/*
 * (c) Copyright IBM Corp 2004, 2006 
 */

package javax.wsdl.extensions;

import java.util.List;

/**
 * Classes that implement this interface can contain extensibility
 * elements.
 * 
 * @author John Kaputin
 */
public interface ElementExtensible {
    
    /**
     * Add an extensibility element.
     *
     * @param extElement the extensibility element to be added
     */
    void addExtensibilityElement(ExtensibilityElement extElement);
    
    /**
     * Remove an extensibility element.
     *
     * @param extElement the extensibility element to be removed
     * @return the extensibility element which was removed
     */
    ExtensibilityElement removeExtensibilityElement(ExtensibilityElement extElement);

    /**
     * Get all the extensibility elements defined here.
     */
    List<ExtensibilityElement> getExtensibilityElements();


}
