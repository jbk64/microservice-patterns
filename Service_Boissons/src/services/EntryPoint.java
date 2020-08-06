package services;


import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import business.*;
import transport.AtomBoisson;
import transport.DTOBoisson;

@Path("/")
public class EntryPoint {

    // get le path du fichier actualData.txt (dans le serveur)
    private String filePathActualD = EntryPoint.class.getClassLoader().getResource("actualData.txt").getPath();
    // get le path du fichier de recuperation.txt (dans le serveur)
    private String filePathUndo = EntryPoint.class.getClassLoader().getResource("undoData.txt").getPath();

    //update a new data
    @PUT
    @Path("Boissons/{idBoisson}")
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public void createReservation(@PathParam("idBoisson") int idBoisson, DTOBoisson dtoBoisson, @Context HttpServletResponse response) throws Exception {
        boolean simulateError = true;

        // simulate error
        if (simulateError) {
            System.out.println("--- Service Boissons => simulation d'erreur (retourne 500)");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        // expected behavior
        } else {
            System.out.println("--- Service Boissons => creation d'une commnde d'une Boisson (retourne 200)");
            FileStorage fileStorage = new FileStorage();
            fileStorage.save(filePathActualD, filePathUndo);
            fileStorage.append(dtoBoisson.toString(), filePathActualD);
            response.setStatus(HttpServletResponse.SC_CREATED);
        }

        try {
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DELETE
    @Path("Boissons/undo")
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public void undo() throws Exception {
        //A modifier
        // A modifier
        FileStorage fileStorage = new FileStorage();
        fileStorage.save(filePathUndo, filePathActualD);
    }


    @GET
    @Path("Boissons/{id}")
    @Produces(MediaType.APPLICATION_ATOM_XML)
    public AtomBoisson getEvent(@PathParam("id") int idBoisson, @Context UriInfo uriInfo) throws Exception {
        Boisson boisson = new Data().getBoisson(idBoisson);

        if (boisson == null)
            throw new WebApplicationException(404);
        return new AtomBoisson(uriInfo.getBaseUri() + "Boissons/" + idBoisson, boisson, true);
    }

}
