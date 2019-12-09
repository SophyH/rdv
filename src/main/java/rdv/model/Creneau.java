package rdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "creneau")
@SequenceGenerator(name = "seqCreneau", sequenceName = "seq_creneau", initialValue = 100, allocationSize = 1 )
public class Creneau {
	@Id
	@GeneratedValue(generator = "seqCreneau", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_creneau")
	private Integer id;
	@Column(name = "heure_debut_creneau")
	private Date heureDebut;
	@OneToOne
	@JoinColumn(name = "consultation_id_creneau", foreignKey = @ForeignKey(name = "consultation_id_creneau_fk"))
	private Consultation consultation;
	@Version
	private int version;
	
	public Creneau() {
		
	}
	
	public Creneau(Integer id, Date heureDebut, Consultation consultation, int version) {
		super();
		this.id = id;
		this.heureDebut = heureDebut;
		this.consultation = consultation;
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
