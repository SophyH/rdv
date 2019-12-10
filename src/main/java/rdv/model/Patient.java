package rdv.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.jsonViews.JsonViews;

@Entity
@DiscriminatorValue("Pa")
public class Patient extends Personne {

	@JsonView(JsonViews.PatientWithAll.class)
	@OneToMany(mappedBy = "key.patient")
	private Set<Consultation> consultations;

	public Patient() {
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

}
