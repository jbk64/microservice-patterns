package business;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType( XmlAccessType.FIELD)
public class DTOCommande {
	private String urlRepas;
	private String urlBoisson;
	private String urlClient;

	public DTOCommande() {}

	public DTOCommande(String urlRepas, String urlBoisson, String urlClient) {
		this.urlRepas = urlRepas;
		this.urlBoisson = urlBoisson;
		this.urlClient = urlClient;
	}

	public String getUrlRepas() {
		return urlRepas;
	}

	public String getUrlBoisson() {
		return urlBoisson;
	}

	public String getUrlClient() {
		return urlClient;
	}

	@Override
	public String toString() {
		return "DTOCommande{" +
				"urlRepas='" + urlRepas + '\'' +
				", urlBoisson='" + urlBoisson + '\'' +
				", urlClient='" + urlClient + '\'' +
				'}';
	}
}
