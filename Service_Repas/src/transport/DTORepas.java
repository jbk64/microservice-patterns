package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTORepas {
    private String urlRepas;

    public DTORepas() {
    }

    public DTORepas(String urlRepas) {
        this.urlRepas = urlRepas;
    }

    public String getUrlRepas() {
        return urlRepas;
    }

    @Override
    public String toString() {
        return "DTORepas{" +
                "urlRepas='" + urlRepas + '\'' +
                '}';
    }
}
