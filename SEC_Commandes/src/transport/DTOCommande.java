package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOCommande {
    private int id;
    private String time;
    private String urlRepas;
    private String urlBoisson;
    private String urlClient;

    public DTOCommande() {
    }

    public DTOCommande(int id, String time, String urlRepas, String urlBoisson, String urlClient) {
        this.id = id;
        this.time = time;
        this.urlRepas = urlRepas;
        this.urlBoisson = urlBoisson;
        this.urlClient = urlClient;
    }

    public int getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public String getUrlRepas() {
        return urlRepas;
    }

    public String getUrlBoisson() {
        return urlBoisson;
    }

    public String getUrlClient() {
        return urlClient;
    }
}
