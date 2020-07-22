package transport;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import business.Commande;


@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomCommande extends AtomConstruct {

    @XmlElement(name = "content")
    private Commande content;

    public AtomCommande() {
    }

    public AtomCommande(String selfURL, Commande c, boolean fullEntry) {
        setId(selfURL);
        setTitle("Commande ");
        setUpdated(Calendar.getInstance().getTime().toString());

        if (fullEntry) {
            this.content = c;
            addLink(new AtomLink("edit", selfURL, "Atom+Xml"));
            addLink(new AtomLink("delete", selfURL + "/delete", "Atom+Xml"));
        } else addLink(new AtomLink("alternate", selfURL, MediaType.APPLICATION_ATOM_XML));
    }

    public Commande getContents() {
        return content;
    }

}
