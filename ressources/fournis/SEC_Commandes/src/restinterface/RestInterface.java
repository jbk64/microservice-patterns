package restinterface;

import java.io.*;
import javax.xml.bind.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestInterface { 
	//Getting a remote resource. Returns an Object that must be Type cast 
	public Object getRemoteObject(String url,String type,Class objectClass)  { 
		Object result = null;
		try {
			//REST service query
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(url);
			request.setHeader(HttpHeaders.ACCEPT, type);
			HttpResponse response = client.execute(request);
			//XML back-conversion
			JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
			Unmarshaller Unmarshaller = jaxbContext.createUnmarshaller();
			System.out.println(response.getStatusLine().getStatusCode());
			if(response.getStatusLine().getStatusCode() == 200)
				result= Unmarshaller.unmarshal(response.getEntity().getContent());		
		} catch (Exception e) {e.printStackTrace();}
		
		return result;}
	//Creating a new resource. Returns the URL of the newly created resource
	public HttpResponse postRemoteObject(String url,String type,Class objectClass,Object o)  { 
		try {
			//XML conversion
			OutputStream os = new ByteArrayOutputStream();
			JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(o, os);
			//REST service query
			HttpPost request = new HttpPost(url);
			request.setHeader(HttpHeaders.CONTENT_TYPE, type);
			request.setEntity(new StringEntity(os.toString(), "UTF-8"));
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return response;

		    } catch (Exception e) {e.printStackTrace();}
		return null;}
	
	public HttpResponse putRemoteObject(String url,String type,Class objectClass,Object o)  { 
		try {
			//XML conversion
			OutputStream os = new ByteArrayOutputStream();
			JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.marshal(o, os);
			//REST service query
			HttpPut request = new HttpPut(url);
			request.setHeader(HttpHeaders.CONTENT_TYPE, type);
			request.setEntity(new StringEntity(os.toString(), "UTF-8"));
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			System.out.println(response.getStatusLine().getStatusCode());
			return response;
		    } catch (Exception e) {e.printStackTrace();}
		return null;}
	
	public String deleteAllRemoteObject(String url)  { 
		try {
			//REST service query
			HttpDelete request = new HttpDelete(url);
			HttpResponse response = HttpClientBuilder.create().build().execute(request);
			System.out.println(response.getStatusLine().getStatusCode());			
		    } catch (Exception e) {e.printStackTrace();}
		return "";}
}


