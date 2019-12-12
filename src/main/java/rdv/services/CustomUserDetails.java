//package rdv.services;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//
//import rdv.model.Login;
//
//public class CustomUserDetails implements UserDetails {
//
//	public Login login;
//
//	public CustomUserDetails(Login login) {
//		this.login = login;
//	}
//
//	public Login getLogin() {
//		return login;
//	}
//
//	public void setLogin(Login login) {
//		this.login = login;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority(login.getRole().toString()));
//		return authorities;
//	}
//
//	@Override
//	public String getPassword() {
//		return login.getPassword();
//	}
//
//	@Override
//	public String getUsername() {
//		return login.getUsername();
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return login.isEnable();
//	}
//
//}