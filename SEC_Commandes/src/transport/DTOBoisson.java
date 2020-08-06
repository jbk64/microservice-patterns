package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOBoisson {
    private int idCommande;
    private String urlBoisson;

    public DTOBoisson() {
    }

    public DTOBoisson(int idCommande, String urlBoisson) {
        this.idCommande = idCommande;
        this.urlBoisson = urlBoisson;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public String getUrlBoisson() {
        return urlBoisson;
    }

    @Override
    public String toString() {
        return "DTOBoisson{" +
                "idCommande=" + idCommande +
                ", urlBoisson='" + urlBoisson + '\'' +
                '}';
    }
}
