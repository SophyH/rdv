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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import rdv.model.Creneau;
import rdv.model.CustomerDateAndTimeDeserialize;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.CreneauRepository;

@RestController
@RequestMapping({"/creneau"})
@CrossOrigin(origins = {"*"})
public class CreneauRestController {
	
	@Autowired
	CreneauRepository creneauRepository;
	
	@JsonView(JsonViews.Common.class)
	@GetMapping()
	public ResponseEntity<List<Creneau>> findAll() {
		return new ResponseEntity<>(creneauRepository.findAll(), HttpStatus.OK);
	}
	
	private ResponseEntity<Creneau> findByKey(@PathVariable("id") Integer id) {
		Optional<Creneau> opt=creneauRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<Creneau>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Creneau>(HttpStatus.NOT_FOUND);
	}
	
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Creneau> findById(@PathVariable("id") Integer id) {
		return findByKey(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Creneau> opt=creneauRepository.findById(id);
		if(opt.isPresent()) {
			creneauRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping({"", "/"})
	public ResponseEntity<Void> insert(@Valid @RequestBody Creneau creneau, BindingResult br, UriComponentsBuilder uCB) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		creneauRepository.save(creneau);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/creneau/{id}").buildAndExpand(creneau.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody Creneau creneau, BindingResult br) {
		if(br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Creneau> opt = creneauRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Creneau creneauEnBase = opt.get();
		creneauEnBase.setHeureDebut(creneau.getHeureDebut());
		creneauRepository.save(creneauEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
