package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOCommande {
    private String urlClient;
    private String urlRepas;
    private String urlBoisson;

    public DTOCommande() {}

    public DTOCommande(String urlClient, String urlRepas, String urlBoisson) {
        this.urlClient = urlClient;
        this.urlRepas = urlRepas;
        this.urlBoisson = urlBoisson;
    }

    public String getUrlClient() {
        return urlClient;
    }

    public String getUrlRepas() {
        return urlRepas;
    }

    public String getUrlBoisson() {
        return urlBoisson;
    }
}
