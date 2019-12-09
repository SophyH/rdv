package rdv.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class PraticienAdressePk implements Serializable {

	@ManyToOne
	@JoinColumn(name = "id_praticien_adresse_praticien", foreignKey = @ForeignKey(name = "praticien_adresse_praticien_id_fk"))
	private Praticien praticien;
	@ManyToOne
	@JoinColumn(name = "id_adresse_adresse_praticien", foreignKey = @ForeignKey(name = "praticien_adresse_adresse_id_fk"))
	private Adresse adresse;

	public PraticienAdressePk() {
		super();
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adresse == null) ? 0 : adresse.hashCode());
		result = prime * result + ((praticien == null) ? 0 : praticien.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PraticienAdressePk other = (PraticienAdressePk) obj;
		if (adresse == null) {
			if (other.adresse != null)
				return false;
		} else if (!adresse.equals(other.adresse))
			return false;
		if (praticien == null) {
			if (other.praticien != null)
				return false;
		} else if (!praticien.equals(other.praticien))
			return false;
		return true;
	}

}