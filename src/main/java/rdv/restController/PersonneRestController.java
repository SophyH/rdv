package rdv.restController;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.Consultation;
import rdv.model.Login;
import rdv.model.Patient;
import rdv.model.Personne;
import rdv.model.Praticien;
import rdv.model.PraticienAdresse;
import rdv.model.jsonViews.JsonViews;
import rdv.model.jsonViews.JsonViews.PersonneWithAll;
import rdv.repository.AdresseRepository;
import rdv.repository.ConsultationRepository;
import rdv.repository.LoginRepository;
import rdv.repository.PersonneRepository;
import rdv.repository.PraticienAdresseRepository;

@RestController
@RequestMapping("/inscrits")
@CrossOrigin(origins = { "*" })
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

	@Autowired
	private ConsultationRepository consultationRepository;

	@Autowired
	private PraticienAdresseRepository paRep;

	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private AdresseRepository adresseRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping({ "", "/" })
	public ResponseEntity<List<Personne>> findAll() {
		return new ResponseEntity<>(personneRepository.findAll(), HttpStatus.OK);
	}

	private ResponseEntity<Personne> findByKey(Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Personne>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<Personne>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<List<Personne>> findByName(String nom) {
		List<Personne> list = personneRepository.findAllByNom(nom);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/id/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/{nom}")
	public ResponseEntity<List<Personne>> findByNomWithAll(@PathVariable("nom") String nom) {
		return findByName(nom);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticien/{id}/patients")
	public ResponseEntity<List<Patient>> findAllPatientByPraticien(@PathVariable("id") Integer id) {
		List<Patient> list = personneRepository.findAllPatientByIdPraticien(id);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens")
	public ResponseEntity<List<Praticien>> findAllPraticien() {
		return new ResponseEntity<>(personneRepository.findAllPraticienWithAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> findAllPatient() {
		return new ResponseEntity<>(personneRepository.findAllPatientWithAll(), HttpStatus.OK);
	}

	@JsonView(PersonneWithAll.class)
	@GetMapping("/praticiens/nom/{nom}")
	public ResponseEntity<List<Praticien>> findPraticienByNom(@PathVariable("nom") String nom) {
		List<Praticien> list = personneRepository.findByNomPraticien(nom);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(PersonneWithAll.class)
	@GetMapping("/patients/nom/{nom}")
	public ResponseEntity<List<Patient>> findPatientByNom(@PathVariable("nom") String nom) {
		List<Patient> list = personneRepository.findByNomPatient(nom);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/ville/{ville}")
	public ResponseEntity<List<Praticien>> findPraticienByVille(@PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllByVille(ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/specialite/{specialite}")
	public ResponseEntity<List<Praticien>> findPraticienBySpecialite(@PathVariable("specialite") String specialite) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialite(specialite);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/{specialite}&{ville}")
	public ResponseEntity<List<Praticien>> findPraticienBySpecialiteAndVille(
			@PathVariable("specialite") String specialite, @PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialiteAndVille(specialite, ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Personne> opt = personneRepository.findById(id);
		if (opt.isPresent()) {
			personneRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<Void> insert(Personne personne, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		personneRepository.save(personne);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/inscrits/{id}").buildAndExpand(personne.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@PostMapping("/patient")
	public ResponseEntity<Void> insertPatient(@Valid @RequestBody Patient patient, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(patient, br, uCB);
	}

	@PostMapping("/praticien")
	public ResponseEntity<Void> insertPraticien(@Valid @RequestBody Praticien praticien, BindingResult br,
			UriComponentsBuilder uCB) {
		return insert(praticien, br, uCB);
	}

	private ResponseEntity<Void> update(Integer id, Personne personne, BindingResult br) {
		Set<Consultation> cons = new HashSet<>();
		Set<PraticienAdresse> praA = new HashSet<>();
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Optional<Personne> opt = personneRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			Personne personneEnBase = opt.get();
			personneEnBase.setCivilite(personne.getCivilite());
			Optional<Login> optL = loginRepository.findById(personne.getLogin().getUsername());
			if (optL.isPresent()) {
				Login loginEnBase = optL.get();
				loginEnBase.setPassword(personne.getLogin().getPassword());
				loginRepository.save(loginEnBase);
				personneEnBase.setLogin(loginEnBase);
			}
			personneEnBase.setMail(personne.getMail());
			personneEnBase.setNom(personne.getNom());
			personneEnBase.setPrenom(personne.getPrenom());
			if (personneEnBase instanceof Patient) {
				List<Consultation> consultations = consultationRepository.findAllByIdPatient(id);
				for (Consultation c : consultations) {
					cons.add(c);
				}
				((Patient) personneEnBase).setConsultations(cons);
			} else if (personneEnBase instanceof Praticien) {
				List<Consultation> consultations = consultationRepository.findAllByIdPraticien(id);
				for (Consultation c : consultations) {
					cons.add(c);
				}
				((Praticien) personneEnBase).setConsultations(cons);
				((Praticien) personneEnBase).setDisponibilites(((Praticien) personne).getDisponibilites());
				List<PraticienAdresse> praticienAdresses = paRep.findAllByIdPraticient(id);
				for (PraticienAdresse pa : praticienAdresses) {
					praA.add(pa);
				}
				((Praticien) personneEnBase).setPraticienAdresses(praA);
				((Praticien) personneEnBase).setSpecialites(((Praticien) personne).getSpecialites());
			}
			personneRepository.save(personneEnBase);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}

	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<Void> updatePatient(@PathVariable("id") Integer id, @Valid @RequestBody Patient patient,
			BindingResult br) {
		return update(id, patient, br);
	}

	@PutMapping("/praticien/{id}")
	public ResponseEntity<Void> updatePraticien(@PathVariable("id") Integer id, @Valid @RequestBody Praticien praticien,
			BindingResult br) {
		return update(id, praticien, br);
	}

}
