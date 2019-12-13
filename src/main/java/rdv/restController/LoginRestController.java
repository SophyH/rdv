package rdv.restController;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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

import rdv.model.Login;
import rdv.model.jsonViews.JsonViews;
import rdv.repository.LoginRepository;

////expliquer à ce service qui a le droit d'aller le chercher (moi j'ai mis j'accepte les requêtes de partout):
//@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/login")
@CrossOrigin(origins = { "*" })
public class LoginRestController {

	@Autowired
	private LoginRepository loginRepository;

	// Liste des logins
	@JsonView(JsonViews.Common.class)
	@GetMapping({ "", "/" })
	ResponseEntity<List<Login>> findAll() {
		return new ResponseEntity<>(loginRepository.findAll(), HttpStatus.OK);
	}

	// Remonter un seul login, rmq id=username
	@JsonView(JsonViews.Common.class)
	@GetMapping("/{id}")
	public ResponseEntity<Login> findByUsername(@PathVariable("id") String id) {
		Optional<Login> opt = loginRepository.findById(id);
		if (opt.isPresent()) {
			return new ResponseEntity<>(opt.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(opt.get(), HttpStatus.NOT_FOUND);
	}

	// Supprimer un login
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteLogin(@PathVariable("id") String id) {
		Optional<Login> opt = loginRepository.findById(id);
		if (opt.isPresent()) {
			loginRepository.deleteById(id);
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	// créer un login
	@PostMapping({ "", "/" })
	public ResponseEntity<Void> insert(@Valid @RequestBody Login login, BindingResult br, UriComponentsBuilder ucB) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		loginRepository.save(login);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucB.path("/login/{id}").buildAndExpand(login.getUsername()).toUri());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	// modifier un login: par exemple changer le mot de passe
	@PutMapping("/{id}")
	public ResponseEntity<Void> updateLogin(@PathVariable("id") String id, @Valid @RequestBody Login login,
			BindingResult br) {
		if (br.hasErrors()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Optional<Login> opt = loginRepository.findById(id);
		if (!opt.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Login loginEnBase = opt.get();
		loginEnBase.setPassword(login.getPassword());
		loginRepository.save(loginEnBase);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
