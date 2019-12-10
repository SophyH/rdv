package rdv.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

import rdv.model.jsonViews.JsonViews;

//import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "login")
public class Login {
	@JsonView(JsonViews.Common.class)
	@Id
	@Column(name = "username", length = 100)
//	@Length(min=3, message = "3 caractères minimum") 
//	@Length(max=3, message = "100 caractères maximum") 
	private String username;

	@JsonView(JsonViews.Common.class)
	@Column(name = "password", length = 15, nullable = false)
	// @Length(min=8, message = "8 caractères minimum") avec Validator
	// @Length(max=15, message = "15 caractères maximum") avec Validator
	private String password;

	//@JsonView(JsonViews.LoginWithPersonne.class)
	@OneToOne(mappedBy = "login")
	private Personne personne;
	
	@Column(name = "activation")
	private boolean enable;
	
	@JsonView(JsonViews.Common.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;

	@Version
	private Integer version;

	public Login() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public Personne getPersonne() {
		return personne;
	}

	public void setPersonne(Personne personne) {
		this.personne = personne;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
