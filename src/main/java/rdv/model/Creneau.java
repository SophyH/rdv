package rdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import rdv.model.jsonViews.JsonViews;

@Entity
@Table(name = "creneau")
@SequenceGenerator(name = "seqCreneau", sequenceName = "seq_creneau", initialValue = 100, allocationSize = 1 )
public class Creneau {
	@JsonView(JsonViews.Common.class)
	@Id
	@GeneratedValue(generator = "seqCreneau", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_creneau")
	private Integer id;
	@JsonView(JsonViews.Common.class)
	@JsonDeserialize(using = CustomerDateAndTimeDeserialize.class)
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern= "HH:mm:ss")
	@Column(name = "heure_debut_creneau")
	private Date heureDebut;
	@OneToOne(mappedBy = "creneau")
	private Consultation consultation;
	@ManyToOne
	@JoinColumn(name = "id_disponibilite_creneau", foreignKey = @ForeignKey(name = "creneau_id_disponibilite_fk"))
	private Disponibilite disponibilite;
	@Version
	private int version; 
	
	public Creneau() { 
		
	}
	
	public Creneau(Integer id, Date heureDebut, Consultation consultation, Disponibilite disponibilite, int version) {
		super();
		this.id = id;
		this.heureDebut = heureDebut;
		this.consultation = consultation;
		this.disponibilite = disponibilite;
		this.version = version;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getHeureDebut() {
		return heureDebut;
	}
	public void setHeureDebut(Date heureDebut) {
		this.heureDebut = heureDebut;
	}
	public Consultation getConsultation() {
		return consultation;
	}
	public void setConsultation(Consultation consultation) {
		this.consultation = consultation;
	}
		
	public Disponibilite getDisponibilite() {
		return disponibilite;
	}

	public void setDisponibilite(Disponibilite disponibilite) {
		this.disponibilite = disponibilite;
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
		Creneau other = (Creneau) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
