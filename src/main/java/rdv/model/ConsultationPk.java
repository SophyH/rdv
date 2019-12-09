package rdv.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class ConsultationPk implements Serializable{
	@ManyToOne
	@JoinColumn(name = "id_patient_consultation", foreignKey = @ForeignKey(name = "consultation_patient_id_fk"))
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "id_praticien_consultation", foreignKey = @ForeignKey(name = "consultation_praticien_id_fk"))
	private Praticien praticien;
	
	public ConsultationPk() {
		super();
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Praticien getPraticien() {
		return praticien;
	}

	public void setPraticien(Praticien praticien) {
		this.praticien = praticien;
	}
		
}
