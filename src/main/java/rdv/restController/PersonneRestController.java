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

import rdv.model.Patient;
import rdv.model.Personne;
import rdv.model.Praticien;
import rdv.repository.PersonneRepository;

@RestController
@RequestMapping({ "/", "" })
public class PersonneRestController {

	@Autowired
	private PersonneRepository personneRepository;

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

	@GetMapping("/{id}")
	public ResponseEntity<Personne> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}

	@GetMapping("/{nom}")
	public ResponseEntity<List<Personne>> findByNom(@PathVariable("nom") String nom) {
		return findByName(nom);
	}

	@GetMapping("/praticien")
	public ResponseEntity<List<Praticien>> findAllPraticien() {
		return new ResponseEntity<List<Praticien>>(personneRepository.findAllPraticien(), HttpStatus.OK);
	}

	@GetMapping("/patient")
	public ResponseEntity<List<Patient>> findAllPatient() {
		return new ResponseEntity<List<Patient>>(personneRepository.findAllPatient(), HttpStatus.OK);
	}

}
