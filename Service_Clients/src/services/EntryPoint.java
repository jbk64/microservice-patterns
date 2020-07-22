package services;


import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import business.*;
import transport.AtomClient;

@Path("/")
public class EntryPoint {

    @GET
    @Path("Clients/{id}")
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public AtomClient getEvent(@PathParam("id") String id, @Context UriInfo uriInfo) throws Exception {
        Client client = new Data().getClient(id);
        if (client == null)
            throw new WebApplicationException(404);
        return new AtomClient(uriInfo.getBaseUri() + "Client/" + id, client, true);
    }
}
