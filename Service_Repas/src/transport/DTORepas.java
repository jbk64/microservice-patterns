package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTORepas {
    private int idCommande;
    private String urlRepas;

    public DTORepas() {
    }

    public DTORepas(int idCommande, String urlRepas) {
        this.idCommande = idCommande;
        this.urlRepas = urlRepas;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public String getUrlRepas() {
        return urlRepas;
    }

    @Override
    public String toString() {
        return "DTORepas{" +
                "idCommande=" + idCommande +
                ", urlRepas='" + urlRepas + '\'' +
                '}';
    }
}
