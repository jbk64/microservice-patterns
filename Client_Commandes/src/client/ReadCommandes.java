package client;

import javax.ws.rs.core.MediaType;

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.config.*;
import org.apache.http.impl.client.*;


import org.apache.http.client.methods.*;

public class ReadCommandes {

    public static void main(String[] args) throws Exception {

        String url1 = "http://localhost:8080/CQRSReader/commandes/Char1975Th";
        String url2 = "http://localhost:8080/CQRSReader2/commandes/Char1975Th";

        HttpGet request1 = new HttpGet(url1);
        request1.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML);

        HttpGet request2 = new HttpGet(url2);
        request2.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML);

        CircuitBreaker cb = new CircuitBreaker();
        int loop = 0;
        while (loop < 100) {

            System.out.println("trying (Reader 1)");

            System.out.println("done");
            break;
        }
        // A modifier
        loop++;
    }


    private static HttpClient getClient() {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(1)
                .setConnectTimeout(1)
                .setSocketTimeout(50).build();
        HttpClient client = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig).build();
        return client;
    }
}







