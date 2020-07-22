package business;


public class CommandeBoisson {
    private String idCommande;
    private String urlBoisson;
    private String urlClient;

    public CommandeBoisson() {
    }

    public CommandeBoisson(String idCommande, String urlBoisson, String urlClient) {
        this.idCommande = idCommande;
        this.urlBoisson = urlBoisson;
        this.urlClient = urlClient;
    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public String getUrlBoisson() {
        return urlBoisson;
    }

    public void setUrlBoisson(String urlBoisson) {
        this.urlBoisson = urlBoisson;
    }

    public String getUrlClient() {
        return urlClient;
    }

    public void setUrlClient(String urlClient) {
        this.urlClient = urlClient;
    }

    @Override
    public String toString() {
        return "id: " + idCommande + " ,idClient: " + urlClient + ", urlBoisson:" + urlBoisson;
    }
}
