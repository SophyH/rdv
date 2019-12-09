package rdv.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "disponibilite")
@SequenceGenerator(name = "seqDisponibilite", sequenceName = "seq_disponibilite", initialValue = 100, allocationSize = 1)
public class Disponibilite {
	@Id
	@GeneratedValue(generator = "seqDisponibilite", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_disponibilite")
	private Integer id;
	@Column(name = "hdebut_disponibilite", length = 100)
	private Date hdebut;
	@Column(name = "hfin_disponibilite", length = 100)
	private Date hfin;
	@Column(name = "jour_disponibilite", length = 100)
	private Jour jour;
	@Column(name = "duree_disponibilite", length = 100)
	private Date duree;
	@OneToMany(mappedBy = "")
	@Column(name = "creneaux_disponibilite", length = 100)
	private Set<Creneau> creneaux;
	@ManyToOne
	@JoinColumn(name = "praticien_id_disponibilite", foreignKey = @ForeignKey(name = "praticien_id_disponibilite_fk"))
	private Praticien praticien;
	@Version
	private int version;

	public Disponibilite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getHdebut() {
		return hdebut;
	}

	public void setHdebut(Date hdebut) {
		this.hdebut = hdebut;
	}

	public Date getHfin() {
		return hfin;
	}

	public void setHfin(Date hfin) {
		this.hfin = hfin;
	}

	public Jour getJour() {
		return jour;
	}

	public void setJour(Jour jour) {
		this.jour = jour;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public Set<Creneau> getCreneaux() {
		return creneaux;
	}

	public void setCreneaux(Set<Creneau> creneaux) {
		this.creneaux = creneaux;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
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
		Disponibilite other = (Disponibilite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
