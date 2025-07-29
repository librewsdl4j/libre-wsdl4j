import com.ibm.wsdl.extensions.soap.SOAPBindingImpl;
import org.junit.jupiter.api.Test;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.factory.WSDLFactory;
import javax.xml.namespace.QName;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SOAPBindingTest {

    @Test
    public void testSOAPBindingAttributes() throws Exception {
        WSDLFactory factory = WSDLFactory.newInstance();
        Definition definition = factory.newDefinition();

        // Create and add a SOAP binding to the definition
        SOAPBinding soapBinding = new SOAPBindingImpl();
        soapBinding.setTransportURI("http://schemas.xmlsoap.org/soap/http");
        soapBinding.setStyle("document");

        Binding binding = definition.createBinding();
        binding.setQName(new QName("http://www.example.com/sample", "SampleBinding"));
        binding.addExtensibilityElement(soapBinding);

        // Validate SOAP binding properties
        assertEquals("http://schemas.xmlsoap.org/soap/http", soapBinding.getTransportURI());
        assertEquals("document", soapBinding.getStyle());
    }
}
