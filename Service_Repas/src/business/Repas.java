package business;

public class Repas {
    private String nom;
    private double prix;


    public Repas(String nom, double prix) {
        super();
        this.nom = nom;
        this.prix = prix;
    }

    public Repas() {
        // TODO Auto-generated constructor stub
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "nom=" + nom + ", [prix=" + prix + "]";
    }


}
