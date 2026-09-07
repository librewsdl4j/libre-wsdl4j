import org.apache.commons.xml.secure.SecureDocumentBuilderFactory;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.wsdl.Binding;
import javax.wsdl.Definition;
import javax.wsdl.Operation;
import javax.wsdl.Port;
import javax.wsdl.PortType;
import javax.wsdl.Service;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Regression coverage for issue #4: a {@code <wsp:Policy>} child of {@code <wsdl:portType>} (and of the other
 * WSDL elements SAP-generated WSDLs put one on) must be read as an extensibility element instead of raising
 * {@code INVALID_WSDL: Encountered unexpected element}.
 */
public class PolicyExtensibilityTest {

    private static final String NS = "http://www.example.com/policy";
    private static final QName POLICY = new QName("http://schemas.xmlsoap.org/ws/2004/09/policy", "Policy");

    @Test
    public void testPolicyElementsAreReadAsExtensibilityElements() throws Exception {
        Definition def = readPolicyWsdl();

        assertPolicy(def.getExtensibilityElements(), "definitions");

        PortType portType = def.getPortType(new QName(NS, "Notification_In"));
        assertNotNull(portType, "portType Notification_In should be present");
        assertPolicy(portType.getExtensibilityElements(), "portType");

        Operation operation = portType.getOperation("Notification_In", null, null);
        assertNotNull(operation, "operation Notification_In should be present");
        assertPolicy(operation.getExtensibilityElements(), "portType operation");

        Binding binding = def.getBinding(new QName(NS, "NotificationBinding"));
        assertNotNull(binding, "binding NotificationBinding should be present");
        assertPolicyAmong(binding.getExtensibilityElements(), "binding");

        Service service = def.getService(new QName(NS, "NotificationService"));
        assertNotNull(service, "service NotificationService should be present");
        assertPolicy(service.getExtensibilityElements(), "service");

        Port port = service.getPort("NotificationPort");
        assertNotNull(port, "port NotificationPort should be present");
        assertPolicyAmong(port.getExtensibilityElements(), "port");
    }

    @Test
    public void testPolicyElementsSurviveAWriteReadRoundTrip() throws Exception {
        WSDLFactory factory = WSDLFactory.newInstance();
        Definition def = readPolicyWsdl();

        WSDLWriter writer = factory.newWSDLWriter();
        StringWriter out = new StringWriter();
        writer.writeWSDL(def, out);

        String written = out.toString();
        assertTrue(written.contains("Policy"), "The written WSDL should still contain the policy elements");

        DocumentBuilderFactory dbf = SecureDocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        Document doc = dbf.newDocumentBuilder().parse(new InputSource(new StringReader(written)));

        Definition reread = factory.newWSDLReader().readWSDL(null, doc);

        PortType portType = reread.getPortType(new QName(NS, "Notification_In"));
        assertNotNull(portType, "portType should survive the round trip");
        assertPolicy(portType.getExtensibilityElements(), "re-read portType");

        Operation operation = portType.getOperation("Notification_In", null, null);
        assertNotNull(operation, "operation should survive the round trip");
        assertPolicy(operation.getExtensibilityElements(), "re-read portType operation");
    }

    private Definition readPolicyWsdl() throws Exception {
        WSDLReader reader = WSDLFactory.newInstance().newWSDLReader();
        Definition def = reader.readWSDL("src/test/resources/policy.wsdl");
        assertNotNull(def, "WSDL definition should not be null");
        return def;
    }

    /** The element carries the policy and nothing else. */
    private void assertPolicy(List<ExtensibilityElement> extElements, String context) {
        assertNotNull(extElements, "Extensibility elements of " + context + " should not be null");
        assertEquals(1, extElements.size(), "Expected exactly one extensibility element on " + context);
        assertPolicyAmong(extElements, context);
    }

    /** The element carries the policy alongside its SOAP binding extensions. */
    private void assertPolicyAmong(List<ExtensibilityElement> extElements, String context) {
        assertNotNull(extElements, "Extensibility elements of " + context + " should not be null");

        ExtensibilityElement policy = extElements.stream()
                .filter(e -> POLICY.equals(e.getElementType()))
                .findFirst()
                .orElse(null);

        assertNotNull(policy, "Expected a wsp:Policy element on " + context);
        assertTrue(policy instanceof UnknownExtensibilityElement,
                "Policy on " + context + " should be kept as an UnknownExtensibilityElement");
    }
}
