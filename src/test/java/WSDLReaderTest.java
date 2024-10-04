import javax.wsdl.*;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.xml.namespace.QName;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class WSDLReaderTest {

    private WSDLReader wsdlReader;

    @BeforeEach
    public void setUp() throws Exception {
        WSDLFactory factory = WSDLFactory.newInstance();
        wsdlReader = factory.newWSDLReader();
    }

    @Test
    public void testLoadWSDL() throws Exception {
        // Load the sample WSDL file
        String wsdlPath = "src/test/resources/sample.wsdl";

        // Read the WSDL file and get the definition object
        Definition definition = wsdlReader.readWSDL(wsdlPath);

        // Validate the definition object
        assertNotNull(definition, "WSDL Definition should not be null");
        assertEquals("http://www.example.com/sample", definition.getTargetNamespace(),
                "The target namespace should match the expected value");

        // Check services in the WSDL
        Map<?, ?> services = definition.getServices();
        assertNotNull(services, "Services should not be null");
        assertEquals(1, services.size(), "There should be exactly one service defined");

        // Use QName to retrieve the service correctly
        QName serviceName = new QName("http://www.example.com/sample", "SampleService");
        Service service = definition.getService(serviceName);
        assertNotNull(service, "SampleService should be present in the WSDL");

        // Check ports in the service
        Map<?, ?> ports = service.getPorts();
        assertNotNull(ports, "Ports should not be null");
        assertEquals(1, ports.size(), "There should be exactly one port in the SampleService");

        Port port = service.getPort("SamplePort");
        assertNotNull(port, "SamplePort should be defined in SampleService");

        // Check the binding associated with the port
        Binding binding = port.getBinding();
        assertNotNull(binding, "Binding should not be null for SamplePort");
        assertEquals("SampleSoapBinding", binding.getQName().getLocalPart(),
                "The binding name should match the expected value");

        // Check the port type (operations)
        PortType portType = binding.getPortType();
        assertNotNull(portType, "PortType should not be null");
        assertEquals("SamplePortType", portType.getQName().getLocalPart(),
                "The port type name should match the expected value");

        // Validate operations
        assertEquals(1, portType.getOperations().size(), "There should be exactly one operation in the port type");
        Operation operation = portType.getOperation("getSampleResponse", null, null);
        assertNotNull(operation, "The getSampleResponse operation should be defined in SamplePortType");

        // Debugging: Print details of the operation
        if (operation.getInput() != null) {
            Message inputMessage = operation.getInput().getMessage();
            System.out.println("Input Message: " + inputMessage);
            if (inputMessage != null) {
                System.out.println("Input Message Name: " + inputMessage.getQName().toString());
                System.out.println("Input Message Parts: " + inputMessage.getParts());
            } else {
                System.out.println("Input message object is null");
            }
        } else {
            System.out.println("Input message is null");
        }

        // Validate input and output messages of the operation
        assertNotNull(operation.getInput(), "The input of getSampleResponse operation should not be null");
        assertNotNull(operation.getOutput(), "The output of getSampleResponse operation should not be null");
        assertEquals("getSampleRequest", operation.getInput().getMessage().getQName().getLocalPart(),
                "The input message name should match the expected value");
        assertEquals("getSampleResponse", operation.getOutput().getMessage().getQName().getLocalPart(),
                "The output message name should match the expected value");
    }
}
