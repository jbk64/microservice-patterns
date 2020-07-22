package transport;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.NONE)
public class AtomLink {
    @XmlAttribute
    private String rel;
    @XmlAttribute
    private String href;
    @XmlAttribute
    private String type;

    AtomLink() {
    }

    AtomLink(String rel, String href, String type) {
        this.rel = rel;
        this.href = href;
        this.type = type;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }


}