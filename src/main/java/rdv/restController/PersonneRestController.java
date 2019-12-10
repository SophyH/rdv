package rdv.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
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
		return new ResponseEntity<List<Praticien>>(personneRepository.findAllPraticien(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/patients")
	public ResponseEntity<List<Patient>> findAllPatient() {
		return new ResponseEntity<List<Patient>>(personneRepository.findAllPatient(), HttpStatus.OK);
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/{ville}")
	public ResponseEntity<List<Praticien>> findByVille(@PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllByVille(ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/{specialite}")
	public ResponseEntity<List<Praticien>> findBySpecialite(@PathVariable("specialite") String specialite) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialite(specialite);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@JsonView(JsonViews.PersonneWithAll.class)
	@GetMapping("/praticiens/{specialite}/{ville}")
	public ResponseEntity<List<Praticien>> findBySpecialiteAndVille(@PathVariable("specialite") String specialite,
			@PathVariable("ville") String ville) {
		List<Praticien> list = personneRepository.findAllPraticienWithAllBySpecialiteAndVille(specialite, ville);
		if (list.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

}
