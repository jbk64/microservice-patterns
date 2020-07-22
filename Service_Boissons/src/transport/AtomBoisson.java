package transport;

import java.util.Calendar;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import business.Boisson;


@XmlRootElement(name="entry")
@XmlAccessorType(XmlAccessType.NONE)
public class AtomBoisson extends AtomConstruct{
	
	@XmlElement(name="content")
	private Boisson content;
	
	public  AtomBoisson() {}
	public AtomBoisson(String selfURL,Boisson b ,boolean fullEntry){
		setId(selfURL);
		setTitle("Boisson :"+b.getNom());
		setUpdated(Calendar.getInstance().getTime().toString());
		
		if(fullEntry) {
			this.content = b;
			addLink(new AtomLink("edit",selfURL,"Atom+Xml"));
			addLink(new AtomLink("delete",selfURL+"/delete","Atom+Xml"));
		}
		else addLink(new AtomLink("alternate",selfURL,MediaType.APPLICATION_ATOM_XML));
	}
	
	public Boisson getContents(){
		return content;
	}	

}
