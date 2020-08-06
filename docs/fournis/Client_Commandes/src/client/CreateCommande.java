package client;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;

import business.*;
import restinterface.RestInterface;

public class CreateCommande {

	public static void main(String[] args) {

		String urlBoisson="http://localhost:8080/Service_Boissons/Boissons/1";
		String urlRepas="http://localhost:8080/Service_Repas/Repas/3";
		String urlClient="http://localhost:8080/Service_Clients/Clients/Char1975";
		String time=Calendar.getInstance().getTime().toString();
		// on achète le repas 1 avec la boisson 3 pour le client dont l'id est: Char1975(L'id du client est compose de ses 4 pre
		//premieres lettres et son annnee de naissance)

		String urlSEC = "http://localhost:8080/SEC_Commandes/Commandes";

//A modifier

	}
}
