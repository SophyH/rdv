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
import rdv.model.Adresse;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.AdresseRepository;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/adresse")
public class AdresseRestController {

	@Autowired
	private AdresseRepository adresseRepository;
	
	//pour remonter les adresses
	@JsonView(JsonViews.Common.class)
	@GetMapping({"","/"})
	ResponseEntity<List<Adresse>> findAll(){
		return new ResponseEntity<> (adresseRepository.findAll(),HttpStatus.OK);
	}
	
	//pour remonter une seule adresse
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Adresse> findById(@PathVariable("id") Integer id){
		Optional<Adresse> opt = adresseRepository.findById(id);
		if(opt.isPresent()) {
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<> (opt.get(), HttpStatus.NOT_FOUND);
	}
	
	//pour supprimer une adresse
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
		Optional<Adresse> opt = adresseRepository.findById(id);
		if(opt.isPresent()) {
			adresseRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	//pour créer une adresse
	@PostMapping({"","/"})
	public ResponseEntity<Void> insert(@Valid @RequestBody Adresse adresse, BindingResult br, UriComponentsBuilder uCB){
		if(br.hasErrors()) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		adresseRepository.save(adresse);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(uCB.path("/adresse/{id}").buildAndExpand(adresse.getId()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);	
	}
	
	//modifier une adresse
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable("id") Integer id, @Valid @RequestBody Adresse adresse, BindingResult br){
		if(br.hasErrors()) {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		Optional<Adresse> opt = adresseRepository.findById(id);
		if(!opt.isPresent()) {
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		Adresse adresseEnBase = opt.get();
		adresseEnBase.setAdresse(adresse.getAdresse());
		adresseEnBase.setCodePostal(adresse.getCodePostal());
		adresseEnBase.setVille(adresse.getVille());
		adresseRepository.save(adresseEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	//Pour une adresse, je cherche tous les praticiens et je le fais pour toutes les adresses
	@JsonView(JsonViews.Common.class)
	@GetMapping("/praticiens")
	public ResponseEntity<List<Adresse>> findAllWithPraticien() {
		return new ResponseEntity<>(adresseRepository.findAllWithPraticien(), HttpStatus.OK);	
	}
	
	//Si je donne le nom d'un praticien je retrouve ses adresses
	@JsonView(JsonViews.AdresseWithPraticien.class)
	@GetMapping("/praticien/{nom}")
	public ResponseEntity<List<Adresse>> findAllWithPraticienByNomPraticien(@PathVariable("nom") String nom){
		return new ResponseEntity<>(adresseRepository.findAllWithPraticienByNomPraticien(nom), HttpStatus.OK);
	}
}
