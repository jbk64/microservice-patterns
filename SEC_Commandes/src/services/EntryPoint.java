package services;


import java.io.UnsupportedEncodingException;
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
    @Consumes(MediaType.APPLICATION_ATOM_XML)
    public void updatesServices(DTOCommande dtoCommande, @Context HttpServletResponse response, @Context UriInfo uriInfo) {
        System.out.println("Reçu la commande " + dtoCommande);

        // liste des services dont on a besoin pour faire les reservations de repas et de boissons
        try {
            ArrayList<Service> services = new FileStorage().readAllServices(filePathServices);
            new FileStorage().rewrite(filePathLog);//je vide le log pour chaque requete
            // A modifier
            boolean koExist = false;
            for (Service s : services) {
                HttpResponse serviceResponse = null;
                if (s.getType().equals("Boisson")) {
                    DTOBoisson dtoBoisson = new DTOBoisson(dtoCommande.getId(), dtoCommande.getUrlBoisson());
                    System.out.println("Envoi de " + dtoBoisson);
                    serviceResponse = new RestInterface().putRemoteObject(dtoBoisson.getUrlBoisson(), MediaType.APPLICATION_ATOM_XML, dtoBoisson.getClass(), dtoBoisson);
                } else {
                    DTORepas dtoRepas = new DTORepas(dtoCommande.getId(), dtoCommande.getUrlRepas());
                    System.out.println("Envoi de " + dtoRepas);
                    serviceResponse = new RestInterface().putRemoteObject(dtoRepas.getUrlRepas(), MediaType.APPLICATION_ATOM_XML, dtoRepas.getClass(), dtoRepas);
                }
                System.out.println("Réponse du service " + s.getType() + ":" + serviceResponse.getEntity().getContent());
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

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @GET
    @Path("Test")
    @Consumes(MediaType.TEXT_PLAIN)
    public String test() {
        return "OK";
    }

}
