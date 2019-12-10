package rdv.restController;

import org.springframework.web.bind.annotation.RestController;

public class LoginRestController {

}

////expliquer à ce service qui a le droit d'aller le chercher (moi j'ai mis j'accepte les requêtes de partout):
//@CrossOrigin(origins = {"*"})
//@RestController
//@RequestMapping("/rest/salle")
//public class SalleRestController {
//
//	//==============================================================
//	// Exemple:
////	@GetMapping("/hello")
////	public ResponseEntity <String> helloWorld(){
////		return new ResponseEntity<String>("hello world", HttpStatus.OK);
////	}
//	//==============================================================
//	
//	
//	@Autowired
//	private SalleRepository salleRepository;
//
//	// Pour remonter toutes les salles
//	@JsonView(JsonViews.Common.class)
//	@GetMapping({ "", "/" })
//	ResponseEntity<List<Salle>> findAll() {
//		return new ResponseEntity<>(salleRepository.findAll(), HttpStatus.OK);
//	}
//	
//	@JsonView(JsonViews.SalleWithPersonne.class)
//	@GetMapping("/{id}/personne")
//	public ResponseEntity<Salle> findByIdWithPersonne(@PathVariable("id") Integer id) {
//		Optional<Salle> opt = salleRepository.findById(id);
//		if (opt.isPresent()) {
//			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(opt.get(), HttpStatus.NOT_FOUND);
//	}
//	
//	@JsonView(JsonViews.Common.class)
//	// Pour remonter une seule salle
//	@GetMapping("/{id}")
//	public ResponseEntity<Salle> findById(@PathVariable("id") Integer id) {
//		Optional<Salle> opt = salleRepository.findById(id);
//		if (opt.isPresent()) {
//			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
//		}
//		return new ResponseEntity<>(opt.get(), HttpStatus.NOT_FOUND);
//	}
//
//	// Pour supprimer une salle -> classe void qui encapsule l'objet Void
//	@DeleteMapping("/{id}")
//	public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
//		Optional<Salle> opt = salleRepository.findById(id);
//		if (opt.isPresent()) {
//			salleRepository.deleteById(id);
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
//	}
//	
//	//Pour créer une salle
//	@PostMapping({"","/"})
//	public ResponseEntity <Void> insert(@Valid @RequestBody Salle salle, BindingResult br, UriComponentsBuilder uCB) {
//		if(br.hasErrors()) {
//			return new ResponseEntity <> (HttpStatus.BAD_REQUEST);
//		}
//		salleRepository.save(salle);
//		HttpHeaders headers = new HttpHeaders();	
//		headers.setLocation(uCB.path("/rest/salle/{id}").buildAndExpand(salle.getId()).toUri());
//		
//		return new ResponseEntity<>(headers, HttpStatus.CREATED);
//	}
//	
//	@PutMapping("/{id}")
//	public ResponseEntity<Void> update(@PathVariable("id")Integer id, @Valid @RequestBody Salle salle, BindingResult br){
//		if(br.hasErrors()) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		Optional<Salle> opt = salleRepository.findById(id);
//		if(!opt.isPresent()) {
//			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
//		}
//		Salle salleEnBase = opt.get();
//		salleEnBase.setNom(salle.getNom());
//		salleRepository.save(salleEnBase);
//		return new ResponseEntity<> (HttpStatus.OK);
//	}
//}
