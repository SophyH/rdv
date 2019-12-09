package rdv.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Adresse {

	private String adresse;
	private String cabinet;
	private String codePostal;
	private String ville;
	@ManyToOne
	private Praticien praticien;

	public Adresse() {
		super();
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCabinet() {
		return cabinet;
	}

	public void setCabinet(String cabinet) {
		this.cabinet = cabinet;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

}
