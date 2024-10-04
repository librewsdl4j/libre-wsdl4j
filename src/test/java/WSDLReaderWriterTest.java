import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import javax.wsdl.*;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.wsdl.xml.WSDLWriter;
import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import javax.xml.namespace.QName;
import javax.xml.transform.stream.StreamSource;

public class WSDLReaderWriterTest {

    @Test
    public void testReadAndWriteWSDL() throws Exception {
        // Create WSDLFactory and WSDLReader/Writer instances
        WSDLFactory factory = WSDLFactory.newInstance();
        WSDLReader reader = factory.newWSDLReader();
        WSDLWriter writer = factory.newWSDLWriter();

        // Load a sample WSDL file
        String wsdlPath = "src/test/resources/sample.wsdl";
        Definition definition = reader.readWSDL(wsdlPath);

        // Ensure the WSDL Definition is correctly read
        assertNotNull(definition, "WSDL Definition should not be null");

        // Verify basic WSDL properties
        assertEquals("http://www.example.com/sample", definition.getTargetNamespace(),
                "The target namespace should match the expected value");

        // Check Services in the WSDL
        Map<?, ?> services = definition.getServices();
        assertNotNull(services, "Services should not be null");
        assertEquals(1, services.size(), "There should be exactly one service defined");

        // Retrieve and validate the service
        Service service = (Service) services.get(new QName("http://www.example.com/sample", "SampleService"));
        assertNotNull(service, "SampleService should be present in the WSDL");

        // Write the WSDL back to a string
        StringWriter stringWriter = new StringWriter();
        writer.writeWSDL(definition, stringWriter);

        // Validate the Written WSDL Content
        String writtenWsdlContent = stringWriter.toString();
        assertNotNull(writtenWsdlContent, "The written WSDL content should not be null");
        assertTrue(writtenWsdlContent.contains("SampleService"), "The written WSDL should contain the service name 'SampleService'");
        assertTrue(writtenWsdlContent.contains("SampleSoapBinding"), "The written WSDL should contain the binding name 'SampleSoapBinding'");
        assertTrue(writtenWsdlContent.contains("getSampleRequest"), "The written WSDL should contain the input message name 'getSampleRequest'");
        assertTrue(writtenWsdlContent.contains("getSampleResponse"), "The written WSDL should contain the output message name 'getSampleResponse'");

        // Re-read the written WSDL content using a StreamSource instead of passing null
        ByteArrayInputStream inputStream = new ByteArrayInputStream(writtenWsdlContent.getBytes(StandardCharsets.UTF_8));
        InputSource streamSource = new InputSource(inputStream);

        // Use readWSDL with StreamSource
        Definition reReadDefinition = reader.readWSDL(null, streamSource);
        assertNotNull(reReadDefinition, "The re-read WSDL definition should not be null");

        // Compare basic properties between the original and re-read definitions
        assertEquals(definition.getTargetNamespace(), reReadDefinition.getTargetNamespace(),
                "The target namespace should match between the original and re-read definitions");

        // Validate Services in the Re-read Definition
        Map<?, ?> reReadServices = reReadDefinition.getServices();
        assertNotNull(reReadServices, "Re-read services map should not be null");
        assertEquals(1, reReadServices.size(), "Re-read definition should have exactly one service defined");

        // Verify the re-read service name
        Service reReadService = (Service) reReadServices.get(new QName("http://www.example.com/sample", "SampleService"));
        assertNotNull(reReadService, "The re-read SampleService should be present in the re-read WSDL");

        // Additional validation to ensure the re-read WSDL matches the original definition
        assertEquals(service.getQName(), reReadService.getQName(), "Service QName should match between original and re-read WSDL");
        assertEquals(service.getPorts().size(), reReadService.getPorts().size(),
                "The number of ports should match between original and re-read WSDL");
    }
}
