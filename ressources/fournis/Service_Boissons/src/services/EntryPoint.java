package services;



import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import business.*;
import transport.AtomBoisson;
@Path("/")
public class EntryPoint {
	
	// get le path du fichier actualData.txt (dans le serveur)
	private String filePathActualD= EntryPoint.class.getClassLoader().getResource("actualData.txt").getPath();
	// get le path du fichier de recuperation.txt (dans le serveur)
	private String filePathUndo= EntryPoint.class.getClassLoader().getResource("undoData.txt").getPath();
	
	//update a new data 
		@PUT
		@Path("Boissons/{idBoisson}")
		@Consumes(MediaType.APPLICATION_ATOM_XML)
		public void createReservation(@PathParam("idBoisson") int idBoisson,@Context HttpServletResponse response)throws Exception{
			System.out.println("--- Service Boissons => creation d'une commnde d'une Boisson (retourne 200)");
			// A modifier
		}
		
		@DELETE
		@Path("Boissons/undo")
		@Consumes(MediaType.APPLICATION_ATOM_XML)
		public void undo()throws Exception{
			//A modifier
		}
		
		
	@GET
	@Path("Boissons/{id}")
	@Produces(MediaType.APPLICATION_ATOM_XML)
	public AtomBoisson getEvent(@PathParam("id") int idBoisson, @Context UriInfo uriInfo)throws Exception{
		Boisson boisson=new Data().getBoisson(idBoisson);
		
		if(boisson == null)
			throw new WebApplicationException(404);
		return new AtomBoisson(uriInfo.getBaseUri()+ "Boissons/"+ idBoisson, boisson, true);
	}
	

	

}
