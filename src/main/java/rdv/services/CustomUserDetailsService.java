package rdv.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import rdv.model.Login;
import rdv.repository.LoginRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private LoginRepository loginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Login> opt = loginRepository.findByUserNameWithUserRole(username);
		if (!opt.isPresent()) {
			throw new UsernameNotFoundException("utilisateur inconnu");
		}
		return new CustomUserDetails(opt.get());
	}

}
