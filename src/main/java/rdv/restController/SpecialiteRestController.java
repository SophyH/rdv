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

import rdv.model.Specialite;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.SpecialiteRepository;

@CrossOrigin(origins = { "*" })
@RequestMapping("/specialite")
@RestController
public class SpecialiteRestController {

	@Autowired
	private SpecialiteRepository specialiteRepository;

	@JsonView(JsonViews.Common.class)
	@GetMapping({ "", "/s" })
	public ResponseEntity<List<Specialite>> findAll() {
		return new ResponseEntity<List<Specialite>>(specialiteRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Specialite> findById(@PathVariable("id") Integer id) {
		Optional<Specialite> opt = specialiteRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Specialite>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Specialite>(HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
		Optional<Specialite> opt = specialiteRepository.findById(id);
		if (opt.isPresent()) {
			specialiteRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@PostMapping({ "", "/" })
	public ResponseEntity<Specialite> insert(@Valid @PathVariable Specialite specialite, BindingResult br,
			UriComponentsBuilder ucb) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		specialiteRepository.save(specialite);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("specialite/{id})").buildAndExpand(specialite.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@JsonView(JsonViews.SpecialiteWithPraticien.class)
	@PutMapping("/{id}")
	public ResponseEntity<Specialite> update(@PathVariable("id") Integer id, @Valid@RequestBody Specialite specialite, BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Specialite> opt = specialiteRepository.findById(id);
		if(!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Specialite specialitEnBase = opt.get();
		specialitEnBase.setDuree(specialite.getDuree());
		specialitEnBase.setPraticien(specialite.getPraticien());
		specialitEnBase.setSpecialite(specialite.getSpecialite());
		specialiteRepository.save(specialitEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
