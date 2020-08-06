package client;

import javax.ws.rs.core.MediaType;

import business.DTOCommande;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.config.*;
import org.apache.http.impl.client.*;


import org.apache.http.client.methods.*;
import restinterface.RestInterface;

public class ReadCommandes {

    public static void main(String[] args) throws Exception {

        String url1 = "http://localhost:8083/CQRSReader/commandes/1234";
        String url2 = "http://localhost:8084/CQRSReader2/commandes/1234";

        DTOCommande dtoCommandeReader1 = (DTOCommande) new RestInterface().getRemoteObject(url1, MediaType.APPLICATION_XML, DTOCommande.class);
        System.out.println("dtoCommande reader 1: " + dtoCommandeReader1);

        DTOCommande dtoCommandeReader2 = (DTOCommande) new RestInterface().getRemoteObject(url2, MediaType.APPLICATION_XML, DTOCommande.class);
        System.out.println("dtoCommande reader 2: " + dtoCommandeReader2);

//        HttpGet request1 = new HttpGet(url1);
//        request1.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML);
//
//        HttpGet request2 = new HttpGet(url2);
//        request2.setHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_ATOM_XML);

//        CircuitBreaker cb = new CircuitBreaker();
//        int loop = 0;
//        while (loop < 100) {
//
//            System.out.println("trying (Reader 1)");
//
//            System.out.println("done");
//            break;
//        }
//        // A modifier
//        loop++;
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







