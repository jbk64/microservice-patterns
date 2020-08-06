package services;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import org.apache.http.HttpResponse;
import business.*;
import restinterface.RestInterface;
import transport.AtomBoisson;
import transport.AtomClient;
import transport.AtomCommande;
import transport.AtomRepas;

@Path("/")
public class EntryPoint {
	

	//log
	private String filePathLog= EntryPoint.class.getClassLoader().getResource("log.txt").getPath();
	
	// liste des services des repas et boissons  
	private String filePathServices= EntryPoint.class.getClassLoader().getResource("services.txt").getPath();
	private static final String OK = "ok-";
	private static final String KO = "ko-";
	
	//stores a new data
	@POST
	@Path("Commandes")
	@Consumes(MediaType.APPLICATION_ATOM_XML)
	public void updatesServices(@Context HttpServletResponse response, @Context UriInfo uriInfo){
		
			// liste des services dont on a besoin pour faire les reservations de repas et de boissons
		try {
			ArrayList<Service> services = new FileStorage().readAllServices(filePathServices);
			new FileStorage().rewrite(filePathLog);//je vide le log pour chaque requete
			// A modifier 
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
