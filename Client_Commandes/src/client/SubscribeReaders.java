package client;

import javax.ws.rs.core.MediaType;

import transport.SubscriberDTO;
import restinterface.RestInterface;

public class SubscribeReaders {
    public static void main(String[] args) {
        // inscrire(subscribe) les 3 services CQRSReader pour recevoir les mises a�jour concernant la topic "commande"
        String subscriberUrl1 = "http://localhost:8080/CQRSReader/events";
        String subscriberUrl2 = "http://localhost:8080/CQRSReader2/events";

        SubscriberDTO dto1 = new SubscriberDTO("commande", subscriberUrl1);
        SubscriberDTO dto2 = new SubscriberDTO("commande", subscriberUrl2);

        String PubSubUrl = "http://localhost:8080/HubPubSubService_cor/subscribers";

        new RestInterface().postRemoteObject(PubSubUrl, MediaType.APPLICATION_XML, SubscriberDTO.class, dto1);

        new RestInterface().postRemoteObject(PubSubUrl, MediaType.APPLICATION_XML, SubscriberDTO.class, dto2);

        System.out.println("initialisation terminee");


    }
}
