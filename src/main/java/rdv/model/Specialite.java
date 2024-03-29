package rdv.model;

import java.util.Date;
import java.util.Optional;

import javax.jws.soap.SOAPBinding.Style;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.boot.convert.DurationFormat;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import rdv.model.jsonViews.JsonViews;

@Entity
@Table(name = "specialite")
@SequenceGenerator(name = "seqSpecialite", sequenceName = "seq_specialite", initialValue = 100, allocationSize = 5)
public class Specialite {
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(generator = "seqSpecialite", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_specialite")
	private Integer id;
	@JsonView(JsonViews.Common.class)
	@Column(name = "specialite_specialite", length = 100)
	private String specialite;
	@JsonView(JsonViews.Common.class)
	@Column(name = "duree_specialite", length = 100)
	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(style = "HH:mm:ss")
	private Date duree;
	@JsonView(JsonViews.SpecialiteWithPraticien.class)
	@ManyToOne
	@JoinColumn(name = "praticien_id_specialite", foreignKey = @ForeignKey(name = "praticien_id_specialite_fk"))
	private Praticien praticien;
	@Version
	private int version;
	
	public Specialite() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSpecialite() {
		return specialite;
	}

	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}

	public Date getDuree() {
		return duree;
	}

	public void setDuree(Date duree) {
		this.duree = duree;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
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
		Specialite other = (Specialite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
