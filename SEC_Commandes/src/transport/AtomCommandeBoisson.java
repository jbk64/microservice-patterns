package transport;

import business.CommandeBoisson;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Calendar;


@XmlRootElement(name = "entry")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomCommandeBoisson extends AtomConstruct {

    @XmlElement(name = "content")
    private CommandeBoisson content;

    public AtomCommandeBoisson() {
    }

    public AtomCommandeBoisson(String selfURL, CommandeBoisson c, boolean fullEntry) {
        setId(selfURL);
        setTitle("CommandeBoisson ");
        setUpdated(Calendar.getInstance().getTime().toString());

        if (fullEntry) {
            this.content = c;
            addLink(new AtomLink("edit", selfURL, "Atom+Xml"));
            addLink(new AtomLink("delete", selfURL + "/delete", "Atom+Xml"));
        } else addLink(new AtomLink("alternate", selfURL, MediaType.APPLICATION_ATOM_XML));
    }

    public CommandeBoisson getContents() {
        return content;
    }

}
