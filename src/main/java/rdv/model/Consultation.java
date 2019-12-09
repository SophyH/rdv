package rdv.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
@Table(name = "consultation")
@SequenceGenerator(name = "seqConsultation", sequenceName = "seq_consultation", initialValue = 100, allocationSize = 1)
public class Consultation {
	@Id
	@GeneratedValue(generator = "seqConsultation", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_consultation")
	private Integer id;
	@Column(name = "date_consultation")
	private Date date;
	@Column(name = "motif_consultation")
	private String motif;
	@Column(name = "status")
	private Status status;
	@OneToOne
	@JoinColumn(name = "creneau_id_consultation", foreignKey = @ForeignKey(name = "creneau_id_consultation_fk"))
	private Creneau creneau;
	
	@EmbeddedId
	private ConsultationPk key;
	
	
	@Version
	private int version;
	
	public Consultation() {
		
	}
	
	public Consultation(Integer id, Date date, String motif, Status status, Creneau creneau, int version) {
		super();
		this.id = id;
		this.date = date;
		this.motif = motif;
		this.status = status;
		this.creneau = creneau;
		this.version = version;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
		Consultation other = (Consultation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
