package transport;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DTOBoisson {
    private String urlBoisson;

    public DTOBoisson() {
    }

    public DTOBoisson(String urlBoisson) {
        this.urlBoisson = urlBoisson;
    }

    public String getUrlBoisson() {
        return urlBoisson;
    }

    @Override
    public String toString() {
        return "DTOBoisson{" +
                "urlBoisson='" + urlBoisson + '\'' +
                '}';
    }
}
