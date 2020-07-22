package services;

import java.util.ArrayList;

import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.HttpHeaders;
import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import business.SubscriberDTO;
import business.SubscriberFileStorage;

@Path("/")
public class EntryPoint {
	
	private String filePath= EntryPoint.class.getClassLoader().getResource("subscribers.txt").getPath();

	@POST
	@Path("subscribers")
	@Consumes(MediaType.APPLICATION_XML)
	public void createSub(SubscriberDTO dto,@Context HttpServletResponse response, @Context UriInfo uriInfo)throws Exception{
		
		new SubscriberFileStorage().append(dto.toString(),filePath);
		String baseURL = uriInfo.getBaseUri().toString();
		response.setStatus(HttpServletResponse.SC_CREATED);
		response.setHeader(HttpHeaders.LOCATION, baseURL+dto.getTopic()+dto.getUrl());
		try { response.flushBuffer(); }catch(Exception e){}
		}
	
	@PUT
	@Path("dispatcher/{topic}")
	@Produces(MediaType.APPLICATION_XML)
	public void dispatchEvent(String event,@PathParam("topic") String topic) throws Exception{
		
		ArrayList<String> urls = new SubscriberFileStorage().readAll(topic,filePath);
		// A modifier
	}
	
	@DELETE
	@Path("dispatcher/{topic}")
	@Produces(MediaType.APPLICATION_XML)
	public void dispatchEvent(@PathParam("topic") String topic) throws Exception{
		
		ArrayList<String> urls = new SubscriberFileStorage().readAll(topic,filePath);
		for(String url : urls)
			try {
				HttpDelete request = new HttpDelete(url);
				HttpResponse response = HttpClientBuilder.create().build().execute(request);
				System.out.println(response.getStatusLine().getStatusCode());			
			    } catch (Exception e) {e.printStackTrace();}
	}
	
}
