package services;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import business.*;
import transport.AtomRepas;

@Path("/")
public class EntryPoint {

    // get le path du fichier actualData.txt (dans le serveur)
    private String filePathActualD = EntryPoint.class.getClassLoader().getResource("actualData.txt").getPath();
    // get le path du fichier de recuperation.txt (dans le serveur)
    private String filePathUndo = EntryPoint.class.getClassLoader().getResource("undoData.txt").getPath();

    //update a new data
    @PUT
    @Path("Repas/{idRepas}")
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public void createReservation(@PathParam("idRepas") int idRepas, @Context HttpServletResponse response) throws Exception {
        System.out.println("--- Service Repas => creation d'une commande d'un Repas (retourne 200)");
        // A modifier
    }

    @DELETE
    @Path("Repas/undo")
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public void undo() throws Exception {
        // A modifier
    }

    @GET
    @Path("Repas/{id}")
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public AtomRepas getEvent(@PathParam("id") int idRepas, @Context UriInfo uriInfo) throws Exception {
        Repas repas = new Data().getRepas(idRepas);

        if (repas == null)
            throw new WebApplicationException(405);
        return new AtomRepas(uriInfo.getBaseUri() + "Repas/" + idRepas, repas, true);
    }


}
