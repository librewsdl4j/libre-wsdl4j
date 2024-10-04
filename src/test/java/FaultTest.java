import org.junit.jupiter.api.Test;
import javax.wsdl.*;
import javax.wsdl.factory.WSDLFactory;

import static org.junit.jupiter.api.Assertions.*;

public class FaultTest {

    @Test
    public void testFaultElement() throws Exception {
        WSDLFactory factory = WSDLFactory.newInstance();
        Definition definition = factory.newDefinition();

        // Create a new operation with a fault
        Operation operation = definition.createOperation();
        operation.setName("testOperation");

        Fault fault = definition.createFault();
        fault.setName("TestFault");
        fault.setDocumentationElement(definition.getDocumentationElement());

        operation.addFault(fault);

        // Check the fault name and linkage
        assertEquals("TestFault", operation.getFault("TestFault").getName());
    }
}
