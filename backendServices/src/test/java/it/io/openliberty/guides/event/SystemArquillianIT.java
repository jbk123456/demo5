package it.io.openliberty.guides.event;

import java.net.URL;
import java.util.Properties;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public class SystemArquillianIT {

    private static final String WARNAME = System.getProperty("arquillian.war.name");

    @Deployment(testable = true)
    public static WebArchive createSystemEndpointTestDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, WARNAME);
        return archive;
    }

    @ArquillianResource
    private URL baseURL;

    

    @Test
    @RunAsClient
    public void testGetPropertiesFromEndpoint() throws Exception {
        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(baseURL + "/system/properties");
        Response response = target.request().get();

        Assert.assertEquals("Incorrect response code from " + baseURL, 200,
                            response.getStatus());

        JsonObject obj = response.readEntity(JsonObject.class);
        Assert.assertEquals("The system property for the local"
                            + " and remote JVM should match",
                            System.getProperty("os.name"), obj.getString("os.name"));
        response.close();
    }
}
