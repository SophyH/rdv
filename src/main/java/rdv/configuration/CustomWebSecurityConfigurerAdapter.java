//package rdv.configuration;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import rdv.services.CustomUserDetailsService;
//
//
//@Configuration
//public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter{
//
//	@Autowired
//	private CustomUserDetailsService userDetailsService;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		//A mettre avant tout et pour utiliser Angular
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
//		.antMatchers(HttpMethod.OPTIONS).anonymous();
//		
//		//sert à eviter qu'un autre utilisateur vole une session, désactiver le système par défaut 
//		http.csrf().disable();
//		
//		http.authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
//		
//		http.authorizeRequests().antMatchers("/**/edit").authenticated()
//		.and().formLogin().loginPage("/login").permitAll().and().logout().logoutSuccessUrl("/personne/list");
//		
//		http.authorizeRequests().antMatchers("/").permitAll();
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
//	}
//	
//	@Bean
//	public PasswordEncoder getPasswordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
//	
//}
