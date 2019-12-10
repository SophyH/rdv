package rdv.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.Creneau;
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
	
}
