package business;

import javax.xml.bind.annotation.*;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
    private String type;
    private String url;

    public Service() {
    }

    public Service(String number, String url) {
        this.type = number;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return type + "-" + url;
    }
}
