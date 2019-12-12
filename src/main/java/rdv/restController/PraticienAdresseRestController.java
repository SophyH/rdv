package rdv.restController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import rdv.model.Adresse;
import rdv.model.Personne;
import rdv.model.Praticien;
import rdv.model.PraticienAdresse;
import rdv.model.PraticienAdressePk;
import rdv.repository.AdresseRepository;
import rdv.repository.PersonneRepository;
import rdv.repository.PraticienAdresseRepository;

@CrossOrigin(origins = "{*}")
@RestController
@RequestMapping("/adresse&praticien")
public class PraticienAdresseRestController {

	@Autowired
	private PraticienAdresseRepository paRepository;

	@Autowired
	private PersonneRepository persRep;

	@Autowired
	private AdresseRepository adresseRep;

		//pour supprimer une association
	@DeleteMapping("/{ida}+{idp}")
	public ResponseEntity<Void> delete(@PathVariable("ida") Integer ida, @PathVariable("idp") Integer idp) {
		Optional<Personne> opt1 = persRep.findById(idp);
		Optional<Adresse> opt2 = adresseRep.findById(ida);
		Praticien praticien = new Praticien();
		Adresse adresse = new Adresse();
		if (opt1.isPresent() && opt2.isPresent() && opt1.get() instanceof Praticien) {
			praticien = (Praticien) opt1.get();
			adresse = opt2.get();
			PraticienAdressePk paPk = new PraticienAdressePk(praticien, adresse);
			Optional<PraticienAdresse> opt = paRepository.findById(paPk);
			if (opt.isPresent()) {
				paRepository.deleteById(paPk);
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}

