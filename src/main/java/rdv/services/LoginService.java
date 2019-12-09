package rdv.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rdv.exceptions.LoginCreationException;
import rdv.model.Login;
import rdv.repository.LoginRepository;

@Service
public class LoginService {

	@Autowired
	private LoginRepository loginRepository;
	
//	public Login create(String username) throws LoginCreationException {
//		if(password.isEmpty()) { throw new LoginCreationException();}
//		Login login = new Login();
//		LoginRepository.save(login);
//		return login;
//	}
			
		
		
//	public Salle create(String nom) throws SalleCreationException {
//	if (nom.isEmpty()) {
//		throw new SalleCreationException();
//	}
//	Salle salle = new Salle(nom);
//	salleRepository.save(salle);
//	return login;
//}
	}
	
	
//	public void delete (Login login) {
//		Optional<Login> opt = LoginRepository.find

	//		Optional<Salle> opt = salleRepository.findByIdWithPersonnes(salle.getId());
//		if (opt.isPresent()) {
//			salle = opt.get();
//			Set<Personne> personnes = salle.getPersonnes();
//			for (Personne personne : personnes) {
//				personne.setSalle(null);
//				personneRepository.save(personne);
//			}
//			salleRepository.delete(salle);
//		}

//	}
	
//}









