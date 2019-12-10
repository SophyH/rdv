package rdv.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.jsonViews.JsonViews;

@Entity
@Table(name = "adresse")
@SequenceGenerator(name = "seqAdresse", sequenceName = "seq_adresse", initialValue = 100, allocationSize = 1)
public class Adresse {
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAdresse")
	private Integer id;
	@JsonView(JsonViews.Common.class)
	private String adresse;
	@JsonView(JsonViews.Common.class)
	@Column(name = "code_postal", length = 5)
	private String codePostal;
	@JsonView(JsonViews.Common.class)
	private String ville;
	@OneToMany(mappedBy = "key.adresse")
	private Set<PraticienAdresse> praticienAdresses;

	public Adresse() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Set<PraticienAdresse> getPraticienAdresses() {
		return praticienAdresses;
	}

	public void setPraticienAdresses(Set<PraticienAdresse> praticienAdresses) {
		this.praticienAdresses = praticienAdresses;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Adresse other = (Adresse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
