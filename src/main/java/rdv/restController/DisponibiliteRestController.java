package rdv.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rdv.model.Disponibilite;
import rdv.repository.DisponibiliteRepository;

@RequestMapping({"/disponibilite"})
@CrossOrigin(origins = {"*"})
@RestController
public class DisponibiliteRestController {

	@Autowired
	private DisponibiliteRepository disponibiliteRepository;
	
	@GetMapping({"", "/"})
	public ResponseEntity<List<Disponibilite>> findAll() {
		return new ResponseEntity<List<Disponibilite>>(disponibiliteRepository.findAll(), HttpStatus.OK);
	}
}
