package services;

import business.SubscriberDTO;
import business.SubscriberFileStorage;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import utils.ThreadedDispatcher;
import utils.TypeRequest;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

@Path("/")
public class EntryPoint {

    private String filePath = EntryPoint.class.getClassLoader().getResource("subscribers.txt").getPath();

    @POST
    @Path("subscribers")
    @Consumes(MediaType.APPLICATION_XML)
    public void createSub(SubscriberDTO dto, @Context HttpServletResponse response, @Context UriInfo uriInfo) throws Exception {
        System.out.println("Received subscription: " + dto);
        new SubscriberFileStorage().append(dto.toString(), filePath);
        String baseURL = uriInfo.getBaseUri().toString();
        response.setStatus(HttpServletResponse.SC_CREATED);
        response.setHeader(HttpHeaders.LOCATION, baseURL + dto.getTopic() + dto.getUrl());
        try {
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PUT
    @Path("dispatcher/{topic}")
    @Produces(MediaType.APPLICATION_XML)
    public void dispatchEvent(String event, @PathParam("topic") String topic) throws Exception {

        ArrayList<String> urls = new SubscriberFileStorage().readAll(topic, filePath);
        for (String url : urls) {
            System.out.println(url);
            new ThreadedDispatcher(TypeRequest.POST, url, event, MediaType.APPLICATION_XML).start();
        }
    }

    @DELETE
    @Path("dispatcher/{topic}")
    @Produces(MediaType.APPLICATION_XML)
    public void dispatchEvent(@PathParam("topic") String topic) throws Exception {
        ArrayList<String> urls = new SubscriberFileStorage().readAll(topic, filePath);
        for (String url : urls)
            try {
                HttpDelete request = new HttpDelete(url);
                HttpResponse response = HttpClientBuilder.create().build().execute(request);
                System.out.println(response.getStatusLine().getStatusCode());
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
