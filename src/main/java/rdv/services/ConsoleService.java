//package rdv.services;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import rdv.model.Login;
//import rdv.repository.LoginRepository;
//
//@Service
//public class ConsoleService implements CommandLineRunner{
//
//	//====================================================================
//	//							Pour encoder un mdp
//	//====================================================================
//	//Il faut le faire tourner une seule fois puis mettre en commentaire
//	
//	@Autowired
//	private LoginRepository loginRepository;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Override
//	public void run(String... args) throws Exception {
////		for(Login login:loginRepository.findAll()) {
////			login.setPassword(passwordEncoder.encode(login.getUsername()));
////			loginRepository.save(login);
////		}
//	}
//}
