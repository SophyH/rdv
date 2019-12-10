package rdv.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rdv.repository.CreneauRepository;

@RestController
@RequestMapping({"/creneau"})
@CrossOrigin(origins = {"*"})
public class CreneauRestController {
	
	@Autowired
	CreneauRepository creneauRepository;

}
