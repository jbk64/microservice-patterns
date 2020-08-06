package client;

import javax.ws.rs.core.MediaType;

import business.SubscriberDTO;
import restinterface.RestInterface;

public class SubscribeReaders {
    public static void main(String[] args) {
        // inscrire(subscribe) les 2 services CQRSReader pour recevoir les mises a�jour concernant la topic "commande"
        String subscriberUrl1 = "http://localhost:8083/CQRSReader/events";
        String subscriberUrl2 = "http://localhost:8084/CQRSReader2/events";
		String PubSubUrl = "http://localhost:8003/PubSubHubService/subscribers";

        SubscriberDTO dto1 = new SubscriberDTO("commande", subscriberUrl1);
        SubscriberDTO dto2 = new SubscriberDTO("commande", subscriberUrl2);

        new RestInterface().postRemoteObject(PubSubUrl, MediaType.APPLICATION_XML, SubscriberDTO.class, dto1);
        new RestInterface().postRemoteObject(PubSubUrl, MediaType.APPLICATION_XML, SubscriberDTO.class, dto2);

        System.out.println("initialisation terminee");
    }
}
