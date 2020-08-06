package services;


import business.FileStorage;
import restinterface.RestInterface;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/")
public class EntryPoint {

    // get le path du fichier filmsReader2.txt (dans le serveur)
    private String filePath = EntryPoint.class.getClassLoader().getResource("commandes.txt").getPath();
    private static final String URL_PUBHUB = "http://localhost:8085/HubPubSubService_cor/dispatcher/commande";

    //stores a new data update event
    @POST
    @Path("events")
    @Consumes(MediaType.APPLICATION_XML)
    public void storeEvent(String event, @Context UriInfo uriInfo, @Context HttpServletResponse response) throws Exception {
        if (filePath.trim().length() == 0)
            throw new WebApplicationException(500);
        new FileStorage().append(event, filePath);

        System.out.println("ecriture dans une BDD locale(fichier commandes.txt )....");

        String id = new FileStorage().getId(event);
        String baseURL = uriInfo.getBaseUri().toString();
        response.setHeader(HttpHeaders.LOCATION, baseURL + "/" + id);

        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //envoyer au Hub pour enregister dans les readers inscrit pour la topic film passee en parametres
        new RestInterface().putRemoteObject(URL_PUBHUB, MediaType.APPLICATION_XML, event.getClass(), event);
    }

    //retrieves the lastest event(commande) having the id
    @GET
    @Path("commandes/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public String getEvent(@PathParam("id") String id) throws Exception {
        if (filePath.trim().length() == 0)
            throw new WebApplicationException(500);

        // simulation du fait qu'apres de quelques requetes les performances du serveur s'ameliorent
        Database db = new Database();
        int rCpt = db.incRead();
        String result = null;
        result = new FileStorage().read(id, filePath);
        if (result == null)
            throw new WebApplicationException(404);
        return result;
//        if (rCpt < 15) {
//            double total = 0;
//            for (int i = 1; i < 100000000; i++) {
//                total = total + Math.log(i);
//            }
//            for (int i = 1; i < 100000000; i++) {
//                total = total + Math.log(i);
//            }
//            result = new FileStorage().read(id, filePath);
//            if (result == null)
//                throw new WebApplicationException(404);
//            return result;
//            //System.out.println("serveur occupe   :( ");
//        } else {
//            db.initialiser();
//            System.out.println("Le serveur s'est libere :) ");
//            result = new FileStorage().read(id, filePath);
//            System.out.println(result);
//            if (result == null)
//                throw new WebApplicationException(404);
//            return result;
//
//        }
    }

    @DELETE
    @Path("events")
    public void deleteAllEvents() throws Exception {
        if (filePath.trim().length() == 0) return;
        new FileStorage().rewrite(filePath);
    }
}
