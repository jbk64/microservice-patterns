package transport;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import business.Client;


@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomClient extends AtomConstruct{
	
	@XmlElement(name="content")
	private Client content;
	
	public  AtomClient() {}
	public AtomClient(String selfURL,Client c ,boolean fullEntry){
		setId(selfURL);
		setTitle("Client :"+c.getNom());
		setUpdated(Calendar.getInstance().getTime().toString());
		
		if(fullEntry) {
			this.content = c;
			addLink(new AtomLink("edit",selfURL,"Atom+Xml"));
			addLink(new AtomLink("delete",selfURL+"/delete","Atom+Xml"));
		}
		else addLink(new AtomLink("alternate",selfURL,MediaType.APPLICATION_ATOM_XML));
	}
	
	public Client getContents(){
		return content;
	}	

}
