package utils;


import javax.ws.rs.core.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ThreadedDispatcher extends Thread{

	private TypeRequest typeRequest;
	private String url;
	private String event;
	private String mediaType;
    public ThreadedDispatcher(TypeRequest typeRequest, String url, String event, String mediaType) {
		super();
		this.typeRequest=typeRequest;
		this.url = url;
		this.event = event;
		this.mediaType=mediaType;
		}
    public ThreadedDispatcher(TypeRequest typeRequest, String url, String mediaType) {
		super();
		this.typeRequest=typeRequest;
		this.url = url;
		this.mediaType=mediaType;
		}

	public void run() {
			try{
				if(typeRequest.equals(TypeRequest.POST)) {
					HttpPost request = new HttpPost(url);
					request.setHeader(HttpHeaders.CONTENT_TYPE, mediaType);
					request.setEntity(new StringEntity(event, "UTF-8"));
					HttpResponse response = HttpClientBuilder.create().build().execute(request);
					System.out.println(response.getStatusLine().getStatusCode()+ " - dispathed to: "+url);	
				}else if(typeRequest.equals(TypeRequest.DELETE)) {
					HttpDelete request = new HttpDelete(url);
					HttpResponse response = HttpClientBuilder.create().build().execute(request);
					System.out.println(response.getStatusLine().getStatusCode());
				}		
				}
			catch (Exception e) {e.printStackTrace();}
				}
}

