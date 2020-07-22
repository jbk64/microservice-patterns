package business;

import java.util.Calendar;


public class Commande {
    private String idCommande;
    private Repas repas;
    private Boisson boisson;
    private Client client;
    private double prix;
    private String time;

    public Commande() {
    }

    public Commande(Repas repas, Boisson boisson, Client client, String time) {
        super();
        this.idCommande = client.getId() + time.substring(0, 2);
        this.repas = repas;
        this.boisson = boisson;
        this.client = client;
        this.prix = repas.getPrix() + boisson.getPrix();

    }


    public Commande(String idCommande, Repas repas, Boisson boisson, Client client) {
        super();
        this.idCommande = client.getId() + time.substring(0, 2);
        this.repas = repas;
        this.boisson = boisson;
        this.client = client;
        this.prix = repas.getPrix() + boisson.getPrix();

    }

    public String getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(String idCommande) {
        this.idCommande = idCommande;
    }

    public Repas getRepas() {
        return repas;
    }

    public void setRepas(Repas repas) {
        this.repas = repas;
    }

    public Boisson getBoisson() {
        return boisson;
    }

    public void setBoisson(Boisson boisson) {
        this.boisson = boisson;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Commande [" + repas.getNom() + ", " + boisson.getNom() + " prix total=" + prix + ", time=" + time + "]";
    }

}
