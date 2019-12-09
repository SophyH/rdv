package rdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "consultation")
public class Consultation {
	@EmbeddedId
	private ConsultationPk key;
	@Column(name = "date_consultation")
	private Date date;
	@Column(name = "motif_consultation")
	private String motif;
	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private Status status;
	@OneToOne
	@JoinColumn(name = "creneau_id_consultation", foreignKey = @ForeignKey(name = "creneau_id_consultation_fk"))
	private Creneau creneau;
		
	@Version
	private int version;
	
	public Consultation() {
		
	}
	
	public Consultation(Date date, String motif, Status status, Creneau creneau, int version) {
		super();
		this.date = date;
		this.motif = motif;
		this.status = status;
		this.creneau = creneau;
		this.version = version;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Creneau getCreneau() {
		return creneau;
	}
	public void setCreneau(Creneau creneau) {
		this.creneau = creneau;
	}
	
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	
	public ConsultationPk getKey() {
		return key;
	}

	public void setKey(ConsultationPk key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		Consultation other = (Consultation) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
	
}
