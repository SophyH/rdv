package rdv.restController;

import java.util.List;
import java.util.Optional;

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
import rdv.model.ConsultationPk;
import rdv.model.Patient;
import rdv.model.Personne;
import rdv.model.Praticien;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.ConsultationRepository;
import rdv.repository.PersonneRepository;

@RestController
@RequestMapping({"/consultation"})
@CrossOrigin(origins= {"*"})
public class ConsultationRestController {
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@Autowired
	private PersonneRepository personneRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping()
	public ResponseEntity<List<Consultation>> findAll() {
		return new ResponseEntity<>(consultationRepository.findAll(), HttpStatus.OK);
	}
	
	private ResponseEntity<Consultation> findByKey(@PathVariable("numeroConsultation") Integer numeroConsultation) {
		Optional<Consultation> opt=consultationRepository.findByNumeroConsultation(numeroConsultation);
		if (opt.isPresent()) {
			return new ResponseEntity<Consultation>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Consultation>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{numeroConsultation}")
	public ResponseEntity<Consultation> findById(@PathVariable("numeroConsultation") Integer numeroConsultation) {
		return findByKey(numeroConsultation);
	}
	
	
	@DeleteMapping("/{idPatient}&{idPraticien}")
	public ResponseEntity<Void> delete(@PathVariable("idPatient") Integer idPatient, @PathVariable("idPraticien") Integer idPraticien) {
		Optional<Personne> opt1 = personneRepository.findById(idPatient);
		Optional<Personne> opt2 = personneRepository.findById(idPraticien);
		Patient patient = null;
		Praticien praticien = null;
		Consultation consultation = null;
		if (opt1.isPresent() && opt2.isPresent()) {
			patient = (Patient) opt1.get();
			praticien = (Praticien) opt2.get();
			Optional<Consultation> opt=consultationRepository.findById(new ConsultationPk(patient, praticien));
			if (opt.isPresent()) {
				consultation = opt.get();
				consultationRepository.delete(consultation);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);	
	}
	
	
	@PostMapping({"", "/"})
	public ResponseEntity<Void> insert(@Valid @RequestBody Consultation consultation, BindingResult br, UriComponentsBuilder uCB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		consultationRepository.save(consultation);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/consultation/{numeroConsultation}").buildAndExpand(consultation.getNumeroConsultation()).toUri());
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("{/numeroConsultation}")
	public ResponseEntity<Void> update(@PathVariable("numeroConsultation") Integer numeroConsultation, @Valid @RequestBody Consultation consultation, BindingResult br) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Consultation> opt = consultationRepository.findByNumeroConsultation(numeroConsultation);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Consultation consultationEnBase = opt.get();
		consultationEnBase.setDate(consultation.getDate());
		consultationEnBase.setMotif(consultation.getMotif());
		consultationEnBase.setStatus(consultation.getStatus());
		consultationEnBase.setNumeroConsultation(consultation.getNumeroConsultation());
		consultationRepository.save(consultationEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
}
