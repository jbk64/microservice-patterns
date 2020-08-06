package client;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import business.*;
import restinterface.RestInterface;

public class CreateCommande {

    public static void main(String[] args) {

        String urlBoisson = "http://localhost:8081/Service_Boissons/Boissons/1";
        String urlRepas = "http://localhost:8082/Service_Repas/Repas/3";
        String urlClient = "http://localhost:8080/Service_Clients/Clients/Char1975";
        String time = Calendar.getInstance().getTime().toString();
        int idCommande = 1234;
        // on ach�te le repas 1 avec la boisson 3 pour le client dont l'id est: Char1975(L'id du client est compose de ses 4 pre
        //premieres lettres et son annnee de naissance)

        String urlSEC = "http://localhost:8080/SEC_Commandes/Commandes";

        //A modifier
        DTOCommande dtoCommande = new DTOCommande(idCommande, time, urlRepas, urlBoisson, urlClient);

        System.out.println("Envoi de la commande " + dtoCommande);
        new RestInterface().postRemoteObject(urlSEC, MediaType.APPLICATION_ATOM_XML, DTOCommande.class, dtoCommande);
    }
}
