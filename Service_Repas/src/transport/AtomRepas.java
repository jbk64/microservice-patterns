package transport;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import business.Repas;


@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomRepas extends AtomConstruct{
	
	@XmlElement(name="content")
	private Repas content;
	
	public  AtomRepas() {}
	public AtomRepas(String selfURL,Repas r ,boolean fullEntry){
		setId(selfURL);
		setTitle("Repas :"+r.getNom());
		setUpdated(Calendar.getInstance().getTime().toString());
		
		if(fullEntry) {
			this.content = r;
			addLink(new AtomLink("edit",selfURL,"Atom+Xml"));
			addLink(new AtomLink("delete",selfURL+"/delete","Atom+Xml"));
		}
		else addLink(new AtomLink("alternate",selfURL,MediaType.APPLICATION_ATOM_XML));
	}
	
	public Repas getContents(){
		return content;
	}	

}
