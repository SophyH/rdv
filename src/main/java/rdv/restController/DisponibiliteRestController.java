package rdv.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import org.springframework.http.HttpHeaders;
import rdv.model.Disponibilite;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.DisponibiliteRepository;

@RequestMapping({"/disponibilite"})
@CrossOrigin(origins = {"*"})
@RestController
public class DisponibiliteRestController {

	@Autowired
	private DisponibiliteRepository disponibiliteRepository;
	
	@JsonView(JsonViews.DisponibiliteWithPraticien.class)
	@GetMapping({"", "/"})
	public ResponseEntity<List<Disponibilite>> findAll() {
		return new ResponseEntity<List<Disponibilite>>(disponibiliteRepository.findAll(), HttpStatus.OK);
	}
	
	@JsonView(JsonViews.DisponibiliteWithPraticien.class)
	@GetMapping("/{id}")
	public ResponseEntity<Disponibilite> findById(@PathVariable("id") Integer id){
		Optional<Disponibilite> opt = disponibiliteRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<Disponibilite>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<Disponibilite>(HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id")Integer id) {
		Optional<Disponibilite> opt = disponibiliteRepository.findById(id);
		if (opt.isPresent()) {
			disponibiliteRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping({"","/"})
	public ResponseEntity<Disponibilite> insert(@Valid @RequestBody Disponibilite disponibilite, BindingResult br, UriComponentsBuilder ucb){
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		disponibiliteRepository.save(disponibilite);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucb.path("/disponibilite/{id})").buildAndExpand(disponibilite.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Disponibilite> update(@PathVariable("id")Integer id, @Valid@RequestBody Disponibilite disponibilite, BindingResult br, UriComponentsBuilder ucb){
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Disponibilite> opt = disponibiliteRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		
		//re utilise une disponibilite existante avec ses infos pour repositioner celle qui est updatee
		Disponibilite dispoEnBase = opt.get();
		dispoEnBase.setJour(disponibilite.getJour());
		dispoEnBase.setCreneaux(disponibilite.getCreneaux());
		dispoEnBase.setDuree(dispoEnBase.getDuree());
		dispoEnBase.setHdebut(disponibilite.getHdebut());
		dispoEnBase.setHfin(disponibilite.getHfin());
		dispoEnBase.setPraticien(disponibilite.getPraticien());
		disponibiliteRepository.save(dispoEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
