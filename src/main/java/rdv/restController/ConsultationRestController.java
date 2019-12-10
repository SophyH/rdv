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
import rdv.model.jsonViews.JsonViews;
import rdv.repository.ConsultationRepository;

@RestController
@RequestMapping({"/consultation"})
@CrossOrigin(origins= {"*"})
public class ConsultationRestController {
	
	@Autowired
	private ConsultationRepository consultationRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping()
	public ResponseEntity<List<Consultation>> findAll() {
		return new ResponseEntity<>(consultationRepository.findAll(), HttpStatus.OK);
	}
	
	private ResponseEntity<Consultation> findByKey(@PathVariable("key") Integer key) {
		Optional<Consultation> opt=consultationRepository.findById(key);
		if (opt.isPresent()) {
			return new ResponseEntity<Consultation>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Consultation>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{key}")
	public ResponseEntity<Consultation> findById(@PathVariable("key") Integer key) {
		return findByKey(key);
	}
	
	@DeleteMapping("/{key}")
	public ResponseEntity<Void> delete(@PathVariable("key") Integer key) {
		Optional<Consultation> opt=consultationRepository.findById(key);
		if (opt.isPresent()) {
			consultationRepository.deleteById(key);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
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
		headers.setLocation(uCB.path("/consultation/{id}").buildAndExpand(consultation.getKey()).toUri());
		return new ResponseEntity<>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("{/key}")
	public ResponseEntity<Void> update(@PathVariable("key") Integer key, @Valid @RequestBody Consultation consultation, BindingResult br) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Consultation> opt = consultationRepository.findById(key);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Consultation consultationEnBase = opt.get();
		consultationEnBase.setDate(consultation.getDate());
		consultationEnBase.setMotif(consultation.getMotif());
		consultationEnBase.setStatus(consultation.getStatus());
		consultationRepository.save(consultationEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	} 
	
}
