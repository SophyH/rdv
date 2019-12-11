package rdv.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

import rdv.model.Patient;
import rdv.model.Personne;
import rdv.model.Praticien;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.PersonneRepository;

@RestController
@RequestMapping({ "/", "" })
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping("/inscrits")
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
	@GetMapping("inscrits/id/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/{nom}")
	public ResponseEntity<List<Personne>> findByNomWithAll(@PathVariable("nom") String nom) {
		return findByName(nom);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens")
	public ResponseEntity<List<Praticien>> findAllPraticien() {
		return new ResponseEntity<>(personneRepository.findAllPraticien(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> findAllPatient() {
		return new ResponseEntity<>(personneRepository.findAllPatient(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/ville/{ville}")
	public ResponseEntity<List<Praticien>> findByVille(@PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllByVille(ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/specialite/{specialite}")
	public ResponseEntity<List<Praticien>> findBySpecialite(@PathVariable("specialite") String specialite) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialite(specialite);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/{specialite}&{ville}")
	public ResponseEntity<List<Praticien>> findBySpecialiteAndVille(@PathVariable("specialite") String specialite,
			@PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialiteAndVille(specialite, ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@DeleteMapping("inscrits/{id}")
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
		if (br.hasErrors()) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Optional<Personne> opt = personneRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		} else {
			Personne personneEnBase = opt.get();
			personneEnBase.setCivilite(personne.getCivilite());
			personneEnBase.setId(personne.getId());
			personneEnBase.setLogin(personne.getLogin());
			personneEnBase.setMail(personne.getMail());
			personneEnBase.setNom(personne.getNom());
			personneEnBase.setPrenom(personne.getPrenom());
			if (personneEnBase instanceof Patient) {
				((Patient) personneEnBase).setConsultations(((Patient) personne).getConsultations());
			} else if (personneEnBase instanceof Praticien) {
				((Praticien) personneEnBase).setConsultations(((Praticien) personne).getConsultations());
				((Praticien) personneEnBase).setDisponibilites(((Praticien) personne).getDisponibilites());
				((Praticien) personneEnBase).setPraticienAdresses(((Praticien) personne).getPraticienAdresses());
				((Praticien) personneEnBase).setSpecialites(((Praticien) personne).getSpecialites());
			}
			personneRepository.save(personneEnBase);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
	}

	@PutMapping("/patient/{id}")
	public ResponseEntity<Void> updatePatient(@PathVariable("id") Integer id, Patient patient, BindingResult br) {
		return update(id, patient, br);
	}

	@PutMapping("praticien/{id}")
	public ResponseEntity<Void> updatePraticien(@PathVariable("id") Integer id, Praticien praticien, BindingResult br) {
		return update(id, praticien, br);
	}

}
