package services;


import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.http.HttpResponse;
import business.*;
import restinterface.RestInterface;
import transport.*;

@Path("/")
public class EntryPoint {


    //log
    private String filePathLog = EntryPoint.class.getClassLoader().getResource("log.txt").getPath();

    // liste des services des repas et boissons
    private String filePathServices = EntryPoint.class.getClassLoader().getResource("services.txt").getPath();
    private static final String OK = "ok-";
    private static final String KO = "ko-";

    //stores a new data
    @POST
    @Path("Commandes")
    @Consumes(MediaType.APPLICATION_XML)
    public void createCommande(DTOCommande dtoCommande, @Context HttpServletResponse response, @Context UriInfo uriInfo) {

        // liste des services dont on a besoin pour faire les reservations de repas et de boissons
        try {
            ArrayList<Service> services = new FileStorage().readAllServices(filePathServices);
            new FileStorage().rewrite(filePathLog);//je vide le log pour chaque requete
            // A modifier
            boolean koExist = false;

            for (Service s : services) {
                CommandeBoisson commandeBoisson = new CommandeBoisson("id commande en dur", dtoCommande.getUrlBoisson(), dtoCommande.getUrlClient());
                AtomCommandeBoisson atomCommandeBoisson = new AtomCommandeBoisson(null, commandeBoisson, true);
                HttpResponse serviceResponse = new RestInterface().postRemoteObject(s.getUrl(), MediaType.APPLICATION_ATOM_XML, AtomCommandeBoisson.class, atomCommandeBoisson);

                if (serviceResponse.getStatusLine().getStatusCode() == HttpServletResponse.SC_CREATED) {
                    new FileStorage().appendMenu(OK + s.getUrl(), filePathLog);
                } else {
                    new FileStorage().appendMenu(KO + s.getUrl(), filePathLog);
                    koExist = true;
                    break;
                }
            }
            if (!koExist) {
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                List<String> urlsOk = new FileStorage().readAllok(filePathLog);
                for (String url : urlsOk) {
                    System.out.println(url + "/undo");
                    new RestInterface().deleteAllRemoteObject(url + "/undo");
                }
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("HelloWorld")
    public String helloWorld() {
        return "Hello, World";
    }
}
