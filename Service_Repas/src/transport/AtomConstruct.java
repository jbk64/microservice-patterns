package transport;

import java.util.ArrayList;
import javax.xml.bind.annotation.*;

public abstract class AtomConstruct {
	
	@XmlElement
	private String id;
	@XmlElement
	private String title;
	@XmlElement
	private String updated;
	@XmlElement(name="link")
	private ArrayList<AtomLink> links = new ArrayList<AtomLink>();
	/*
	@XmlAttribute
	private String xmlns="http://www.w3.org/2005/Atom";
	*/

	protected void addLink(AtomLink l){links.add(l);}
	
	protected void setId(String id) {this.id = id;}
	protected String getId() {return id;}
	protected void setTitle(String title) {this.title = title;}
	protected void setUpdated(String updated) {this.updated = updated;}
	
	public String getTitle() {return title;}
	public ArrayList<AtomLink> getLinks(){return links;}
}
