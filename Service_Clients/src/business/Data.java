package business;

import java.util.HashMap;

public class Data {
    private HashMap<String, Client> hClients = new HashMap<String, Client>();

    public Data() {
        Client c = new Client("Dupont", "Charle", "1975");
        hClients.put(c.getId(), c);
        c = new Client("Blanc", "Alain", "1960");
        hClients.put(c.getId(), c);
        c = new Client("Toto", "Alex", "1967");
        hClients.put(c.getId(), c);
        c = new Client("Ami", "Milal", "1970");
        hClients.put(c.getId(), c);
        c = new Client("Courtin", "Dani", "1979");
        hClients.put(c.getId(), c);
        // TODO Auto-generated constructor stub
    }

    public Client getClient(String id) {
        return hClients.get(id);
    }

}
