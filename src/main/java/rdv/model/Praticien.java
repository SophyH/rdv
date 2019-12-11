package rdv.model;

import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.jsonViews.JsonViews;

@Entity
@DiscriminatorValue("Dr")
public class Praticien extends Personne {

	@JsonView(JsonViews.PersonneWithAll.class)
	@OneToMany(mappedBy = "key.praticien")
	private Set<Consultation> consultations;
	@JsonView(JsonViews.PersonneWithAll.class)
	@OneToMany(mappedBy = "praticien")
	private Set<Specialite> specialites;
	@JsonView(JsonViews.PersonneWithAll.class)
	@OneToMany(mappedBy = "praticien")
	private Set<Disponibilite> disponibilites;
	@JsonView(JsonViews.PraticiensWithAdresse.class)
	@OneToMany(mappedBy = "key.praticien")
	private Set<PraticienAdresse> praticienAdresses;

	public Praticien() {
		super();
	}

	public Set<Consultation> getConsultations() {
		return consultations;
	}

	public void setConsultations(Set<Consultation> consultations) {
		this.consultations = consultations;
	}

	public Set<Specialite> getSpecialites() {
		return specialites;
	}

	public void setSpecialites(Set<Specialite> specialites) {
		this.specialites = specialites;
	}

	public Set<Disponibilite> getDisponibilites() {
		return disponibilites;
	}

	public void setDisponibilites(Set<Disponibilite> disponibilites) {
		this.disponibilites = disponibilites;
	}

	public Set<PraticienAdresse> getPraticienAdresses() {
		return praticienAdresses;
	}

	public void setPraticienAdresses(Set<PraticienAdresse> praticienAdresses) {
		this.praticienAdresses = praticienAdresses;
	}

}
