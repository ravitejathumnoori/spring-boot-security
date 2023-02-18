package io.javabrains.springbootsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity(debug = true)
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	CustAuthenticationProvider authenticationProvider;
	
	@Autowired
	CustomHeaderAuthFilter customAuthFilter;

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasAnyRole("USER","ADMIN")
		.antMatchers("/").permitAll().and().formLogin();
		
        http.addFilterBefore(
                customAuthFilter, 
                UsernamePasswordAuthenticationFilter.class);
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		 
		auth.userDetailsService(userDetailsService);
		
		auth.inMemoryAuthentication().withUser("admin1").password("admin1").authorities("ROLE_ADMIN")
		.and()
		.withUser("ravitejathumnoori@gmail.com").password("Ravi@1997").authorities("ROLE_USER")
		.and()
		.passwordEncoder(NoOpPasswordEncoder.getInstance());
		
		auth.authenticationProvider(authenticationProvider);
		
	}
	
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		
		return NoOpPasswordEncoder.getInstance();
	}
}
