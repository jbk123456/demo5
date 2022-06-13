
package io.openliberty.guides.event.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ibm.websphere.security.jwt.Claims;
import com.ibm.websphere.security.jwt.InvalidClaimException;
import com.ibm.websphere.security.jwt.JwtBuilder;
import com.ibm.websphere.security.jwt.JwtToken;
import com.ibm.websphere.security.jwt.KeyException;
import javax.jms.JMSConnectionFactory;

@RequestScoped
@Path("/events")
public class EventClient {

    // @Inject
    // @JMSConnectionFactory("jms/JmsFactory")
    @Resource(lookup = "jms/JmsFactory")
    private ConnectionFactory jmsFactory;
    
    @Resource(lookup = "jms/JmsQueue")
    private Queue jmsQueue;



    private WebTarget tut;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject getEvent(@PathParam("id") int eventId) throws UnknownUrlException {throw new IllegalArgumentException();}

    @DELETE
    @Path("/{id}")
    public void deleteEvent(@PathParam("id") int eventId) throws UnknownUrlException{throw new IllegalArgumentException();}

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addEvent(@FormParam("name") String name,
        @FormParam("time") String time, @FormParam("location") String location) throws
	    UnknownUrlException, BadRequestException {throw new IllegalArgumentException();}

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void updateEvent(@FormParam("name") String name,
        @FormParam("time") String time, @FormParam("location") String location,
        @PathParam("id") int id) throws UnknownUrlException, BadRequestException{throw new IllegalArgumentException();}


    @PUT
    @Consumes("application/octet-stream")
    public void putFile(InputStream fileInputStream){throw new IllegalArgumentException();}
  


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public
    JsonArray getEvents()  {
        JmsMessageSender sender = new JmsMessageSender(jmsFactory, jmsQueue);
        sender.send();
        if (false) return null;
	try {
            String jwtTokenString =  JwtBuilder.create("jwtFrontEndBuilder")
            .subject("zweiundvierzig")
		.buildJwt()
                         .compact();
            
          
            Client client = ClientBuilder.newClient();
            this.tut = client.target("http://localhost:9080/events");
            Response response = this.tut.
            request().
            header("Authorization", "Bearer "+ jwtTokenString).
            get();
            JsonArray ar = response.readEntity(JsonArray.class);
            return ar;
	} catch(Exception e) {
	    throw new IllegalArgumentException(e);
        }
    }


}
